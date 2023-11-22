package org.example;

import lombok.extern.log4j.Log4j2;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginTest extends BaseTest {

    Login loginPage = new Login();

    @BeforeMethod(description = "Open Main Page before method", alwaysRun = true)
    public void openLoginPage(){
        open("login");
    }

    @Test(description = "Checking Login To Website", groups = "Smoke")
    public void checkValidLogin() throws IOException, ParseException {
        log.info("Checking login with valid credentials");
        Assert.assertTrue(loginPage.loginToSiteValid());
        LoginUtils.logout();
    }

    @Test(description = "Checking Login To Website", groups = "Regression")
    public void checkInvalidLogin() {
        log.info("Checking login with valid credentials");
        Assert.assertFalse(loginPage.loginToSiteInvalid());
    }

}