package org.example.model;

import lombok.extern.log4j.Log4j2;
import org.example.utilities.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Log4j2
public class TestPlanModelBuilder {

    public static TestPlanModel createTestPlan(String testPlanName, String testPlanDesc) {
        log.info("Building Test case model");
        return new TestPlanModel.TestPlanModelBuilder()
                .title(testPlanName)
                .description(testPlanDesc)
                .build();
    }
}
