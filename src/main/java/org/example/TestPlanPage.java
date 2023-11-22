package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.TestCaseModel;
import org.example.model.TestCaseModelBuilder;
import org.example.model.TestPlanModel;
import org.example.model.TestPlanModelBuilder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class TestPlanPage {

    static TestPlanModel testPlanModel = TestPlanModelBuilder
            .getTestPlan("Test Plan 1", "Simple description");
    static TestCaseModel mockTestCaseOne;
    static TestCaseModel mockTestCaseTwo;

    TestCasePage testCasePage = new TestCasePage();

    SelenideElement createPlanButton = $x("//a[@id='createButton']");
    SelenideElement savePlanButton = $x("//button[@id='save-plan']");
    SelenideElement titleInput = $x("//input[@name='title']");
    SelenideElement descriptionInput = $x("//div[@contenteditable='true' " +
            "and @class='ProseMirror toastui-editor-contents']/p");
    SelenideElement addCasesButton = $x("//button[@id='edit-plan-add-cases-button']");
    SelenideElement addFilterButton = $x("//button[text()='Add filter']");
    SelenideElement searchInput = addCasesButton.preceding(0);
    SelenideElement firstSuiteControl = $x("(//p[text()='First Suite'])[1]");
    SelenideElement secondSuiteControl = $x("(//p[text()='Second Suite'])[1]");
    SelenideElement firstCaseCheckbox = $x("//div[@class='suitecase-info']/p[text()='Case 1']/parent::div/parent::div//label");
    SelenideElement secondCaseCheckbox = $x("//div[@class='suitecase-info']/p[text()='Case 2']/parent::div/parent::div//label");
    SelenideElement doneButton = $x("//span[text()='Done']/parent::button");

    SelenideElement testPlanOneControl = $x("//a[text()='Test Plan 1']");
    SelenideElement testPlanOneDesc = $x("//p[text()='Simple description']");
    SelenideElement trippledotButton = $x("//button[@class='btn btn-secondary']");
    SelenideElement editButton = $x("//a[contains(@href,'/edit/')]");
    SelenideElement deleteButton = $x("//a[@class='text-danger']");
    SelenideElement confirmDeleteButton = $x("//span[text()='Delete plan']/parent::button");

    SelenideElement createSuccessMessage = $x("//*[text()='Test plan was created successfully!']");
    SelenideElement editSuccessMessage = $x("//*[text()='Test plan was edited successfully!']");
    SelenideElement deleteSuccessMessage = $x("//*[text()='Test plan Test Plan 1 was deleted successfully!']");

    public TestPlanPage() throws ParserConfigurationException, IOException, SAXException {
    }

    @Step("Creating two mock Test Cases in repository")
    public void createMockTestCases(){
        log.info("Creating two Mock Test Case Models");
        mockTestCaseOne = TestCaseModelBuilder.getTestCase();
        mockTestCaseTwo = TestCaseModelBuilder.getTestCase();
        testCasePage.createMockTestCase(mockTestCaseOne);
        testCasePage.createMockTestCase(mockTestCaseTwo);
        log.info("Mock test case models created");
    }

    @Step("Set test plan Title")
    public void setTitle(String title){
        titleInput.setValue(title);
    }

    @Step("Set test plan Description")
    public void setDescription(String description){
        descriptionInput.setValue(description);
    }

    @Step("Select Test Cases")
    public void selectTestCases(){
        addCasesButton.click();
        firstSuiteControl.click();
        firstCaseCheckbox.click();
        secondCaseCheckbox.click();
        doneButton.click();
        log.debug("Test cases selected");
    }

    @Step("Create test plan")
    public boolean createTestPlan(){
        createPlanButton.click();
        log.debug("Test plan creation form open");
        setTitle(testPlanModel.getTitle());
        setDescription(testPlanModel.getDescription());
        log.debug("Test plan name and description set");
        selectTestCases();
        savePlanButton.click();
        log.debug("Test plan saved");
        return createSuccessMessage.exists();
    }

    @Step("Read test plan")
    public boolean readTestPlan(){
        testPlanOneControl.click();
        return testPlanOneDesc.getText().equals("Simple description");
    }

    @Step("Edit test plan")
    public boolean editTestPlan(){
        testPlanOneControl.click();
        trippledotButton.click();
        editButton.click();
        setDescription("Edited Description");
        savePlanButton.click();
        return editSuccessMessage.exists();
    }

    @Step("Delete test plan")
    public boolean deleteTestPlan(){
        testPlanOneControl.click();
        trippledotButton.click();
        deleteButton.click();
        confirmDeleteButton.click();
        return deleteSuccessMessage.exists();
    }





}
