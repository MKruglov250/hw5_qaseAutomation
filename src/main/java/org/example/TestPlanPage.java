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

    static TestPlanModel testPlanModel;
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
    SelenideElement firstCaseCheckbox = $x("//div[@class='suitecase-info']/p[text()='Case 1']/parent::div/parent::div//input");
    SelenideElement secondCaseCheckbox = $x("//div[@class='suitecase-info']/p[text()='Case 2']/parent::div/parent::div//input");
    SelenideElement doneButton = $x("//span[text()='Done']/parent::button");

    public TestPlanPage() throws ParserConfigurationException, IOException, SAXException {
    }


    @Step("Creating Test Plan Model")
    public void createTestPlanModel(String name, String description){
        testPlanModel = TestPlanModelBuilder.createTestPlan(name, description);
        log.info("Created Test Plan Model, name = " + name + "; description = " + description);
    }

    @Step("Creating two mock Test Cases Models")
    public void createMockTestCaseModels(){
        log.info("Creating two Mock Test Cases");
        mockTestCaseOne = TestCaseModelBuilder.getTestCase();
        mockTestCaseTwo = TestCaseModelBuilder.getTestCase();
        testCasePage.createMockTestCase(mockTestCaseOne);
        testCasePage.createMockTestCase(mockTestCaseTwo);
        log.info("Mock test cases created");
    }

    @Step("Adding two mock Test Cases to Qase repository")
    public void addMockTestCases(){

    }

    @Step("Creating New Test Plan")
    public void createNewTestPlan(){

    }




}
