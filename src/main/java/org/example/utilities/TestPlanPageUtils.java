package org.example.utilities;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.TestCasePage;
import org.example.model.TestCaseModel;
import org.example.model.TestCaseModelBuilder;
import org.example.model.TestPlanModel;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class TestPlanPageUtils {

    static TestCasePage testCasePage = new TestCasePage();

    static SelenideElement createPlanButton = $x("//a[@id='createButton']");
    static SelenideElement savePlanButton = $x("//button[@id='save-plan']");
    static SelenideElement titleInput = $x("//input[@name='title']");
    static SelenideElement descriptionInput = $x("//div[@contenteditable='true' " +
            "and @class='ProseMirror toastui-editor-contents']/p");
    static SelenideElement addCasesButton = $x("//button[@id='edit-plan-add-cases-button']");
    SelenideElement addFilterButton = $x("//button[text()='Add filter']");
    SelenideElement searchInput = addCasesButton.preceding(0);
    static SelenideElement firstSuiteControl = $x("(//p[text()='First Suite'])[1]");
    SelenideElement secondSuiteControl = $x("(//p[text()='Second Suite'])[1]");
    static SelenideElement firstCaseCheckbox = $x("//div[@class='suitecase-info']/p[text()='Case 1']/parent::div/parent::div//label");
    static SelenideElement secondCaseCheckbox = $x("//div[@class='suitecase-info']/p[text()='Case 2']/parent::div/parent::div//label");
    static SelenideElement doneButton = $x("//span[text()='Done']/parent::button");

    static SelenideElement testPlanOneControl = $x("//a[text()='Test Plan 1']");
    SelenideElement testPlanOneDesc = $x("//p[text()='Simple description']");
    static SelenideElement trippledotButton = $x("//button[@class='btn btn-secondary']");
    static SelenideElement editButton = $x("//a[contains(@href,'/edit/')]");
    static SelenideElement deleteButton = $x("//a[@class='text-danger']");
    static SelenideElement confirmDeleteButton = $x("//span[text()='Delete plan']/parent::button");

    SelenideElement createSuccessMessage = $x("//*[text()='Test plan was created successfully!']");
    SelenideElement editSuccessMessage = $x("//*[text()='Test plan was edited successfully!']");
    SelenideElement deleteSuccessMessage = $x("//*[text()='Test plan Test Plan 1 was deleted successfully!']");

    @Step("Creating two mock test case models")
    public static void createMockTestCases(){
        TestCaseModel mockTestCaseOne = TestCaseModelBuilder.getTestCase();
        TestCaseModel mockTestCaseTwo = TestCaseModelBuilder.getTestCase();
        testCasePage.createMockTestCase(mockTestCaseOne);
        testCasePage.createMockTestCase(mockTestCaseTwo);
        log.info("Mock test case models created");
    }

    @Step("Creating Test Plan")
    public static void createTestPlan(TestPlanModel testPlanModel){
        createPlanButton.click();
        log.debug("Test plan creation form open");
        setTitle(testPlanModel.getTitle());
        setDescription(testPlanModel.getDescription());
        log.debug("Test plan name and description set");
        selectTestCases();
        savePlanButton.click();
        log.debug("Test plan saved");
    }

    @Step("Set test plan Title")
    public static void setTitle(String title){
        titleInput.setValue(title);
    }

    @Step("Set test plan Description")
    public static void setDescription(String description){
        descriptionInput.setValue(description);
    }

    @Step("Select Test Cases")
    public static void selectTestCases(){
        addCasesButton.click();
        firstSuiteControl.click();
        firstCaseCheckbox.click();
        secondCaseCheckbox.click();
        doneButton.click();
        log.debug("Test cases selected");
    }

    @Step("Edit Test Plan")
    public static void editTestPlan(String planName){
        Faker faker = new Faker();
        SelenideElement testPlanControl = $x(String.format("//a[text()='%s']", planName));
        testPlanControl.click();
        trippledotButton.click();
        editButton.click();
        setDescription(faker.food().ingredient());
        savePlanButton.click();
    }

    @Step("Delete Test Plan")
    public static void deleteTestPlan(String planName){
        SelenideElement testPlanControl = $x(String.format("//a[text()='%s']", planName));
        testPlanControl.click();
        trippledotButton.click();
        deleteButton.click();
        confirmDeleteButton.click();
        log.debug("Deleted test plan: " + planName);
    }





}
