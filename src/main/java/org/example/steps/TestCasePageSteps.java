package org.example.steps;

import lombok.extern.log4j.Log4j2;
import io.qameta.allure.Step;
import org.example.model.TestCaseModel;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class TestCasePageSteps extends BaseSteps {

    @Step("Open Qase Project")
    public void openQaseProject(){
        testCasePage.openQaseProject();
    }

    @Step("Open Repository")
    public void openRepository(){
        testCasePage.openRepository();
    }

    @Step("Create Test Case")
    public boolean createTestCase(TestCaseModel testCase){
        log.info("Creating Test Case from model: " + testCase.getTitle());
        openRepository();
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


    @Step("Read test case and get name")
    public String checkTestCaseRead(String caseName){
        log.info("Reading Test Case: " + caseName);
        openRepository();
        testCasePage.openTestCase(caseName);
        testCasePage.clickEditCase();
        return testCasePage.titleInput.getValue();
    }


    @Step("Read test case and get CaseID")
    public int checkTestCaseReadDeep(String caseName){
        log.info("Reading Test Case: " + caseName);
        openRepository();
        testCasePage.openTestCase(caseName);
        testCasePage.clickEditCase();
        return Integer.parseInt(getWebDriver().getCurrentUrl()
                .replace("https://app.qase.io/case/QASEAPP/edit/",""));
    }

    @Step("Get Test Case ID")
    public int getTestCaseId(TestCaseModel model){
        log.info("Getting id from case: " + model.getTitle());
        openRepository();
        testCasePage.openTestCase(model.getTitle());
        testCasePage.clickEditCase();
        int testCaseId = Integer.parseInt(getWebDriver().getCurrentUrl()
                .replace("https://app.qase.io/case/QASEAPP/edit/",""));
        openRepository();
        return testCaseId;
    }

    @Step("Edit test case")
    public String checkEditTestCase(String caseName){
        log.info("Editing Test Case: " + caseName);
        openRepository();
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
        openRepository();
        testCasePage.openTestCase(caseName);
        testCasePage.clickDeleteCase();
        testCasePage.clickConfirmDelete();
        return testCasePage.checkDeleteSuccess();
    }

}
