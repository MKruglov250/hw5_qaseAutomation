package org.example;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.UserModel;
import org.example.model.UserModelBuilder;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;

import java.io.IOException;

@Log4j2
public class Login {

    @Step
    public boolean loginToSiteValid() throws IOException, ParseException {
        UserModel validUser = UserModelBuilder.getValidUser();
        log.debug("Logging into website with " + validUser.getEmail() + " user email");
        boolean loginSuccess = LoginUtils.loginToSite(validUser);
        log.debug("Login to site succeeded: " + loginSuccess);
        return loginSuccess;
    }

    @Step
    public boolean loginToSiteInvalid(){
        UserModel invalidUser = UserModelBuilder.getIncorrectUser();
        log.debug("Logging into website with " + invalidUser.getEmail() + " user email (invalid user)");
        boolean loginSuccess = LoginUtils.loginToSite(invalidUser);
        log.debug("Login to site succeeded: " + loginSuccess);
        return loginSuccess;
    }
}
