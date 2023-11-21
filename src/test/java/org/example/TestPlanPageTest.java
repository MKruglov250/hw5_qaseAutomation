package org.example;

import lombok.extern.log4j.Log4j2;
import org.example.BaseTest;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.*;

@Log4j2
public class TestPlanPageTest extends BaseTest {

    TestPlanPage testPlanPage = new TestPlanPage();
    Login login = new Login();

    public TestPlanPageTest() throws ParserConfigurationException, IOException, SAXException {
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
        log.info("Test Plan Creation checked");
    }

    @Test(description = "Check Create Test Plan")
    public void checkReadTestPlan(){
        log.info("Checking Create Test Plan");
        log.info("Test Plan Creation checked");
    }

    @Test(description = "Check Create Test Plan")
    public void checkUpdateTestPlan(){
        log.info("Checking Create Test Plan");
        log.info("Test Plan Creation checked");
    }

    @Test(description = "Check Create Test Plan")
    public void checkDeleteTestPlan(){
        log.info("Checking Create Test Plan");
        log.info("Test Plan Creation checked");
    }


}