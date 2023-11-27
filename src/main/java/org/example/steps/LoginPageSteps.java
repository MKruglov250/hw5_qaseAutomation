package org.example.steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.LoginPage;
import org.example.model.UserModel;

@Log4j2
public class LoginPageSteps extends BaseSteps {

    @Step("Login to site")
    public void login(UserModel user){
        log.info("Logging in");
        loginPage.setEmail(user);
        loginPage.setPassword(user);
        loginPage.clickLogin();
    }


    @Step("Login with assertion")
    public boolean loginToSite(UserModel user){
        log.info("Logging with user email: " + user.getEmail());
        login(user);
        boolean loginSuccessful = loginPage.loginSuccessful();
        log.info("Login succesful: " + loginSuccessful);
        return loginSuccessful;
    }

    @Step("Logout from site")
    public void logoutFromSite(){
        log.info("Logging out");
        if(LoginPage.menuButton.exists()) {
            loginPage.clickMenuButton();
            if (LoginPage.logoutButton.exists()) {
                loginPage.clickLogoutButton();
                log.info("Logout successful");
            } else log.info("Logout failed :(");
        } else log.info("Logout failed :(");
    }
}
