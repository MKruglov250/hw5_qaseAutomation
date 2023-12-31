package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.example.model.TestCaseModel;
import org.example.model.TestCaseModelBuilder;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileReader;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Log4j2
public class TestCasePageTest extends BaseTest {

    TestCaseModel firstCaseModel = TestCaseModelBuilder.getTestCase(1);
    TestCaseModel secondCaseModel = TestCaseModelBuilder.getTestCase(2);
    Gson gson;

    public TestCasePageTest() throws ParserConfigurationException, IOException, SAXException, ParseException {
        super();
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @BeforeMethod(description = "Login before performing Test Case module tests",
    alwaysRun = true)
    public void beforeMethod() {
        loginPageSteps.login(validUser);
        log.info("Logged in to Qase.io");
        testCasePageSteps.openQaseProject();
    }

    @TmsLink("QAT-1")
    @Test(description = "Check Creation of Test Case 1", groups = "Smoke")
    public void testCreateTestCaseOne(){
        log.info("Checking Create Test Case One");
        Assert.assertTrue(testCasePageSteps.createTestCase(firstCaseModel));
    }

    @TmsLink("QAT-1")
    @Test(description = "Check Creation of Test Case 2", groups = "Smoke")
    public void testCreateTestCaseTwo(){
        log.info("Checking Create Test Case Two");
        Assert.assertTrue(testCasePageSteps.createTestCase(secondCaseModel));
    }

    @TmsLink("QAT-2")
    @Test(description = "Check Reading Test Case", groups = "Smoke",
            priority = 1)
    public void testReadTestCaseOne(){
        log.info("Checking Read Test Case One");
        Assert.assertEquals(testCasePageSteps.checkTestCaseRead(firstCaseModel.getTitle()),
                "First Test Case");
    }

    @TmsLink("QAT-2")
    @Test(description = "Check Read Test Case using WebAPI", groups = "Smoke")
    public void testReadTestCaseOneApi() throws IOException {
        log.info("Checking Read Test Case One using WebAPI");
        int caseId = testCasePageSteps.checkTestCaseReadDeep(firstCaseModel.getTitle());
        Response response = requests.readTestCase(caseId);
        log.info("Status code is: " + response.statusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.asString());
        JsonNode resultNode = jsonNode.get("result");
        JsonNode refNode = objectMapper.readTree(new FileReader("src/test/resources/references/testCaseRef.json"));
        TestCaseModel referenceModel = gson.fromJson(refNode.toString(), TestCaseModel.class);
        TestCaseModel compareModel = gson.fromJson(resultNode.toString(), TestCaseModel.class);
        Assert.assertEquals(compareModel, referenceModel);
    }

    @TmsLink("QAT-3")
    @Test(description = "Check Editing Test Case", groups = "Regression",
            priority = 2)
    public void testEditTestCaseOne(){
        log.info("Checking Edit Test Case One");
        Assert.assertEquals(testCasePageSteps.checkEditTestCase(firstCaseModel.getTitle()),
                "Brand-new edited description");
    }

    @TmsLink("QAT-4")
    @Test(description = "Check Deleting test case 1", groups = "Regression",
            priority = 3)
    public void testDeleteTestCaseOne(){
        log.info("Checking Delete Test Case 1");
        Assert.assertTrue(testCasePageSteps.deleteTestCase(firstCaseModel.getTitle()));
    }

    @TmsLink("QAT-4")
    @Test(description = "Check Deleting test case 2", groups = "Regression",
            priority = 3)
    public void testDeleteTestCaseTwo(){
        log.info("Checking Delete Test Case 2");
        Assert.assertTrue(testCasePageSteps.deleteTestCase(secondCaseModel.getTitle()));
    }

    @AfterMethod(description = "Logging out after performing test", alwaysRun = true)
    public void logout(){
        loginPageSteps.logoutFromSite();
    }

}