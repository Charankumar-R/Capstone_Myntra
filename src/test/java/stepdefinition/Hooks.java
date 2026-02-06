package stepdefinition;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Hooks {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected ExtentSparkReporter extSparkReporter = new ExtentSparkReporter("reports/ExtentReport.html");
    protected ExtentReports extReports = new ExtentReports();
    protected ExtentTest extTest;
    protected Properties prop;


    @Before
    public void beforeScenario(Scenario scenario) {

        extReports.attachReporter(extSparkReporter);

        prop = PropertyReader.readProperty();
        String browser = prop.getProperty("Browser");
        String url = prop.getProperty("URL");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_leak_detection", false);

        if (browser.equalsIgnoreCase("chrome")) {
//            Using Chrome Browser
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--headless=new");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();

        } else {
            throw new RuntimeException("Invalid browser name: " + browser);
        }
        driver.get(url);
    }

    @After
    public void afterScenario(Scenario scenario) {
        driver.quit();
        extReports.flush();
//	    SimpleEmailSender.sendReport(); // email
    }
}
