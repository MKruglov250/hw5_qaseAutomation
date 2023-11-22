package org.example;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.utilities.OurListener;
import org.example.utilities.PropertyReader;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(OurListener.class)
@Log4j2
public class BaseTest {

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

    @BeforeClass(alwaysRun = true, description = "Initialize testing for Qase.io")
    public void before() throws IOException {
        log.info("Starting configuring web driver");
        getFileBytes("config.properties");

        Configuration.baseUrl = "https://app.qase.io/";
        Configuration.browser = PropertyReader.getBrowserProperty();
        Configuration.headless = false;
        open(".");

        getWebDriver().manage().window().maximize();
        getWebDriver().manage().timeouts().implicitlyWait(Duration
                .ofSeconds(PropertyReader.getTimeoutProperty()));
        log.info("Web driver configuration complete");
    }

    @AfterSuite(description = "Closing web drivers", alwaysRun = true)
    public void afterSuite() {
    }

}
