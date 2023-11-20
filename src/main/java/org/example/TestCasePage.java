package org.example;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.TestCaseModel;
import org.example.model.TestCaseModelBuilder;
import org.openqa.selenium.Keys;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class TestCasePage {

    TestCaseModel firstCaseModel = TestCaseModelBuilder.getFirstTestCase();
    TestCaseModel secondCaseModel = TestCaseModelBuilder.getSecondTestCase();

    SelenideElement createCaseButton = $x("//a[@id='create-case-button']");
    SelenideElement firstTestCaseElement = $x("//*[text()='First Test Case']");
    SelenideElement secondTestCaseElement = $x("//*[text()='Second Test Case']");
    SelenideElement saveButton = $x("//*[text()='Save']").parent();
    SelenideElement addStep = $x("//*[text()=' Add step']").parent();
    ElementsCollection pageDropdowns = $$("input[aria-autocomplete='list']");
    SelenideElement statusElement = pageDropdowns.get(0);
    SelenideElement suiteElement = pageDropdowns.get(1);
    SelenideElement severityElement = pageDropdowns.get(2);
    SelenideElement priorityElement = pageDropdowns.get(3);
    SelenideElement typeElement = pageDropdowns.get(4);
    SelenideElement layerElement = pageDropdowns.get(5);
    SelenideElement isFlakyElement = pageDropdowns.get(6);
    SelenideElement milestoneElement = pageDropdowns.get(7);
    SelenideElement behaviourElement = pageDropdowns.get(8);
    SelenideElement automationStatusElement = pageDropdowns.get(9);
    SelenideElement titleInput = $x("//input[@name='title']");
    SelenideElement descriptionInput = $x("//label[@for='0-description']" +
                    "/parent::div//div[@class='ProseMirror toastui-editor-contents']/p");
    SelenideElement preconditionInput = $x("//label[@for='0-preconditions']" +
            "/parent::div//div[@class='ProseMirror toastui-editor-contents']/p");
    SelenideElement postconditionInput = $x("//label[@for='0-postconditions']" +
            "/parent::div//div[@class='ProseMirror toastui-editor-contents']/p");
    SelenideElement stepActionInput = $x("(//div[@class='case-create-block steps-block']" +
            "/following-sibling::div//p)[1]");
    SelenideElement stepDataInput = $x("(//div[@class='case-create-block steps-block']" +
            "/following-sibling::div//p)[2]");
    SelenideElement stepResultInput = $x("(//div[@class='case-create-block steps-block']" +
            "/following-sibling::div//p)[3]");
    SelenideElement editControl = $x("//a[contains(@href,'/case/QASEAPP/edit')]");
    SelenideElement deleteControl = editControl.sibling(1);
    SelenideElement deleteButton = $x("//*[text()='Delete']");
    SelenideElement deleteSuccessMessage = $x("//*[text()[contains(.,' was successfully deleted')]]");

    public TestCasePage() throws ParserConfigurationException, IOException, SAXException {
    }

    @Step("Opening Create Test Case page")
    public void newTestCase(){
        log.debug("Opening Create Test Case page...");
        createCaseButton.click();
    }

    @Step("Entering data in value and dropdown fields")
    public void enterTestCaseData(){
        setTitle();
        setDescription();
        setStatus();
        setSuite();
        setSeverity();
        setPriority();
        setType();
        setLayer();
        setIsFlaky();
        setMilestone();
        setBehaviour();
        setAutomationStatus();
        setPrecondition();
        setPostcondition();
        clickAddStep();
        setStepAction();
        setStepData();
        setStepResult();
    }

    @Step("Set Test Case Title")
    public void setTitle(){
        titleInput.setValue(firstCaseModel.getTitle());
        log.debug("Title set to: " + firstCaseModel.getTitle());
    }

    @Step("Set Test Case Status")
    public void setStatus(){
        statusElement.sendKeys(firstCaseModel.getStatus());
        statusElement.sendKeys(Keys.ARROW_DOWN);
        statusElement.sendKeys(Keys.ENTER);
        log.debug("Status set to: " + firstCaseModel.getStatus());
    }

    @Step("Set Test Case Description")
    public void setDescription(){
        descriptionInput.setValue(firstCaseModel.getDescription());
        log.debug("Description set to: " + firstCaseModel.getDescription());
    }

    @Step("Set Test Case Description")
    public void setDescription(String description){
        descriptionInput.setValue(description);
        log.debug("Description set to: " + description);
    }

    @Step("Set Test Case Suite")
    public void setSuite(){
        suiteElement.sendKeys(firstCaseModel.getSuite());
        suiteElement.sendKeys(Keys.ARROW_DOWN);
        suiteElement.sendKeys(Keys.ENTER);
        log.debug("Suite set to: " + firstCaseModel.getSuite());
    }

    @Step("Set Test Case Severity")
    public void setSeverity(){
        severityElement.sendKeys(firstCaseModel.getSeverity());
        severityElement.sendKeys(Keys.ARROW_DOWN);
        severityElement.sendKeys(Keys.ENTER);
        log.debug("Severity set to: " + firstCaseModel.getSeverity());
    }

    @Step("Set Test Case Priority")
    public void setPriority(){
        priorityElement.sendKeys(firstCaseModel.getPriority());
        priorityElement.sendKeys(Keys.ARROW_DOWN);
        priorityElement.sendKeys(Keys.ENTER);
        log.debug("Priority set to: " + firstCaseModel.getPriority());
    }

    @Step("Set Test Case Type")
    public void setType(){
        typeElement.sendKeys(firstCaseModel.getType());
        typeElement.sendKeys(Keys.ARROW_DOWN);
        typeElement.sendKeys(Keys.ENTER);
        log.debug("Type set to: " + firstCaseModel.getType());
    }

    @Step("Set Test Case Layer")
    public void setLayer(){
        layerElement.sendKeys(firstCaseModel.getLayer());
        layerElement.sendKeys(Keys.ARROW_DOWN);
        layerElement.sendKeys(Keys.ENTER);
        log.debug("Layer set to: " + firstCaseModel.getLayer());
    }

    @Step("Set Test Case IsFlaky")
    public void setIsFlaky(){
        isFlakyElement.sendKeys(firstCaseModel.getIsFlaky());
        isFlakyElement.sendKeys(Keys.ARROW_DOWN);
        isFlakyElement.sendKeys(Keys.ENTER);
        log.debug("IsFlaky field set to: " + firstCaseModel.getIsFlaky());
    }

    @Step("Set Test Case Milestone")
    public void setMilestone(){
        milestoneElement.sendKeys(firstCaseModel.getMilestone());
        milestoneElement.sendKeys(Keys.ARROW_DOWN);
        milestoneElement.sendKeys(Keys.ENTER);
        log.debug("Milestone set to: " + firstCaseModel.getMilestone());
    }

    @Step("Set Test Case Behaviour")
    public void setBehaviour(){
        behaviourElement.sendKeys(firstCaseModel.getBehaviour());
        behaviourElement.sendKeys(Keys.ARROW_DOWN);
        behaviourElement.sendKeys(Keys.ENTER);
        log.debug("Behaviour set to: " + firstCaseModel.getBehaviour());
    }

    @Step("Set Test Case Automation Status")
    public void setAutomationStatus(){
        automationStatusElement.sendKeys(firstCaseModel.getAutomationStatus());
        automationStatusElement.sendKeys(Keys.ARROW_DOWN);
        automationStatusElement.sendKeys(Keys.ENTER);
        log.debug("Automation Status set to: " + firstCaseModel.getAutomationStatus());
    }

    @Step("Set Test Case Preconditions")
    public void setPrecondition(){
        preconditionInput.setValue(firstCaseModel.getPreconditions());
        log.debug("Pre-conditions set to: " + firstCaseModel.getPreconditions());
    }

    @Step("Set Test Case Postconditions")
    public void setPostcondition(){
        postconditionInput.setValue(firstCaseModel.getPostconditions());
        log.debug("Post-conditions set to: " + firstCaseModel.getPostconditions());
    }

    @Step("Add new Test Case Step")
    public void clickAddStep(){
        addStep.click();
        log.debug("Added new step");
    }

    @Step("Set Step action")
    public void setStepAction(){
        stepActionInput.setValue(firstCaseModel.getTestStepOneName());
        log.debug("Step action set to: " + firstCaseModel.getTestStepOneName());
    }

    @Step("Set Step Data")
    public void setStepData(){
        stepDataInput.setValue(firstCaseModel.getTestStepOneData());
        log.debug("Step data set to: " + firstCaseModel.getTestStepOneData());
    }

    @Step("Set Step Result")
    public void setStepResult(){
        stepResultInput.setValue(firstCaseModel.getTestStepOneResult());
        log.debug("Step result to: " + firstCaseModel.getTestStepOneResult());
    }

    @Step("Saving test case with created data")
    public void saveTestCase(){
        saveButton.click();
        log.debug("Saving Test Case Data - God bless us all!");
    }

    @Step("Opening Test Case")
    public void openTestCase(){
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

    @Step("Check delete message")
    public boolean checkDeleteMessage(){
        log.debug("Getting Delete Success Message");
        return deleteSuccessMessage.exists();
    }

}
