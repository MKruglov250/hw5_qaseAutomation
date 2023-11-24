package org.example.pagesteps.steps;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;
import org.example.model.TestCaseModel;

@Log4j2
public class TestCasePageSteps extends BaseSteps {

    @Step("Open Qase Project")
    public void openQaseProject(){
        testCasePage.openQaseProject();
    }

    @Step("Create Test Case")
    public boolean createTestCase(TestCaseModel testCase){
        log.info("Creating Test Case from model: " + testCase.getTitle());
        openQaseProject();
        testCasePage.clickCreateCase();
        testCasePage.setTitle(testCase);
        testCasePage.setDescription(testCase);
        testCasePage.setStatus(testCase);
        testCasePage.setSuite(testCase);
        testCasePage.setSeverity(testCase);
        testCasePage.setPriority(testCase);
        testCasePage.setType(testCase);
        testCasePage.setLayer(testCase);
        testCasePage.setIsFlaky(testCase);
        testCasePage.setMilestone(testCase);
        testCasePage.setBehaviour(testCase);
        testCasePage.setAutomationStatus(testCase);
        testCasePage.setPrecondition(testCase);
        testCasePage.setPostcondition(testCase);
        testCasePage.clickAddStep();
        testCasePage.setStepAction(testCase);
        testCasePage.setStepData(testCase);
        testCasePage.setStepResult(testCase);
        testCasePage.clickSaveButton();
        return testCasePage.checkCreateSuccess();
    }

    @Step("Create Mock Test Case")
    public boolean createMockTestCase(TestCaseModel testCase){
        log.info("Creating Mock Case from model: " + testCase.getTitle());
        openQaseProject();
        testCasePage.clickCreateCase();
        testCasePage.setTitle(testCase);
        testCasePage.setDescription(testCase);
        testCasePage.clickAddStep();
        testCasePage.setStepAction(testCase);
        return testCasePage.checkCreateSuccess();
    }

    @Step("Read test case")
    public String checkTestCaseRead(String caseName){
        log.info("Reading Test Case: " + caseName);
        openQaseProject();
        testCasePage.openTestCase(caseName);
        testCasePage.clickEditCase();
        return testCasePage.titleInput.getValue();
    }

    @Step("Edit test case")
    public String checkEditTestCase(String caseName){
        log.info("Editing Test Case: " + caseName);
        openQaseProject();
        testCasePage.openTestCase(caseName);
        testCasePage.clickEditCase();
        testCasePage.setDescription("Brand-new edited description");
        testCasePage.clickSaveButton();
        testCasePage.clickEditCase();
        return (testCasePage.descriptionInput.getText());
    }

    @Step("Delete Test Case")
    public boolean deleteTestCase(String caseName){
        log.info("Deleting Test Case: " + caseName);
        openQaseProject();
        testCasePage.openTestCase(caseName);
        testCasePage.clickDeleteCase();
        testCasePage.clickConfirmDelete();
        return testCasePage.checkDeleteSuccess();
    }

}
