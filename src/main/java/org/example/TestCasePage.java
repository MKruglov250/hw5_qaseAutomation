package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.TestCaseModel;
import org.example.utilities.TestCasePageUtils;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class TestCasePage {

    SelenideElement createCaseButton = $x("//a[@id='create-case-button']");
    SelenideElement saveButton = $x("//*[text()='Save']").parent();
    SelenideElement deleteSuccessMessage = $x("//*[text()[contains(.,' was successfully deleted')]]");

    public TestCasePage(){
    }

    @Step("Create Mock Test Case")
    public void createMockTestCase(TestCaseModel mockCase){
        log.debug("Creating Test Case from model " + mockCase.getTitle());
        TestCasePageUtils.createMockCase(mockCase);
        saveTestCase();
    }

    @Step("Opening Create Test Case page")
    public void newTestCase(){
        log.debug("Opening Create Test Case page...");
        createCaseButton.click();
    }

    @Step("Entering data in value and dropdown fields")
    public void enterTestCaseData(TestCaseModel caseModel){
        TestCasePageUtils.enterTestCaseData(caseModel);
        log.info("Test Case data set");
    }

    @Step("Saving test case with created data")
    public void saveTestCase(){
        saveButton.click();
        log.debug("Saving Test Case Data - God bless us all!");
    }


    @Step("Opening Test Case")
    public void openTestCase(String caseName){
        SelenideElement testCaseElem = $x(String.format("//*[text()='%s']",caseName));
        testCaseElem.click();
        log.debug("Clicked on: " + caseName);
    }

    @Step("Create Test Case")
    public void createTestCase(TestCaseModel caseModel){
        log.info("Creating test case from model: " + caseModel.getTitle());
        TestCasePageUtils.createTestCase(caseModel);
        log.info("Test Case created");
    }

    @Step("Check First Test Case exists in Repository")
    public boolean checkTestCaseExists(String caseName){
        log.debug("Checking Test Case exists in repository: " + caseName);
        SelenideElement testCaseElem = $x(String.format("//*[text()='%s']",caseName));
        return testCaseElem.exists();
    }

    @Step("Check Reading test case")
    public String checkTestCaseRead(String caseName){
        log.debug("Reading Test Case " + caseName);
        openTestCase(caseName);
        TestCasePageUtils.editTestCase();
        return TestCasePageUtils.titleInput.getValue();
    }

    @Step("Check Editing test case")
    public String checkEditTestCase(String caseName){
        log.debug("Editing Test Case " + caseName);
        openTestCase(caseName);
        TestCasePageUtils.setNewDescription();
        return (TestCasePageUtils.descriptionInput.getText());
    }


    @Step("Delete test case")
    public void deleteTestCase(String caseName){
        openTestCase(caseName);
        TestCasePageUtils.deleteCase();
        TestCasePageUtils.confirmDeletion();
    }

    @Step("Check Delete message exists")
    public boolean checkDeleteMessage(){
        log.debug("Getting Delete Success Message");
        return deleteSuccessMessage.exists();
    }


}
