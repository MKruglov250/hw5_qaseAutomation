package org.example;

import lombok.extern.log4j.Log4j2;
import org.example.model.TestPlanModel;
import org.example.model.TestPlanModelBuilder;
import org.example.utilities.LoginUtils;
import org.example.utilities.TestPlanPageUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class TestPlanPageTest extends BaseTest {

    TestPlanPage testPlanPage = new TestPlanPage();
    Login login = new Login();
    static TestPlanModel testPlanModel = TestPlanModelBuilder
            .getTestPlan("Test Plan 1", "Simple description");

    public TestPlanPageTest() {
    }

    @BeforeClass(description = "Create mock test cases")
    public void createMockTestCases() throws IOException, ParseException {
        login.loginToSiteValid();
        testPlanPage.createMockTestCases();
        log.info("Created mock test cases");
        LoginUtils.logout();
    }


    @BeforeMethod(description = "Login before performing Test Case module tests",
            alwaysRun = true)
    public void beforeMethod() throws IOException, ParseException {
        login.loginToSiteValid();
        log.info("Logged in to Qase.io");
        open("plan/QASEAPP");
    }

    @Test(description = "Check Create Test Plan")
    public void checkCreateTestPlan(){
        log.info("Checking Create Test Plan 1");
        Assert.assertTrue(testPlanPage.createTestPlan(testPlanModel));
    }

    @Test(description = "Check Read Test Plan")
    public void checkReadTestPlanOne(){
        log.info("Checking Read Test Plan 1");
        Assert.assertEquals(testPlanPage.readTestPlan("Test Plan 1"), "Simple description");

    }

    @Test(description = "Check Edit Test Plan", priority = 1)
    public void checkEditTestPlanOne(){
        log.info("Checking Edit Test Plan 1");
        Assert.assertTrue(testPlanPage.editTestPlan("Test Plan 1"));
    }

    @Test(description = "Check Delete Test Plan", priority = 2)
    public void checkDeleteTestPlan(){
        log.info("Checking Delete Test Plan 1");
        Assert.assertTrue(testPlanPage.deleteTestPlan("Test Plan 1"));
    }

    @AfterMethod(description = "Logging out after performing test")
    public void logout(){
        LoginUtils.logout();
    }

    @AfterClass(description = "Deleting Mock Test Cases")
    public void deleteMockCases() throws IOException, ParseException {
        login.loginToSiteValid();
        TestPlanPageUtils.deleteMockTestCases();
        log.info("Deleted mock test cases");
        LoginUtils.logout();
    }

}