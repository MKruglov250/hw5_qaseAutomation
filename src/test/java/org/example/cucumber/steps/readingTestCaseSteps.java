package org.example.cucumber.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.TestCasePage;
import org.example.model.UserModel;
import org.example.model.UserModelBuilder;
import org.example.steps.LoginPageSteps;
import org.example.steps.TestCasePageSteps;
import org.example.utilities.PropertyReader;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class readingTestCaseSteps {

    UserModel user = UserModelBuilder.getValidUser();
    LoginPageSteps loginPageSteps = new LoginPageSteps();
    TestCasePageSteps testCasePageSteps = new TestCasePageSteps();
    TestCasePage testCasePage = new TestCasePage();

    public readingTestCaseSteps() throws IOException, ParseException {
    }

    @Before
    public void setUp() throws FileNotFoundException {
        Configuration.baseUrl = PropertyReader.getProperty("Baseurl");
        Configuration.browser = PropertyReader.getProperty("Browser");
        open(".");
        getWebDriver().manage().window().maximize();
        getWebDriver().manage().timeouts().implicitlyWait(Duration
                .ofSeconds(PropertyReader.getTimeoutProperty()));
    }

    @Given("User logs in")
    public void userLogsIn() {
        loginPageSteps.login(user);
    }

    @When("User opens project")
    public void userOpensProject() {
        testCasePageSteps.openQaseProject();
    }

//    @And("User clicks Cucu Case")
//    public void userClicksCucuCase() {
//        SelenideElement testCase = $x("//div[text()='Cucu Case']");
//        testCase.click();
//    }

    @And("User clicks {string}")
    public void userClicksCaseName(String caseName) {
        SelenideElement testCase = $x(String.format("//div[text()='%s']",caseName));
        testCase.click();
    }

    @And("User clicks Edit button")
    public void userClicksEditButton() {
        testCasePage.clickEditCase();
    }

//    @Then("Title is Cucu Case")
//    public void titleIsCucuCase() {
//        Assert.assertEquals(testCasePage.titleInput.getValue(),"Cucu Case");
//    }

    @Then("Title is {string}")
    public void titleIsCaseTitle(String caseTitle) {
        Assert.assertEquals(testCasePage.titleInput.getValue(),caseTitle);
    }

    @After
    public void logout(){
        loginPageSteps.logoutFromSite();
    }
}
