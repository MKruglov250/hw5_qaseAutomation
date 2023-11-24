package org.example;

import io.qameta.allure.TmsLink;
import lombok.extern.log4j.Log4j2;
import org.example.model.UserModel;
import org.example.model.UserModelBuilder;
import org.example.pagesteps.steps.LoginPageSteps;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginTest extends BaseTest {

    Login loginPage = new Login();
    LoginPageSteps loginPageSteps = new LoginPageSteps();
    UserModel validUser = UserModelBuilder.getValidUser();
    UserModel badUser = UserModelBuilder.getIncorrectUser();

    public LoginTest() throws IOException, ParseException {
    }

    @BeforeMethod(description = "Open Main Page before method", alwaysRun = true)
    public void openLoginPage(){
        open("login");
    }


//    public void checkValidLogin() throws IOException, ParseException {
//        log.info("Checking login with valid credentials");
//        Assert.assertTrue(loginPage.loginToSiteValid());
//    }
//
    //    public void checkInvalidLogin() {
//        log.info("Checking login with valid credentials");
//        Assert.assertFalse(loginPage.loginToSiteInvalid());
//    }
//
//    @AfterClass(alwaysRun = true)
//    public void logout(){
//        LoginUtils.logout();
//    }

    @TmsLink("QAT-9")
    @Test(description = "Checking Login To Website with valid credentials", groups = "Smoke",
            priority = 1)
    public void checkValidLogin(){
        log.info("Checking login with valid credentials");
        Assert.assertTrue(loginPageSteps.loginToSite(validUser));
    }

    @TmsLink("QAT-10")
    @Test(description = "Checking Login To Website with bad credentials", groups = "Regression")
    public void checkInvalidLogin(){
        log.info("Checking login with valid credentials");
        Assert.assertFalse(loginPageSteps.loginToSite(badUser));
    }

    @AfterClass
    public void logout(){
        log.info("Login Page steps complete, logging out");
        loginPageSteps.logoutFromSite();
    }

}