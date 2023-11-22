package org.example;

import lombok.extern.log4j.Log4j2;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class TestPlanPageTest extends BaseTest {

    TestPlanPage testPlanPage = new TestPlanPage();
    Login login = new Login();

    public TestPlanPageTest() throws ParserConfigurationException, IOException, SAXException {
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
        log.info("Checking Create Test Plan");
        Assert.assertTrue(testPlanPage.createTestPlan());
    }

    @Test(description = "Check Create Test Plan")
    public void checkReadTestPlan(){
        log.info("Checking Read Test Plan");
        Assert.assertTrue(testPlanPage.readTestPlan());

    }

    @Test(description = "Check Create Test Plan", priority = 1)
    public void checkEditTestPlan(){
        log.info("Checking Edit Test Plan");
        Assert.assertTrue(testPlanPage.editTestPlan());
    }

    @Test(description = "Check Create Test Plan", priority = 2)
    public void checkDeleteTestPlan(){
        log.info("Checking Delete Test Plan");
        Assert.assertTrue(testPlanPage.deleteTestPlan());

    }

    @AfterMethod(description = "Logging out after performing test")
    public void logout(){
        LoginUtils.logout();
    }

}