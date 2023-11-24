package org.example;

import io.qameta.allure.TmsLink;
import lombok.extern.log4j.Log4j2;
import org.example.model.TestCaseModel;
import org.example.model.TestCaseModelBuilder;
import org.example.model.TestPlanModel;
import org.example.model.TestPlanModelBuilder;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Log4j2
public class TestPlanPageTest extends BaseTest {

    TestPlanPage testPlanPage = new TestPlanPage();
    Login login = new Login();
    TestPlanModel testPlanModel = TestPlanModelBuilder
            .getTestPlan("Test Plan 1", "Simple description");
    TestPlanModel realTestPlan = TestPlanModelBuilder
            .getTestPlan("Real e2e plan", "Complicated desc");
    TestCaseModel mockCaseOne = TestCaseModelBuilder.getTestCase();
    TestCaseModel mockCaseTwo = TestCaseModelBuilder.getTestCase();

    public TestPlanPageTest() throws IOException, ParseException {
        super();
    }

    @BeforeClass(description = "Create mock test cases", alwaysRun = true)
    public void createMockTestCases() {
        log.info("Creating Mock Test Cases before test");
        loginPageSteps.loginToSite(validUser);
        testCasePageSteps.openQaseProject();
        testCasePageSteps.createMockTestCase(mockCaseOne);
        testCasePageSteps.createMockTestCase(mockCaseTwo);
        log.info("Created mock test cases");
        loginPageSteps.logoutFromSite();
    }


    @BeforeMethod(description = "Login before performing Test Case module tests",
            alwaysRun = true)
    public void beforeMethod() {
        log.info("Logging in");
        loginPageSteps.loginToSite(validUser);
        testCasePageSteps.openQaseProject();
        testPlanPageSteps.openTestPlans();
    }

    @TmsLink("QAT-5")
    @Test(description = "Check Create Test Plan", groups = "Smoke")
    public void checkCreateTestPlan(){
        log.info("Checking Create Test Plan 1");
        Assert.assertTrue(testPlanPageSteps.createTestPlan(testPlanModel));
    }

    @TmsLink("QAT-6")
    @Test(description = "Check Read Test Plan", groups = "Smoke")
    public void checkReadTestPlanOne(){
        log.info("Checking Read Test Plan 1");
        Assert.assertEquals(testPlanPageSteps.readTestPlan(testPlanModel.getTitle()),
                "Simple description");

    }

    @TmsLink("QAT-7")
    @Test(description = "Check Edit Test Plan", groups = "Regression",
            priority = 1)
    public void checkEditTestPlanOne(){
        log.info("Checking Edit Test Plan 1");
        Assert.assertTrue(testPlanPageSteps.editTestPlan(testPlanModel.getTitle()));
    }

    @TmsLink("QAT-8")
    @Test(description = "Check Delete Test Plan", groups = "Regression",
            priority = 2)
    public void checkDeleteTestPlan(){
        log.info("Checking Delete Test Plan 1");
        Assert.assertTrue(testPlanPageSteps.deleteTestPlan(testPlanModel.getTitle()));
    }

    @Test(description = "End to end: Create Test Plan with complete Test Cases",
            groups = "E2E", priority = 4)
    public void checkTestPlanWithCases() throws ParserConfigurationException, IOException, SAXException {
        log.info("Creating test plan with First Test Case & Second Test Case");
        testPlanPage.createRealTestCases();
        testPlanPage.createRealPlan(realTestPlan);
        log.info("Checking description of real test plan");
        Assert.assertEquals(testPlanPage.readTestPlan("Real e2e plan"),
                                                "Complicated desc");
        testPlanPage.switchToTestCases();
        log.info("Checking test cases are added, description is correct");
        Assert.assertEquals(testPlanPage.getTestCaseDescription("First Test Case")
                ,"Description of First Test Case");
        Assert.assertEquals(testPlanPage.getTestCaseDescription("Second Test Case")
                ,"Second Test Case description");
    }

    @AfterMethod(description = "Logging out after performing test", alwaysRun = true)
    public void logout(){
        loginPageSteps.logoutFromSite();
    }

    @AfterClass(description = "Deleting Mock Test Cases", alwaysRun = true)
    public void deleteMockCases() throws IOException, ParseException {
        login.loginToSiteValid();
        testCasePageSteps.openQaseProject();
        testCasePageSteps.deleteTestCase(mockCaseOne.getTitle());
        testCasePageSteps.deleteTestCase(mockCaseTwo.getTitle());
        TestCaseModelBuilder.mockTestCases = 0;
        log.info("Deleted mock test cases");
        loginPageSteps.logoutFromSite();
    }

}