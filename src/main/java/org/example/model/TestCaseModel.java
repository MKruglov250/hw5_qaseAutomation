package org.example.model;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCaseModel {
    @Expose
    private final String title;
    @Expose
    private final String description;
    @Expose
    private final String status;

    @Expose
    private final String suite;
    @Expose
    private final String severity;
    @Expose
    private final String priority;

    @Expose
    private final String type;
    @Expose
    private final String layer;
    @Expose
    private final String isFlaky;

    @Expose
    private final String milestone;
    @Expose
    private final String behaviour;
    @Expose
    private final String automationStatus;

    @Expose
    private final String preconditions;
    @Expose
    private final String postconditions;

    private final String testStepOneName;
    private final String testStepOneData;
    private final String testStepOneResult;

    private final String testStepTwoName;
    private final String testStepTwoData;
    private final String testStepTwoResult;
}