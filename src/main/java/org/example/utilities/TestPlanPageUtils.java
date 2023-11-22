package org.example.utilities;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.TestCasePage;
import org.example.model.TestCaseModel;
import org.example.model.TestCaseModelBuilder;
import org.example.model.TestPlanModel;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class TestPlanPageUtils {

    static TestCasePage testCasePage = new TestCasePage();

    static TestCaseModel mockTestCaseOne;
    static TestCaseModel mockTestCaseTwo;

    static SelenideElement createPlanButton = $x("//a[@id='createButton']");
    static SelenideElement savePlanButton = $x("//button[@id='save-plan']");
    static SelenideElement titleInput = $x("//input[@name='title']");
    static SelenideElement descriptionInput = $x("//div[@contenteditable='true' " +
            "and @class='ProseMirror toastui-editor-contents']/p");
    static SelenideElement addCasesButton = $x("//button[@id='edit-plan-add-cases-button']");
    static SelenideElement doneButton = $x("//span[text()='Done']/parent::button");
    static SelenideElement trippledotButton = $x("//button[@class='btn btn-secondary']");
    static SelenideElement editButton = $x("//a[contains(@href,'/edit/')]");
    static SelenideElement deleteButton = $x("//a[@class='text-danger']");
    static SelenideElement confirmDeleteButton = $x("//span[text()='Delete plan']/parent::button");

    @Step("Creating two mock test case models")
    public static void createMockTestCases(){
        mockTestCaseOne = TestCaseModelBuilder.getTestCase();
        mockTestCaseTwo = TestCaseModelBuilder.getTestCase();
        log.debug("Creating mock test case models");
        testCasePage.createMockTestCase(mockTestCaseOne);
        testCasePage.createMockTestCase(mockTestCaseTwo);
        log.info("Mock test cases created");
    }

    @Step("Creating two real test cases for e2e scenario")
    public static void createRealTestCases() throws ParserConfigurationException, IOException, SAXException {
        var realTestCaseOne = TestCaseModelBuilder.getTestCase(1);
        var realTestCaseTwo = TestCaseModelBuilder.getTestCase(2);
        log.debug("Real test case models created");
        testCasePage.createTestCase(realTestCaseOne);
        testCasePage.createTestCase(realTestCaseTwo);
        log.info("Real test cases added");
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

    @Step("Create e2e test plan")
    public static void createRealPlan(TestPlanModel testPlanModel){
        createPlanButton.click();
        log.debug("Test plan creation form open");
        setTitle(testPlanModel.getTitle());
        setDescription(testPlanModel.getDescription());
        log.debug("Test plan name and description set");
        selectRealTestCases();
        savePlanButton.click();
        log.debug("Test plan e2e with real cases saved");
    }

    @Step("Click suite")
    public static void clickSuite(String suiteName){
        SelenideElement suiteControl = $x(String.format("(//p[text()='%s'])[1]",suiteName));
        suiteControl.click();
    }

    @Step("Click test case")
    public static void selectCase(String caseName){
        SelenideElement caseCheckbox = $x(String.format("//div[@class='suitecase-info']/p[text()='%s']" +
                        "/parent::div/parent::div//label",caseName));
        caseCheckbox.click();
    }

    @Step("Set test plan Title")
    public static void setTitle(String title){
        titleInput.setValue(title);
    }

    @Step("Set test plan Description")
    public static void setDescription(String description){
        descriptionInput.setValue(description);
    }

    @Step("Select Mock Test Cases")
    public static void selectTestCases(){
        addCasesButton.click();
        clickSuite("First Suite");
        selectCase("Case 1");
        selectCase("Case 2");
        doneButton.click();
        log.debug("Test cases selected");
    }

    @Step("Select real test cases")
    public static void selectRealTestCases(){
        addCasesButton.click();
        clickSuite("First Suite");
        selectCase("First Test Case");
        clickSuite("Second Suite");
        selectCase("Second Test Case");
        doneButton.click();
        log.debug("Real test cases selected");
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

    @Step("Deleting mock test case models")
    public static void deleteMockTestCases(){
        open("project/QASEAPP");
        testCasePage.deleteTestCase(mockTestCaseOne.getTitle());
        testCasePage.deleteTestCase(mockTestCaseTwo.getTitle());
        log.info("Mock test case models created");
    }





}
