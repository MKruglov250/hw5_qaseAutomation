package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.TestCaseModel;
import org.example.utilities.TestCasePageUtils;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class TestCasePage {

    SelenideElement createCaseButton = $x("//a[@id='create-case-button']");
    SelenideElement firstTestCaseElement = $x("//*[text()='First Test Case']");
    SelenideElement secondTestCaseElement = $x("//*[text()='Second Test Case']");
    SelenideElement saveButton = $x("//*[text()='Save']").parent();
    SelenideElement editControl = $x("//a[contains(@href,'/case/QASEAPP/edit')]");
    SelenideElement deleteControl = editControl.sibling(1);
    SelenideElement deleteButton = $x("//*[text()='Delete']");
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
    public void openFirstTestCase(){
        firstTestCaseElement.click();
        log.debug("Clicked on Test Case 1");
    }

    @Step("Clicking Edit Control")
    public void editTestCase(){
        editControl.click();
        log.debug("Opened test case for Edit");
    }

    @Step("Clicking Delete Control")
    public void deleteTestCase(){
        deleteControl.click();
        log.debug("Deleting test case");
    }

    @Step("Confirm Deleting Test Case")
    public void confirmDeletion(){
        deleteButton.click();
        log.debug("Confirmed test case deletion");
    }

    @Step("Create Test Case")
    public void createTestCase(TestCaseModel caseModel){
        newTestCase();
        log.debug("Create New Test Case window opened");
        enterTestCaseData(caseModel);
        log.debug("Test Case Info Entered");
        saveTestCase();
        log.debug("Save button pressed");
        log.info("Test Case created");
    }

    @Step("Check First Test Case exists in Repository")
    public boolean checkFirstTestCaseExists(){
        log.debug("Checking First Test Case exists on Repository page");
        return firstTestCaseElement.exists();
    }

    @Step("Check that First Test Case is read")
    public boolean checkFirstTestcaseRead(){
        log.debug("Checking if First Test Case is read");
        openFirstTestCase();
        editTestCase();
        return (Objects.equals(TestCasePageUtils.titleInput.getValue(), "First Test Case"));
    }

    @Step("Check editing First Test Case")
    public String checkEditFirstTestCase(){
        openFirstTestCase();
        editTestCase();
        TestCasePageUtils.setDescription("Brand-new edited description");
        saveTestCase();
        editTestCase();
        return (TestCasePageUtils.descriptionInput.getText());
    }

    @Step("Check delete message")
    public boolean checkDeleteMessage(){
        log.debug("Getting Delete Success Message");
        return deleteSuccessMessage.exists();
    }

}
