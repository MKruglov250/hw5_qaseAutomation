package org.example.model;

import lombok.extern.log4j.Log4j2;
import org.example.utilities.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Log4j2
public class TestCaseModelBuilder {

    static int mockTestCases;

    public static TestCaseModel getTestCase(int i) throws ParserConfigurationException, IOException, SAXException {
        log.info("Building Test case model from XML example: #" + i);
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

    public static TestCaseModel getTestCase(){
        mockTestCases++;
        log.info("Building Mock Test Case #: " + mockTestCases);
        return new TestCaseModel.TestCaseModelBuilder()
                .title("Case " + mockTestCases)
                .suite("First Suite")
                .testStepOneName("Mock Step One")
                .build();
    }
}
