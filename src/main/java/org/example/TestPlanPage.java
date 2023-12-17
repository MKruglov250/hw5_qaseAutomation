package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.TestPlanModel;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class TestPlanPage {

    SelenideElement titleInput = $x("//input[@name='title']");
    SelenideElement descriptionInput = $x("//div[@contenteditable='true' " +
            "and @class='ProseMirror toastui-editor-contents']/p");
    SelenideElement testPlanDesc = $x("(//p[text()='Description']/parent::div//p)[2]");

    SelenideElement createSuccessMessage = $x("//*[text()='Test plan was created successfully!']");
    SelenideElement editSuccessMessage = $x("//*[text()='Test plan was edited successfully!']");
    SelenideElement deleteSuccessMessage = $x("//*[text()[contains(., 'was deleted successfully!')]]");

    SelenideElement testCasesTab = $x("//button[@id='tab-test-cases']");
    SelenideElement testCaseDescription = $x("//label[@for='0-description']/parent::section//p");

    SelenideElement createPlanButton = $x("//a[@id='createButton']");
    SelenideElement savePlanButton = $x("//button[@id='save-plan']");
    SelenideElement addCasesButton = $x("//button[@id='edit-plan-add-cases-button']");
    SelenideElement doneButton = $x("//span[text()='Done']/parent::button");
    SelenideElement trippledotButton = $x("//button[@class='btn btn-secondary']");
    SelenideElement editButton = $x("//a[contains(@href,'/edit/')]");
    SelenideElement deleteButton = $x("//a[@class='text-danger']");
    SelenideElement confirmDeleteButton = $x("//span[text()='Delete plan']/parent::button");

    SelenideElement testPlansPage = $x("//span[text()='Test Plans']/parent::a");

    @Step("Open Test Plans Page")
    public void openTestPlans(){
        log.debug("Open Test Plans page");
        testPlansPage.click();
    }

    @Step("Click Create Test Plan")
    public void clickCreateTestPlan() {
        log.debug("Click Create Test Plan");
        createPlanButton.click();
    }


    @Step("Set Test Plan Title")
    public void setTitle(TestPlanModel testPlan){
        log.debug("Setting Title to: " + testPlan.getTitle());
        titleInput.setValue(testPlan.getTitle());
    }

    @Step("Set Test Plan Description from Model")
    public void setDescription(TestPlanModel testPlan){
        log.debug("Setting Description to: " + testPlan.getDescription());
        descriptionInput.setValue(testPlan.getDescription());
    }

    @Step("Set Test Plan Description from String")
    public void setDescription(String description){
        log.debug("Setting descriotion to: " + description);
        descriptionInput.setValue(description);
    }

    @Step("Open Add Test Cases window")
    public void clickAddTestCases(){
        log.debug("Click on Add Cases Button");
        addCasesButton.click();
    }

    @Step("Select suite")
    public void selectSuite(String suiteName){
        log.debug("Select suite: " + suiteName);
        SelenideElement suiteControl = $x(String.format("(//p[text()='%s'])[1]",suiteName));
        suiteControl.click();
    }

    @Step("Select Test Case")
    public void selectTestCase(String caseName){
        SelenideElement caseCheckbox = $x(String.format("//p[text()='%s']" +
                "/parent::div/parent::div//input[@type='checkbox']/parent::span",caseName));
        caseCheckbox.click();
    }

    @Step("Click Done Button")
    public void clickDoneButton() {
        log.debug("Click Done Button");
        doneButton.click();
    }

    @Step("Click Save Test Plan")
    public void saveTestPlan(){
        log.debug("Click Create Plan (Save) button");
        savePlanButton.click();
    }



    @Step("Check Create Success")
    public boolean checkCreateSuccess(){
        log.debug("Check if Create Success Message appeared");
        return createSuccessMessage.exists();
    }

    @Step("Check Edit Success")
    public boolean checkEditSuccess(){
        log.debug("Check if Create Success Message appeared");
        return editSuccessMessage.exists();
    }

    @Step("Check Create Success")
    public boolean checkDeleteSuccess(){
        log.debug("Check if Create Success Message appeared");
        return deleteSuccessMessage.exists();
    }

    @Step("Click '...' button ")
    public void clickTrippledotButton(){
        log.debug("Click '...' button");
        trippledotButton.click();
    }

    @Step("Click Edit button")
    public void clickEditButton(){
        log.debug("Click Edit Button");
        editButton.click();
    }

    @Step("Click Delete Button")
    public void clickDeleteButton(){
        log.debug("Click Delete button");
        deleteButton.click();
    }

    @Step("Click Confirm Delete Button")
    public void clickConfirmDelete(){
        log.debug("Click Confirm Delete button");
        confirmDeleteButton.click();
    }

    @Step("Get Test Plan Description")
    public String getTestPlanDescription(){
        log.debug("Getting test plan description: " + testPlanDesc.getText());
        return testPlanDesc.getText();
    }

    @Step("Switch to Test Cases Tab")
    public void switchToTestCases(){
        log.debug("Switch to Test Cases tab");
        testCasesTab.click();
    }

    @Step("Check test case exists")
    public void openTestCase(String caseName){
        log.debug("Opening Test Case");
        $x(String.format("//p[text()='%s']",caseName)).click();
    }

    @Step("Switch to Test Cases Tab")
    public String getTestCaseDescription(String caseName){
        log.debug("Getting description for selected test case");
        openTestCase(caseName);
        return testCaseDescription.getText();
    }




}
