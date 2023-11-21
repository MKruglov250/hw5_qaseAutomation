package org.example;

import lombok.extern.log4j.Log4j2;
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

    public TestCasePageTest() throws ParserConfigurationException, IOException, SAXException {
    }

    @BeforeMethod(description = "Login before performing Test Case module tests",
    alwaysRun = true)
    public void beforeMethod() throws IOException, ParseException {
        login.loginToSiteValid();
        log.info("Logged in to Qase.io");
        open("project/QASEAPP");
    }

    @Test(description = "Check Creation of Test Case", groups = "Smoke")
    public void testCreateTestCase(){
        log.info("Asserting Create Test Case One");
        testCasePage.createTestCase();
        Assert.assertTrue(testCasePage.checkFirstTestCaseExists());
        log.info("Checked Creation of Test Case One");
    }

    @Test(description = "Check Reading Test Case", groups = "Smoke",
            priority = 1)
    public void testReadTestCaseOne(){
        log.info("Asserting Read Test Case One");
        Assert.assertTrue(testCasePage.checkFirstTestcaseRead());
        log.info("Checked Test Case One is read");
    }

    @Test(description = "Check Editing Test Case", groups = "Regression",
            priority = 2)
    public void testEditTestCaseOne(){
        log.info("Asserting Edit Test Case One");
        Assert.assertEquals(testCasePage.checkEditFirstTestCase(),
                "Brand-new edited description");
        log.info("Checked Description field of Test Case One");
    }

    @Test(description = "Check Deleting test case", groups = "Regression",
            priority = 3)
    public void testDeleteTestCaseOne(){
        log.info("Checking Test Case Deletion");
        testCasePage.openTestCase();
        testCasePage.deleteTestCase();
        testCasePage.confirmDeletion();
        boolean deleteMessageExists = testCasePage.checkDeleteMessage();
        Assert.assertTrue(deleteMessageExists);
        log.info("Delete message exists: " + deleteMessageExists);
    }

    @AfterMethod(description = "Logging out after performing test")
    public void logout(){
        LoginUtils.logout();
    }

}