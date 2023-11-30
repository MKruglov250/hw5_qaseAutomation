package org.example;

import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
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

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class TestPlanPageTest extends BaseTest {

    TestPlanPage testPlanPage = new TestPlanPage();
    TestPlanModel testPlanModel = TestPlanModelBuilder
            .getTestPlan("Test Plan 1", "Simple description");
    TestPlanModel realTestPlan = TestPlanModelBuilder
            .getTestPlan("Real e2e plan", "Complicated desc");
    TestCaseModel mockCaseOne = TestCaseModelBuilder.getTestCase();
    TestCaseModel mockCaseTwo = TestCaseModelBuilder.getTestCase();
    TestCaseModel firstCaseModel = TestCaseModelBuilder.getTestCase(1);
    TestCaseModel secondCaseModel = TestCaseModelBuilder.getTestCase(2);
    static int mockCaseOneId;
    static int mockCaseTwoId;
    public static int planId;

    public TestPlanPageTest() throws IOException, ParseException, ParserConfigurationException, SAXException {
        super();
    }

    @BeforeClass(description = "Create mock test cases using WebAPI", alwaysRun = true)
    public void createMockTestCases(){
        log.info("Creating Mock Test Cases before test");
        Response response = requests.createMockTestCase(mockCaseOne.getTitle(), 3);
        mockCaseOneId = response.jsonPath().get("result.id");
        response = requests.createMockTestCase(mockCaseTwo.getTitle(), 3);
        mockCaseTwoId = response.jsonPath().get("result.id");
        log.info("Created Mock Test Cases");
    }

//    @BeforeClass(description = "Create mock test cases", alwaysRun = true)
//    public void createMockTestCases() {
//        log.info("Creating Mock Test Cases before test");
//        loginPageSteps.login(validUser);
//        testCasePageSteps.openQaseProject();
//        testCasePageSteps.createMockTestCase(mockCaseOne);
//        testCasePageSteps.createMockTestCase(mockCaseTwo);
//        log.info("Created mock test cases");
//        loginPageSteps.logoutFromSite();
//    }

    @BeforeMethod(description = "Login before performing Test Case module tests",
            alwaysRun = true)
    public void beforeMethod() {
        log.info("Logging in");
        loginPageSteps.login(validUser);
        testCasePageSteps.openQaseProject();
        testPlanPageSteps.openTestPlans();
    }

    @AfterMethod(description = "Logging out after performing test", alwaysRun = true)
    public void logout(){
        loginPageSteps.logoutFromSite();
    }

//    @AfterClass(description = "Deleting Mock Test Cases and E2E Test Plan", alwaysRun = true)
//    public void deleteMockCasesAndRealPlan(){
//        log.info("Deleting mock cases and E2E test plan");
//        loginPageSteps.login(validUser);
//        testCasePageSteps.openQaseProject();
//        testCasePageSteps.deleteTestCase(mockCaseOne.getTitle());
//        testCasePageSteps.deleteTestCase(mockCaseTwo.getTitle());
//        Selenide.refresh();
//        testCasePageSteps.deleteTestCase(firstCaseModel.getTitle());
//        testCasePageSteps.deleteTestCase(secondCaseModel.getTitle());
//        TestCaseModelBuilder.mockTestCases = 0;
//        log.info("Deleted mock test cases");
//        Selenide.refresh();
//        testPlanPageSteps.openTestPlans();
//        testPlanPageSteps.deleteTestPlan(realTestPlan.getTitle());
//        log.info("Deleted E2E Test Plan");
//        loginPageSteps.logoutFromSite();
//    }

    @AfterClass(description = "Delete Mock Cases and e2e Test Plan")
    public void deleteMockCasesAndTestPlan(){
        log.info("Deleting mock cases");
        requests.deleteTestCase(mockCaseOneId);
        requests.deleteTestCase(mockCaseTwoId);
        log.info("Deleting e2e test plan");
        requests.deleteTestPlan(planId);
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
    public void checkTestPlanWithCases() {
        log.info("Creating test plan with First Test Case & Second Test Case");
        testCasePageSteps.openRepository();
        testCasePageSteps.createTestCase(firstCaseModel);
        testCasePageSteps.createTestCase(secondCaseModel);
        testPlanPageSteps.createEndtoendPlan(realTestPlan);
        testPlanPageSteps.readTestPlan(realTestPlan.getTitle());
        planId = Integer.parseInt(getWebDriver().getCurrentUrl().replace("https://app.qase.io/plan/QASEAPP/",""));
        testPlanPage.switchToTestCases();
        Assert.assertEquals(testPlanPage.getTestCaseDescription(firstCaseModel.getTitle())
                ,"Description of First Test Case");
        Assert.assertEquals(testPlanPage.getTestCaseDescription(secondCaseModel.getTitle())
                ,"Second Test Case description");
    }

}