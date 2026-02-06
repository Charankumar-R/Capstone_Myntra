package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OtpPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = ".desktop-infoEmail")
    private WebElement mobileNumber;

    public OtpPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    private boolean validateOtp(String mobileNum) {
        try {
            String number = mobileNumber.getText();
            return mobileNum.equals(number);
        } catch (TimeoutException e) {
            return false;
        }
    }

}
