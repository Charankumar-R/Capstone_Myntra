package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(css = "input[type='tel']")
    private WebElement mobileNumberInput;
    @FindBy(css = "input[type='checkbox']")
    private WebElement byContinuing, IAgreeToThe;
    @FindBy(css = ".submitBottomOption")
    private WebElement continueButton;
    @FindBy(css = "div[class='mobContainer'] h3")
    private WebElement validateOtpPage;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);

    }

    private boolean validateMobileNumber(String mobileNumber) {
        try {
            mobileNumberInput.sendKeys(mobileNumber);
            byContinuing.click();
            wait.until(ExpectedConditions.visibilityOf(continueButton));
            continueButton.click();
            wait.until(ExpectedConditions.visibilityOf(validateOtpPage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
