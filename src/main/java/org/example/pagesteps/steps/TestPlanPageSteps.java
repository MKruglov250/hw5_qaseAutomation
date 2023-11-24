package org.example.pagesteps.steps;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.TestPlanModel;

import static com.codeborne.selenide.Selenide.$x;


@Log4j2
public class TestPlanPageSteps extends BaseSteps {

    @Step("Open Test Plans Page")
    public void openTestPlans(){
        testPlanPage.openTestPlans();
    }

    @Step("Creating Test Plan")
    public boolean createTestPlan(TestPlanModel testPlanModel){
        log.info("Creating Test Plan: " + testPlanModel.getTitle());
        testPlanPage.clickCreateTestPlan();
        testPlanPage.setTitle(testPlanModel);
        testPlanPage.setDescription(testPlanModel);
        testPlanPage.clickAddTestCases();
        testPlanPage.selectSuite("First Suite");
        testPlanPage.selectTestCase("Case 1");
        testPlanPage.selectTestCase("Case 2");
        testPlanPage.clickDoneButton();
        testPlanPage.saveTestPlan();
        log.debug("Test plan creation complete: " + testPlanModel.getTitle());
        return testPlanPage.checkCreateSuccess();
    }

    @Step("Create e2e test plan")
    public boolean createEndtoendPlan(TestPlanModel testPlanModel){
        log.info("Creating Test Plan: " + testPlanModel.getTitle());
        testPlanPage.openTestPlans();
        testPlanPage.clickCreateTestPlan();
        testPlanPage.setTitle(testPlanModel);
        testPlanPage.setDescription(testPlanModel);
        testPlanPage.clickAddTestCases();
        testPlanPage.selectSuite("First Suite");
        testPlanPage.selectTestCase("First Test Case");
        testPlanPage.selectSuite("Second Suite");
        testPlanPage.selectTestCase("Second Test Case");
        testPlanPage.clickDoneButton();
        testPlanPage.saveTestPlan();
        log.debug("Test plan creation complete: " + testPlanModel.getTitle());
        return testPlanPage.checkCreateSuccess();
    }

    @Step("Read test plan")
    public String readTestPlan(String planName){
        log.info("Reading test plan: " + planName);
        SelenideElement testPlanControl = $x(String.format("//a[text()='%s']", planName));
        testPlanControl.click();
        return testPlanPage.getTestPlanDescription();
    }


    @Step("Edit Test Plan")
    public boolean editTestPlan(String planName){
        log.info("Editing test plan: " + planName);
        Faker faker = new Faker();
        SelenideElement testPlanControl = $x(String.format("//a[text()='%s']", planName));
        testPlanControl.click();
        testPlanPage.clickTrippledotButton();
        testPlanPage.clickEditButton();
        testPlanPage.setDescription(faker.food().ingredient());
        testPlanPage.saveTestPlan();
        return testPlanPage.checkEditSuccess();
    }

    @Step("Delete Test Plan")
    public boolean deleteTestPlan(String planName){
        log.info("Deleting test plan: " + planName);
        SelenideElement testPlanControl = $x(String.format("//a[text()='%s']", planName));
        testPlanControl.click();
        testPlanPage.clickTrippledotButton();
        testPlanPage.clickDeleteButton();
        testPlanPage.clickConfirmDelete();
        return testPlanPage.checkDeleteSuccess();
    }

}
