package org.example.model;

import org.example.utilities.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TestCaseModelBuilder {

    public static TestCaseModel getTestCase(int i) throws ParserConfigurationException, IOException, SAXException {
        return new TestCaseModel.TestCaseModelBuilder()
                .title(XMLParser.getCaseAttributeValue(i,"title"))
                .description(XMLParser.getCaseAttributeValue(i,"description"))
                .status(XMLParser.getCaseAttributeValue(i, "statusDropdown"))
                .suite(XMLParser.getCaseAttributeValue(i, "suiteDropdown"))
                .severity(XMLParser.getCaseAttributeValue(i, "severityDropdown"))
                .priority(XMLParser.getCaseAttributeValue(i, "priorityDropdown"))
                .type(XMLParser.getCaseAttributeValue(i, "typeDropdown"))
                .layer(XMLParser.getCaseAttributeValue(i,"layerDropdown"))
                .isFlaky(XMLParser.getCaseAttributeValue(i,"isFlakyDropdown"))
                .milestone(XMLParser.getCaseAttributeValue(i, "milestoneDropdown"))
                .behaviour(XMLParser.getCaseAttributeValue(i,"behaviourDropdown"))
                .automationStatus(XMLParser.getCaseAttributeValue(i, "automationStatusDropdown"))
                .preconditions(XMLParser.getCaseAttributeValue(i,"preconditions"))
                .postconditions(XMLParser.getCaseAttributeValue(i,"postconditions"))
                .testStepOneName(XMLParser.getCaseAttributeValue(i,"testStepOneName"))
                .testStepOneData(XMLParser.getCaseAttributeValue(i, "testStepOneData"))
                .testStepOneResult(XMLParser.getCaseAttributeValue(i, "testStepOneResult"))
                .testStepTwoName(XMLParser.getCaseAttributeValue(i,"testStepTwoName"))
                .testStepTwoData(XMLParser.getCaseAttributeValue(i, "testStepTwoData"))
                .testStepTwoResult(XMLParser.getCaseAttributeValue(i, "testStepTwoResult"))
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
