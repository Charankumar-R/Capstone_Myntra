package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//span[normalize-space()='Profile']")
    private WebElement profile;

    @FindBy(xpath = "//a[normalize-space()='login / Signup']")
    private WebElement login;

    @FindBy(css = "input[type='tel']")
    private WebElement verifyLogin;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    private boolean validateLogin() {
        Actions actions = new Actions(driver);
        try {
            actions.moveToElement(profile).perform();
            wait.until(ExpectedConditions.visibilityOf(login));
            login.click();
            wait.until(ExpectedConditions.visibilityOf(verifyLogin));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
