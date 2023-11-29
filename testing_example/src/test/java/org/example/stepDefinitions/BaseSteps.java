package org.example.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.utilities.ManageWebDriver;
import org.example.utilities.TestContext;

import java.util.*;

public class BaseSteps {
    private ManageWebDriver manageWebDriver;
    private TestContext testContext;

    public BaseSteps(TestContext testContext) {
        this.testContext = testContext;
        manageWebDriver = testContext.getManageWebDriver();
    }

    @Given("the user is on the registration page")
    public void theUserIsOnTheRegistrationPage() {
        System.out.println("Navigate to the registration page");
    }

    @When("the user enters the following details and submits")
    public void theUserEntersTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> data : dataList) {
            String firstName = data.get("First Name");
            String lastName = data.get("Last Name");
            String email = data.get("Email Address");
            String password = data.get("Password");

            System.out.println(firstName);
            System.out.print(lastName);
            System.out.print(email);
            System.out.println(password);

            // Fill in the registration form with the provided data and submit
        }
    }

    @Then("the user should be registered successfully")
    public void theUserShouldBeRegisteredSuccessfully() {
        System.out.println("Verify that the registration was successful");
    }

    //basic browser steps
    @Before(order = 1)
    public void initializeDriver() {
        manageWebDriver = new ManageWebDriver();
        manageWebDriver.initializeDriver();
        testContext.setManageWebDriver(manageWebDriver);
    }

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        try {
            scenario.attach(manageWebDriver.takeScreenshot(), "image/png", "Screenshot_" + scenario.getId());
            //System.out.println(scenario.getLine());
        } catch (Exception e) {
            System.out.println("Not able to take screenshot for line "+scenario.getLine());
        }
    }

    @After
    public void quitBrowser() {
        manageWebDriver.quit();
    }

    @Given("Open {string} website")
    public void openWebsite(String url) {
        manageWebDriver.openUrl(url);
    }

    @Then("Get page title")
    public void getPageTitle() {
        System.out.println(manageWebDriver.getDriver().getTitle());
    }

    @And("Wait for {string} to come as page title")
    public void waitForToComeAsPageTitle(String expectedTitle) {
        manageWebDriver.waitForPageTitleToLoad(expectedTitle);
    }
}
