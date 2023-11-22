package org.example.utilities;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.TestCaseModel;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;


@Log4j2
public class TestCasePageUtils {

    static SelenideElement createCaseButton = $x("//a[@id='create-case-button']");
    SelenideElement firstTestCaseElement = $x("//*[text()='First Test Case']");
    SelenideElement secondTestCaseElement = $x("//*[text()='Second Test Case']");
    SelenideElement saveButton = $x("//*[text()='Save']").parent();
    static SelenideElement addStep = $x("//*[text()=' Add step']").parent();
    static ElementsCollection pageDropdowns = $$("input[aria-autocomplete='list']");
    static SelenideElement statusElement = pageDropdowns.get(0);
    static SelenideElement suiteElement = pageDropdowns.get(1);
    static SelenideElement severityElement = pageDropdowns.get(2);
    static SelenideElement priorityElement = pageDropdowns.get(3);
    static SelenideElement typeElement = pageDropdowns.get(4);
    static SelenideElement layerElement = pageDropdowns.get(5);
    static SelenideElement isFlakyElement = pageDropdowns.get(6);
    static SelenideElement milestoneElement = pageDropdowns.get(7);
    static SelenideElement behaviourElement = pageDropdowns.get(8);
    static SelenideElement automationStatusElement = pageDropdowns.get(9);
    public static SelenideElement titleInput = $x("//input[@name='title']");
    public static SelenideElement descriptionInput = $x("//label[@for='0-description']" +
            "/parent::div//div[@class='ProseMirror toastui-editor-contents']/p");
    static SelenideElement preconditionInput = $x("//label[@for='0-preconditions']" +
            "/parent::div//div[@class='ProseMirror toastui-editor-contents']/p");
    static SelenideElement postconditionInput = $x("//label[@for='0-postconditions']" +
            "/parent::div//div[@class='ProseMirror toastui-editor-contents']/p");
    static SelenideElement stepActionInput = $x("(//div[@class='case-create-block steps-block']" +
            "/following-sibling::div//p)[1]");
    static SelenideElement stepDataInput = $x("(//div[@class='case-create-block steps-block']" +
            "/following-sibling::div//p)[2]");
    static SelenideElement stepResultInput = $x("(//div[@class='case-create-block steps-block']" +
            "/following-sibling::div//p)[3]");
    SelenideElement editControl = $x("//a[contains(@href,'/case/QASEAPP/edit')]");
    SelenideElement deleteControl = editControl.sibling(1);
    SelenideElement deleteButton = $x("//*[text()='Delete']");
    SelenideElement deleteSuccessMessage = $x("//*[text()[contains(.,' was successfully deleted')]]");

    public TestCasePageUtils(){
    }


    @Step("Entering data in value and dropdown fields")
    public static void enterTestCaseData(TestCaseModel caseModel){
        setTitle(caseModel.getTitle());
        setDescription(caseModel.getDescription());
        setStatus(caseModel.getStatus());
        setSuite(caseModel.getSuite());
        setSeverity(caseModel.getSeverity());
        setPriority(caseModel.getPriority());
        setType(caseModel.getType());
        setLayer(caseModel.getLayer());
        setIsFlaky(caseModel.getIsFlaky());
        setMilestone(caseModel.getMilestone());
        setBehaviour(caseModel.getBehaviour());
        setAutomationStatus(caseModel.getAutomationStatus());
        setPrecondition(caseModel.getPreconditions());
        setPostcondition(caseModel.getPostconditions());
        clickAddStep();
        setStepAction(caseModel.getTestStepOneName());
        setStepData(caseModel.getTestStepOneData());
        setStepResult(caseModel.getTestStepOneResult());
    }

    @Step("Creating Test Case from mock data")
    public static void createMockCase(TestCaseModel mockCase){
        open("project/QASEAPP");
        createCaseButton.click();
        setTitle(mockCase.getTitle());
        setSuite(mockCase.getSuite());
        clickAddStep();
        setStepAction(mockCase.getTestStepOneName());
    }

    @Step("Set Test Case Title")
    public static void setTitle(String title){
        titleInput.setValue(title);
        log.debug("Title set to: " + title);
    }

