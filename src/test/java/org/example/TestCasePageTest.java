package org.example;

import lombok.extern.log4j.Log4j2;
import org.example.model.TestCaseModel;
import org.example.model.TestCaseModelBuilder;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class TestCasePageTest extends BaseTest {

    Login login = new Login();
    TestCasePage testCasePage = new TestCasePage();
    TestCaseModel firstCaseModel = TestCaseModelBuilder.getTestCase(1);
    TestCaseModel secondCaseModel = TestCaseModelBuilder.getTestCase(2);

    public TestCasePageTest() throws ParserConfigurationException, IOException, SAXException {
    }

    @BeforeMethod(description = "Login before performing Test Case module tests",
    alwaysRun = true)
    public void beforeMethod() throws IOException, ParseException {
        login.loginToSiteValid();
        log.info("Logged in to Qase.io");
        open("project/QASEAPP");
    }

    @Test(description = "Check Creation of Test Case 1", groups = "Smoke")
    public void testCreateTestCaseOne(){
        log.info("Checking Create Test Case One");
        testCasePage.createTestCase(firstCaseModel);
        Assert.assertTrue(testCasePage.checkTestCaseExists(firstCaseModel.getTitle()));
    }

    @Test(description = "Check Creation of Test Case 1", groups = "Smoke")
    public void testCreateTestCaseTwo(){
        log.info("Checking Create Test Case One");
        testCasePage.createTestCase(secondCaseModel);
        Assert.assertTrue(testCasePage.checkTestCaseExists(secondCaseModel.getTitle()));
    }

    @Test(description = "Check Reading Test Case", groups = "Smoke",
            priority = 1)
    public void testReadTestCaseOne(){
        log.info("Checking Read Test Case One");
        Assert.assertEquals(testCasePage.checkTestCaseRead(firstCaseModel.getTitle()),
                "First Test Case");
    }

    @Test(description = "Check Editing Test Case", groups = "Regression",
            priority = 2)
    public void testEditTestCaseOne(){
        log.info("Checking Edit Test Case One");
        Assert.assertEquals(testCasePage.checkEditTestCase(firstCaseModel.getTitle()),
                "Brand-new edited description");
    }

    @Test(description = "Check Deleting test case 1", groups = "Regression",
            priority = 3)
    public void testDeleteTestCaseOne(){
        log.info("Checking Delete Test Case 1");
        testCasePage.deleteTestCase(firstCaseModel.getTitle());
        Assert.assertTrue(testCasePage.checkDeleteMessage());
    }

    @Test(description = "Check Deleting test case 2", groups = "Regression",
            priority = 3)
    public void testDeleteTestCaseTwo(){
        log.info("Checking Delete Test Case 2");
        testCasePage.deleteTestCase(secondCaseModel.getTitle());
        Assert.assertTrue(testCasePage.checkDeleteMessage());
    }

    @AfterMethod(description = "Logging out after performing test")
    public void logout(){
        LoginUtils.logout();
    }

}