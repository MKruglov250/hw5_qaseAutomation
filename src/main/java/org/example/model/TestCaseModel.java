package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCaseModel {
    private final String title;
    private final String description;
    private final String status;

    private final String suite;
    private final String severity;
    private final String priority;

    private final String type;
    private final String layer;
    private final String isFlaky;

    private final String milestone;
    private final String behaviour;
    private final String automationStatus;

    private final String preconditions;
    private final String postconditions;

    private final String testStepOneName;
    private final String testStepOneData;
    private final String testStepOneResult;

    private final String testStepTwoName;
    private final String testStepTwoData;
    private final String testStepTwoResult;
}