    @Step("Set Test Case Status")
    public static void setStatus(String status){
        statusElement.sendKeys(status);
        statusElement.sendKeys(Keys.ARROW_DOWN);
        statusElement.sendKeys(Keys.ENTER);
        log.debug("Status set to: " + status);
    }

    @Step("Set Test Case Description")
    public static void setDescription(String description){
        descriptionInput.setValue(description);
        log.debug("Description set to: " + description);
    }

    @Step("Set Test Case Suite")
    public static void setSuite(String suite){
        suiteElement.sendKeys(suite);
        suiteElement.sendKeys(Keys.ARROW_DOWN);
        suiteElement.sendKeys(Keys.ENTER);
        log.debug("Suite set to: " + suite);
    }

    @Step("Set Test Case Severity")
    public static void setSeverity(String severity){
        severityElement.sendKeys(severity);
        severityElement.sendKeys(Keys.ARROW_DOWN);
        severityElement.sendKeys(Keys.ENTER);
        log.debug("Severity set to: " + severity);
    }

    @Step("Set Test Case Priority")
    public static void setPriority(String priority){
        priorityElement.sendKeys(priority);
        priorityElement.sendKeys(Keys.ARROW_DOWN);
        priorityElement.sendKeys(Keys.ENTER);
        log.debug("Priority set to: " + priority);
    }

    @Step("Set Test Case Type")
    public static void setType(String type){
        typeElement.sendKeys(type);
        typeElement.sendKeys(Keys.ARROW_DOWN);
        typeElement.sendKeys(Keys.ENTER);
        log.debug("Type set to: " + type);
    }

    @Step("Set Test Case Layer")
    public static void setLayer(String layer){
        layerElement.sendKeys(layer);
        layerElement.sendKeys(Keys.ARROW_DOWN);
        layerElement.sendKeys(Keys.ENTER);
        log.debug("Layer set to: " + layer);
    }

    @Step("Set Test Case IsFlaky")
    public static void setIsFlaky(String isFlaky){
        isFlakyElement.sendKeys(isFlaky);
        isFlakyElement.sendKeys(Keys.ARROW_DOWN);
        isFlakyElement.sendKeys(Keys.ENTER);
        log.debug("IsFlaky field set to: " + isFlaky);
    }

    @Step("Set Test Case Milestone")
    public static void setMilestone(String milestone){
        milestoneElement.sendKeys(milestone);
        milestoneElement.sendKeys(Keys.ARROW_DOWN);
        milestoneElement.sendKeys(Keys.ENTER);
        log.debug("Milestone set to: " + milestone);
    }

    @Step("Set Test Case Behaviour")
    public static void setBehaviour(String behaviour){
        behaviourElement.sendKeys(behaviour);
        behaviourElement.sendKeys(Keys.ARROW_DOWN);
        behaviourElement.sendKeys(Keys.ENTER);
        log.debug("Behaviour set to: " + behaviour);
    }

    @Step("Set Test Case Automation Status")
    public static void setAutomationStatus(String automationStatus){
        automationStatusElement.sendKeys(automationStatus);
        automationStatusElement.sendKeys(Keys.ARROW_DOWN);
        automationStatusElement.sendKeys(Keys.ENTER);
        log.debug("Automation Status set to: " + automationStatus);
    }

    @Step("Set Test Case Preconditions")
    public static void setPrecondition(String precondition){
        preconditionInput.setValue(precondition);
        log.debug("Pre-conditions set to: " + precondition);
    }

    @Step("Set Test Case Postconditions")
    public static void setPostcondition(String postcondition){
        postconditionInput.setValue(postcondition);
        log.debug("Post-conditions set to: " + postcondition);
    }

    @Step("Add new Test Case Step")
    public static void clickAddStep(){
        addStep.click();
        log.debug("Added new step");
    }

    @Step("Set Step action")
    public static void setStepAction(String stepAction){
        stepActionInput.setValue(stepAction);
        log.debug("Step action set to: " + stepAction);
    }

    @Step("Set Step Data")
    public static void setStepData(String stepData){
        stepDataInput.setValue(stepData);
        log.debug("Step data set to: " + stepData);
    }

    @Step("Set Step Result")
    public static void setStepResult(String stepResult){
        stepResultInput.setValue(stepResult);
        log.debug("Step result to: " + stepResult);
    }
}
