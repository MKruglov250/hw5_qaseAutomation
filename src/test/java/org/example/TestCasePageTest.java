package org.example;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class TestCasePageTest extends BaseTest {

    Login login = new Login();
    TestCasePage testCasePage = new TestCasePage();

    public TestCasePageTest() throws ParserConfigurationException, IOException, SAXException {
    }

    @BeforeClass(description = "Login before performing Test Case module tests",
    alwaysRun = true)
    public void beforeClass() throws IOException, ParseException {
        login.loginToSiteValid();
        open("project/QASEAPP");
    }

    @Test(description = "Check Creation of Test Case", groups = "Smoke")
    public void testCreateTestCase(){
        testCasePage.newTestCase();
        testCasePage.enterTestCaseData();
        testCasePage.saveTestCase();
        Assert.assertTrue(testCasePage.firstTestCaseElement.exists());
    }

}