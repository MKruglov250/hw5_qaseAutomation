package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.TestPlanModel;
import org.example.utilities.TestPlanPageUtils;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class TestPlanPage {

    SelenideElement titleInput = $x("//input[@name='title']");
    SelenideElement descriptionInput = $x("//div[@contenteditable='true' " +
            "and @class='ProseMirror toastui-editor-contents']/p");
    SelenideElement testPlanDesc = $x("//p[text()='Simple description']");
    SelenideElement createSuccessMessage = $x("//*[text()='Test plan was created successfully!']");
    SelenideElement editSuccessMessage = $x("//*[text()='Test plan was edited successfully!']");
    SelenideElement deleteSuccessMessage = $x("//*[text()='Test plan Test Plan 1 was deleted successfully!']");

    public TestPlanPage() {
    }


    public void createMockTestCases(){
        log.info("Creating Mock Test Cases");
        TestPlanPageUtils.createMockTestCases();
    }

    @Step("Set test plan Title")
    public void setTitle(String title){
        titleInput.setValue(title);
    }

    @Step("Set test plan Description")
    public void setDescription(String description){
        descriptionInput.setValue(description);
    }

    @Step("Create test plan")
    public boolean createTestPlan(TestPlanModel testPlanModel){
        log.info("Creating test plan");
        TestPlanPageUtils.createTestPlan(testPlanModel);
        return createSuccessMessage.exists();
    }

    @Step("Read test plan")
    public String readTestPlan(String planName){
        SelenideElement testPlanControl = $x(String.format("//a[text()='%s']", planName));
        testPlanControl.click();
        return testPlanDesc.getText();
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
