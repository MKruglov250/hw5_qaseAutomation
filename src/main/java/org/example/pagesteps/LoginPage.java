package org.example.pagesteps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.UserModel;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class LoginPage {

    static SelenideElement emailInput = $x("//input[@name='email']");

    static SelenideElement passwordInput = $x("//input[@name='password']");

    static SelenideElement loginButton = $x("//button[@type='submit']");

    static SelenideElement projectQaseControl = $x("//a[text()='Qaseapp']");

    static SelenideElement badCredentialsMessage = $x("//*[text()='These credentials do not match our records.']");

    public static SelenideElement menuButton = $x("//img[contains(@src,'.jpg')]");

    public static SelenideElement logoutButton = $x("//*[text()='Sign out']");


    @Step("Set user email")
    public void setEmail(UserModel user){
        emailInput.setValue(user.getEmail());
        log.debug("Set user email: " + user.getEmail());
    }

    @Step("Set user password")
    public void setPassword(UserModel user){
        passwordInput.setValue(user.getPassword());
        log.debug("Set provided user password");
    }

    @Step("Click login button")
    public void clickLogin(){
        log.debug("Logging in with provided login and password");
        loginButton.click();
    }

    @Step("Check user message")
    public boolean loginSuccessful(){
        log.debug("Checking if Login Successful, Projects page open");
        if (badCredentialsMessage.exists()){
            return false;
        }
        return (projectQaseControl.exists());
    }

    @Step("Click Menu Button")
    public void clickMenuButton(){
        log.debug("Opening Qase menu");
        menuButton.click();
    }

    @Step("Click Logout button")
    public void clickLogoutButton(){
        log.debug("Clicking logout button");
        logoutButton.click();
    }

    @Step("Check logout successful")
    public boolean checkLogoutSuccesful(){
        log.debug("Checking logout succeeded");
        return (!projectQaseControl.exists());
    }

}
