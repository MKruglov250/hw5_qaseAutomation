package org.example;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import lombok.extern.log4j.Log4j2;
import org.example.model.UserModel;
import org.example.model.UserModelBuilder;
import org.example.steps.LoginPageSteps;
import org.example.steps.TestCasePageSteps;
import org.example.steps.TestPlanPageSteps;
import org.example.utilities.OurListener;
import org.example.utilities.PropertyReader;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(OurListener.class)
@Log4j2
public class BaseTest {

    UserModel validUser = UserModelBuilder.getValidUser();
    UserModel badUser = UserModelBuilder.getIncorrectUser();
    LoginPageSteps loginPageSteps = new LoginPageSteps();
    TestCasePageSteps testCasePageSteps = new TestCasePageSteps();
    TestPlanPageSteps testPlanPageSteps = new TestPlanPageSteps();
    Requests requests = new Requests();


    public BaseTest() throws IOException, ParseException {
        RestAssured.baseURI="https://api.qase.io/v1";
    }

    @BeforeSuite
    public void setSystemProperties(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("currenttime", dateFormat.format(new Date()));
    }

    @Attachment
    @Step("Get provided Resource file and attach it to report")
    public static byte[] getFileBytes(String resourceName) throws IOException{
        log.info("Attaching Properties file");
        return Files.readAllBytes(Paths.get("src/resources", resourceName));
    }

//    @Parameters({"BrowserType"})
    @BeforeSuite(alwaysRun = true, description = "Initialize testing for Qase.io")
    public void before() throws IOException {
        log.info("Starting configuring web driver");
        getFileBytes("config.properties");
        Configuration.baseUrl = PropertyReader.getProperty("Baseurl");
//        if (sBrowserType != null){
//            Configuration.browser = sBrowserType;
//        } else {}
        Configuration.browser = PropertyReader.getProperty("Browser");
        Configuration.headless = Boolean.getBoolean(PropertyReader.getProperty("Headless"));
        open(".");

        getWebDriver().manage().window().maximize();
        getWebDriver().manage().timeouts().implicitlyWait(Duration
                .ofSeconds(PropertyReader.getTimeoutProperty()));
        log.info("Web driver configuration complete");
    }

    @AfterSuite(description = "Closing web drivers", alwaysRun = true)
    public void afterTest() {
        closeWebDriver();
    }

}
