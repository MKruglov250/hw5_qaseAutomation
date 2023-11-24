package org.example.pagesteps.steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.UserModel;
import org.example.pagesteps.LoginPage;

@Log4j2
public class LoginPageSteps extends BaseSteps {

    @Step("Login to site")
    public boolean loginToSite(UserModel user){
        log.info("Logging with user email: " + user.getEmail());
        loginPage.setEmail(user);
        loginPage.setPassword(user);
        loginPage.clickLogin();
        boolean loginSuccessful = loginPage.loginSuccessful();
        log.info("Login succesful: " + loginSuccessful);
        return loginSuccessful;
    }

    @Step("Logout from site")
    public boolean logoutFromSite(){
        log.info("Logging out");
        if(LoginPage.menuButton.exists()) {
            loginPage.clickMenuButton();
            if (LoginPage.logoutButton.exists()) {
                loginPage.clickLogoutButton();
            }
        }
        boolean logoutSuccessful = loginPage.checkLogoutSuccesful();
        log.info("User logged out: " + logoutSuccessful);
        return logoutSuccessful;
    }
}
