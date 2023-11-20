package org.example.utilities;

import io.qameta.allure.Step;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class XMLParser {

    @Step("Get Test Case Attribute value from XML")
    public static String getCaseAttributeValue(int caseNumber, String attribute) throws ParserConfigurationException, IOException, SAXException {
        File file = new File("src/resources/testCases.xml");
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        Node root = document.getDocumentElement();
        Node testCase = root.getChildNodes().item(caseNumber);
        NodeList testCaseData = testCase.getChildNodes();
        for (int i = 0; i < testCaseData.getLength(); i++){
            Node att = testCaseData.item(i);
            if (Objects.equals(att.getNodeName(), attribute)){
                return att.getTextContent();
            }
        }
        return "Attribute value not found in XML :(";
    }
}
