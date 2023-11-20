package org.example.model;

import org.example.utilities.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TestCaseModelBuilder {

    public static TestCaseModel getFirstTestCase() throws ParserConfigurationException, IOException, SAXException {
        return new TestCaseModel.TestCaseModelBuilder()
                .title(XMLParser.getCaseAttributeValue(1,"title"))
                .description(XMLParser.getCaseAttributeValue(1,"description"))
                .status(XMLParser.getCaseAttributeValue(1, "statusDropdown"))
                .suite(XMLParser.getCaseAttributeValue(1, "suiteDropdown"))
                .severity(XMLParser.getCaseAttributeValue(1, "severityDropdown"))
                .priority(XMLParser.getCaseAttributeValue(1, "priorityDropdown"))
                .type(XMLParser.getCaseAttributeValue(1, "typeDropdown"))
                .layer(XMLParser.getCaseAttributeValue(1,"layerDropdown"))
                .isFlaky(XMLParser.getCaseAttributeValue(1,"isFlakyDropdown"))
                .milestone(XMLParser.getCaseAttributeValue(1, "milestoneDropdown"))
                .behaviour(XMLParser.getCaseAttributeValue(1,"behaviourDropdown"))
                .automationStatus(XMLParser.getCaseAttributeValue(1, "automationStatusDropdown"))
                .preconditions(XMLParser.getCaseAttributeValue(1,"preconditions"))
                .postconditions(XMLParser.getCaseAttributeValue(1,"postconditions"))
                .testStepOneName(XMLParser.getCaseAttributeValue(1,"testStepOneName"))
                .testStepOneData(XMLParser.getCaseAttributeValue(1, "testStepOneData"))
                .testStepOneResult(XMLParser.getCaseAttributeValue(1, "testStepOneResult"))
                .testStepTwoName(XMLParser.getCaseAttributeValue(1,"testStepTwoName"))
                .testStepTwoData(XMLParser.getCaseAttributeValue(1, "testStepTwoData"))
                .testStepTwoResult(XMLParser.getCaseAttributeValue(1, "testStepTwoResult"))
                .build();

    }

    public static TestCaseModel getSecondTestCase() throws ParserConfigurationException, IOException, SAXException {
        return new TestCaseModel.TestCaseModelBuilder()
                .title(XMLParser.getCaseAttributeValue(2,"title"))
                .description(XMLParser.getCaseAttributeValue(2,"description"))
                .status(XMLParser.getCaseAttributeValue(2, "statusDropdown"))
                .suite(XMLParser.getCaseAttributeValue(2, "suiteDropdown"))
                .severity(XMLParser.getCaseAttributeValue(2, "severityDropdown"))
                .priority(XMLParser.getCaseAttributeValue(2, "priorityDropdown"))
                .type(XMLParser.getCaseAttributeValue(2, "typeDropdown"))
                .layer(XMLParser.getCaseAttributeValue(2,"typeDropdown"))
                .isFlaky(XMLParser.getCaseAttributeValue(2,"isFlakyDropdown"))
                .milestone(XMLParser.getCaseAttributeValue(2, "milestoneDropdown"))
                .behaviour(XMLParser.getCaseAttributeValue(2,"behaviourDropdown"))
                .automationStatus(XMLParser.getCaseAttributeValue(2, "automationStatusDropdown"))
                .preconditions(XMLParser.getCaseAttributeValue(2,"preconditions"))
                .postconditions(XMLParser.getCaseAttributeValue(2,"postconditions"))
                .testStepOneName(XMLParser.getCaseAttributeValue(2,"testStepOneName"))
                .testStepOneData(XMLParser.getCaseAttributeValue(2, "testStepOneData"))
                .testStepOneResult(XMLParser.getCaseAttributeValue(2, "testStepOneResult"))
                .testStepTwoName(XMLParser.getCaseAttributeValue(2,"testStepTwoName"))
                .testStepTwoData(XMLParser.getCaseAttributeValue(2, "testStepTwoData"))
                .testStepTwoResult(XMLParser.getCaseAttributeValue(2, "testStepTwoResult"))
                .build();
    }
}
