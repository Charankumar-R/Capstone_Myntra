package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Base {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static ExtentReports extentReports;
    protected Properties prop;

    public void launchBrowser() {

        // ✅ Ensure reports folder exists
        new File("reports").mkdirs();

        // ✅ Attach reporter
        ExtentSparkReporter spark =
                new ExtentSparkReporter("reports/ExtentReport.html");

        extentReports = new ExtentReports();
        extentReports.attachReporter(spark);

        prop = PropertyReader.readProperty();
        String browser = prop.getProperty("Browser");
        String url = prop.getProperty("URL");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_leak_detection", false);

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();

        } else {
            throw new RuntimeException("Invalid browser name: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(url);
    }

    // ✅ MUST be called once after all tests
    public void tearDown() {
        if (extentReports != null) {
            extentReports.flush();
        }
        if (driver != null) {
            driver.quit();
        }


    }
}
