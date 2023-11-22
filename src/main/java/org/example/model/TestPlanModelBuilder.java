package org.example.model;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestPlanModelBuilder {

    public static TestPlanModel getTestPlan(String testPlanName, String testPlanDesc) {
        log.info("Building Test case model");
        return new TestPlanModel.TestPlanModelBuilder()
                .title(testPlanName)
                .description(testPlanDesc)
                .build();
    }
}
