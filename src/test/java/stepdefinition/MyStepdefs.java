package stepdefinition;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class MyStepdefs {
    HomePage homePage;

    @Given("user is on the home page of Myntra")
    public void userIsOnTheHomePageOfMyntra() {
        homePage = new HomePage(Hooks.driver, Hooks.wait);
    }

    @When("user hovers to profile and clicks on login")
    public void userHoversToProfileAndClicksOnLogin() {
        boolean flag = homePage.validateLogin();
    }

    @And("user enters mobile number and clicks continue")
    public void userEntersMobileNumberAndClicksContinue() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("user verify's with otp")
    public void userVerifySWithOtp() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("user should be successfully logged into Myntra account")
    public void userShouldBeSuccessfullyLoggedIntoMyntraAccount() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("user enter's incorrect otp")
    public void userEnterSIncorrectOtp() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
