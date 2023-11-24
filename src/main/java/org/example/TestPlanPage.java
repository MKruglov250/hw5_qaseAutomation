package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.TestPlanModel;
import org.example.utilities.TestPlanPageUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class TestPlanPage {

    SelenideElement titleInput = $x("//input[@name='title']");
    SelenideElement descriptionInput = $x("//div[@contenteditable='true' " +
            "and @class='ProseMirror toastui-editor-contents']/p");
    SelenideElement testPlanDesc = $x("(//p[text()='Description']/parent::div//p)[2]");
    SelenideElement createSuccessMessage = $x("//*[text()='Test plan was created successfully!']");
    SelenideElement editSuccessMessage = $x("//*[text()='Test plan was edited successfully!']");
    SelenideElement deleteSuccessMessage = $x("//*[text()='Test plan Test Plan 1 was deleted successfully!']");

    SelenideElement testCasesTab = $x("//button[@id='tab-test-cases']");
    SelenideElement testCaseDescription = $x("//label[@for='0-description']/parent::section//p");

    public TestPlanPage() {
    }


    public void createMockTestCases(){
        log.info("Creating Mock Test Cases");
        TestPlanPageUtils.createMockTestCases();
    }

    public void createRealTestCases() throws ParserConfigurationException, IOException, SAXException {
        log.info("Creating test cases with full data");
        TestPlanPageUtils.createRealTestCases();
    }

    @Step("Set test plan Title")
    public void setTitle(String title){
        titleInput.setValue(title);
    }

    @Step("Set test plan Description")
    public void setDescription(String description){
        descriptionInput.setValue(description);
    }

    @Step("Create unit test plan")
    public boolean createTestPlan(TestPlanModel testPlanModel){
        log.info("Creating test plan");
        TestPlanPageUtils.createTestPlan(testPlanModel);
        return createSuccessMessage.exists();
    }

    @Step("Create e2e test plan")
    public void createRealPlan(TestPlanModel testPlanModel){
        log.info("Creating test plan");
        open("plan/QASEAPP");
        TestPlanPageUtils.createRealPlan(testPlanModel);
    }

    @Step("Read test plan")
    public String readTestPlan(String planName){
        SelenideElement testPlanControl = $x(String.format("//a[text()='%s']", planName));
        testPlanControl.click();
        return testPlanDesc.getText();
    }

    @Step("Switch to Test Cases tab")
    public void switchToTestCases(){
        testCasesTab.click();
    }

    @Step("Check test case exists")
    public void openTestCase(String caseName){
        $x(String.format("//p[text()='%s']",caseName)).click();
    }

    @Step("Check Test Case Description")
    public String getTestCaseDescription(String caseName){
        openTestCase(caseName);
        return testCaseDescription.getText();
    }

    @Step("Edit test plan")
    public boolean editTestPlan(String planName){
        TestPlanPageUtils.editTestPlan(planName);
        return editSuccessMessage.exists();
    }

    @Step("Delete test plan")
    public boolean deleteTestPlan(String planName){
        TestPlanPageUtils.deleteTestPlan(planName);
        return deleteSuccessMessage.exists();
    }





}
