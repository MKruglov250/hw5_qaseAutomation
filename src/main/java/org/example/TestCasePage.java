package org.example;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.TestCaseModel;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class TestCasePage {

    SelenideElement projectQaseControl = $x("//a[text()='Qaseapp']");
    SelenideElement repositoryPage = $x("//span[text()='Repository']/parent::a");

    public SelenideElement titleInput = $x("//input[@name='title']");
    public SelenideElement descriptionInput = $x("//label[@for='0-description']" +
            "/parent::div//div[@class='ProseMirror toastui-editor-contents']/p");
    SelenideElement preconditionInput = $x("//label[@for='0-preconditions']" +
            "/parent::div//div[@class='ProseMirror toastui-editor-contents']/p");
    SelenideElement postconditionInput = $x("//label[@for='0-postconditions']" +
            "/parent::div//div[@class='ProseMirror toastui-editor-contents']/p");

    ElementsCollection pageDropdowns = $$("input[aria-autocomplete='list']");
    SelenideElement statusDropdown = pageDropdowns.get(0);
    SelenideElement suiteDropdown = pageDropdowns.get(1);
    SelenideElement severityDropdown = pageDropdowns.get(2);
    SelenideElement priorityDropdown = pageDropdowns.get(3);
    SelenideElement typeDropdown = pageDropdowns.get(4);
    SelenideElement layerDropdown = pageDropdowns.get(5);
    SelenideElement isFlakyDropdown = pageDropdowns.get(6);
    SelenideElement milestoneDropdown = pageDropdowns.get(7);
    SelenideElement behaviourDropdown = pageDropdowns.get(8);
    SelenideElement automationStatusDropdown = pageDropdowns.get(9);

    SelenideElement createCaseButton = $x("//a[@id='create-case-button']");
    SelenideElement saveCaseButton = $x("//*[text()='Save']").parent();
    SelenideElement editCaseButton = $x("//a[contains(@href,'/case/QASEAPP/edit')]");
    SelenideElement deleteCaseButton = editCaseButton.sibling(1);
    SelenideElement deleteConfirmButton = $x("//*[text()='Delete']");

    SelenideElement addStepButton = $x("//*[text()=' Add step']").parent();
    SelenideElement stepActionInput = $x("(//div[@class='case-create-block steps-block']" +
            "/following-sibling::div//p)[1]");
    SelenideElement stepDataInput = $x("(//div[@class='case-create-block steps-block']" +
            "/following-sibling::div//p)[2]");
    SelenideElement stepResultInput = $x("(//div[@class='case-create-block steps-block']" +
            "/following-sibling::div//p)[3]");

    SelenideElement createSuccessMessage = $x("//*[text()[contains(., ' was created successfully!')]]");
    SelenideElement deleteSuccessMessage = $x("//*[text()[contains(., ' was successfully deleted')]]");

    @Step("Set Title from model")
    public void setTitle(TestCaseModel testCase){
        log.debug("Setting Title to: " + testCase.getTitle());
        titleInput.setValue(testCase.getTitle());
    }

    @Step("Set Description from model")
    public void setDescription(TestCaseModel testCase){
        log.debug("Setting Description to: " + testCase.getDescription());
        descriptionInput.setValue(testCase.getDescription());
    }

    @Step("Set Description from model")
    public void setDescription(String description){
        log.debug("Setting Description to: " + description);
        descriptionInput.setValue(description);
    }

    @Step("Set Status from model")
    public void setStatus(TestCaseModel testCase){
        log.debug("Setting Status to: " + testCase.getStatus());
        statusDropdown.sendKeys(testCase.getStatus());
        statusDropdown.sendKeys(Keys.ARROW_DOWN);
        statusDropdown.sendKeys(Keys.ENTER);
    }

    @Step("Set Suite from model")
    public void setSuite(TestCaseModel testCase){
        log.debug("Setting Suite to: " + testCase.getSuite());
        suiteDropdown.sendKeys(testCase.getSuite());
        suiteDropdown.sendKeys(Keys.ARROW_DOWN);
        suiteDropdown.sendKeys(Keys.ENTER);
    }

    @Step("Set Severity from model")
    public void setSeverity(TestCaseModel testCase){
        log.debug("Setting Severity to: " + testCase.getSeverity());
        severityDropdown.sendKeys(testCase.getSeverity());
        severityDropdown.sendKeys(Keys.ARROW_DOWN);
        severityDropdown.sendKeys(Keys.ENTER);
    }

    @Step("Set Priority from model")
    public void setPriority(TestCaseModel testCase){
        log.debug("Setting Priority to: " + testCase.getPriority());
        priorityDropdown.sendKeys(testCase.getPriority());
        priorityDropdown.sendKeys(Keys.ARROW_DOWN);
        priorityDropdown.sendKeys(Keys.ENTER);
    }

    @Step("Set Type from model")
    public void setType(TestCaseModel testCase){
        log.debug("Setting Title to: " + testCase.getType());
        typeDropdown.sendKeys(testCase.getType());
        typeDropdown.sendKeys(Keys.ARROW_DOWN);
        typeDropdown.sendKeys(Keys.ENTER);
    }

    @Step("Set Layer from model")
    public void setLayer(TestCaseModel testCase){
        log.debug("Setting Layer to: " + testCase.getLayer());
        layerDropdown.sendKeys(testCase.getLayer());
        layerDropdown.sendKeys(Keys.ARROW_DOWN);
        layerDropdown.sendKeys(Keys.ENTER);
    }

    @Step("Set IsFlaky from model")
    public void setIsFlaky(TestCaseModel testCase){
        log.debug("Setting IsFlaky to: " + testCase.getIsFlaky());
        isFlakyDropdown.sendKeys(testCase.getIsFlaky());
        isFlakyDropdown.sendKeys(Keys.ARROW_DOWN);
        isFlakyDropdown.sendKeys(Keys.ENTER);
    }

    @Step("Set Milestone from model")
    public void setMilestone(TestCaseModel testCase){
        log.debug("Setting Milestone to: " + testCase.getMilestone());
        milestoneDropdown.sendKeys(testCase.getMilestone());
        milestoneDropdown.sendKeys(Keys.ARROW_DOWN);
        milestoneDropdown.sendKeys(Keys.ENTER);
    }

    @Step("Set Behaviour from model")
    public void setBehaviour(TestCaseModel testCase){
        log.debug("Setting Behaviour to: " + testCase.getBehaviour());
        behaviourDropdown.sendKeys(testCase.getBehaviour());
        behaviourDropdown.sendKeys(Keys.ARROW_DOWN);
        behaviourDropdown.sendKeys(Keys.ENTER);
    }

    @Step("Set Automation Status from model")
    public void setAutomationStatus(TestCaseModel testCase){
        log.debug("Setting Automation Status to: " + testCase.getAutomationStatus());
        automationStatusDropdown.sendKeys(testCase.getAutomationStatus());
        automationStatusDropdown.sendKeys(Keys.ARROW_DOWN);
        automationStatusDropdown.sendKeys(Keys.ENTER);
    }

    @Step("Set Pre-conditions from model")
    public void setPrecondition(TestCaseModel testCase){
        log.debug("Setting Precondition to: " + testCase.getPreconditions());
        preconditionInput.setValue(testCase.getPreconditions());
    }

    @Step("Set Post-conditions from model")
    public void setPostcondition(TestCaseModel testCase){
        log.debug("Setting Postcondition to: " + testCase.getPostconditions());
        postconditionInput.setValue(testCase.getPostconditions());
    }

    @Step("Click add step button")
    public void clickAddStep(){
        log.debug("Clicking Add Step");
        addStepButton.click();
    }

    @Step("Set Step Action from model")
    public void setStepAction(TestCaseModel testCase){
        log.debug("Setting Step Action to: " + testCase.getTestStepOneName());
        stepActionInput.setValue(testCase.getTestStepOneName());
        stepActionInput.pressTab();
    }

    @Step("Set Step Data from model")
    public void setStepData(TestCaseModel testCase){
        log.debug("Setting Step Data to: " + testCase.getTestStepOneData());
        stepDataInput.sendKeys(testCase.getTestStepOneData());
        stepDataInput.pressTab();
    }

    @Step("Set Step Result from model")
    public void setStepResult(TestCaseModel testCase){
        log.debug("Setting Step Result to: " + testCase.getTestStepOneResult());
        stepResultInput.sendKeys(testCase.getTestStepOneResult());
    }

    @Step("Click Save button")
    public void clickSaveButton(){
        log.debug("Clicking Save button");
        saveCaseButton.click();
    }

    @Step("Click Create Test Case")
    public void clickCreateCase (){
        log.debug("Clicking Create Case button");
        createCaseButton.click();
    }

    @Step("Click Edit Test Case")
    public void clickEditCase(){
        log.debug("Clicking Edit Test Case button");
        editCaseButton.click();
    }

    @Step("Click Delete Case")
    public void clickDeleteCase(){
        log.debug("Clicking Delete button");
        deleteCaseButton.click();
    }

    @Step("Click Delete Case")
    public void clickConfirmDelete(){
        log.debug("Clicking Confirm Delete button");
        deleteConfirmButton.click();
    }

    @Step("Check Delete Success")
    public boolean checkDeleteSuccess(){
        log.debug("Checking if Delete Message appeared");
        return deleteSuccessMessage.exists();
    }

    @Step("Check Create Success")
    public boolean checkCreateSuccess(){
        log.debug("Checking if Create Message appeared");
        return createSuccessMessage.exists();
    }

    @Step("Open Qase Project")
    public void openQaseProject(){
        log.debug("Opening QaseProject page");
        projectQaseControl.click();
    }

    @Step("Open Test Case Repository")
    public void openRepository(){
        log.debug("Opening Repository page");
        repositoryPage.click();
    }

    @Step("Opening Test Case")
    public void openTestCase(String caseName){
        SelenideElement testCaseElem = $x(String.format("//*[text()='%s']",caseName));
        testCaseElem.click();
        log.debug("Clicked on: " + caseName);
    }

}
