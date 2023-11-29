package org.example;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;

@Log4j2
public class ApiTests extends BaseTest{

    static int createdCaseId = 0;
    static int mockOneId = 0;
    static int mockTwoId = 0;
    static int createPlanId = 0;
    public ApiTests() throws IOException, ParseException {
    }

    @BeforeClass(description = "Create Mock Cases")
    public void createMockCases(){
        log.info("Create mock cases before Test Plan tests");
        Response response = requests.createMockTestCase("Haha 1",3);
        log.info("Status code is: " + response.statusCode());
        mockOneId = response.jsonPath().get("result.id");
        log.info("Created case: " + mockOneId);
        response = requests.createMockTestCase("Hoho 2",3);
        log.info("Status code is: " + response.statusCode());
        mockTwoId = response.jsonPath().get("result.id");
        log.info("Created case: " + mockTwoId);
    }

    @AfterClass(description = "Delete Mock Cases")
    public void deleteMockCases(){
        log.info("Deleting mock cases after Test Plan tests");
        log.info("Deleting case: " + mockOneId);
        Response response = requests.deleteTestCase(mockOneId);
        log.info("Status code is: " + response.statusCode());
        log.info("Deleting case: " + mockTwoId);
        response = requests.deleteTestCase(mockTwoId);
        log.info("Status code is: " + response.statusCode());
        log.info("Mock Cases Deleted");
    }

    @Test(description="WebAPI: Check POST Create New Test Case")
    public void createTestCase() throws IOException, ParseException {
        log.info("Checking POST request to Create New Test Case");
        String casePath = "src/test/resources/references/testCaseRef.json";
        Response response = requests.createTestCase(casePath);
        createdCaseId = response.jsonPath().get("result.id");
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description="Read Test Case", priority = 1)
    public void readTestCase(){
        log.info("Checking GET request to Read Test Case");
        JsonPath expectedJson = new JsonPath(new File(
                "src/test/resources/references/testCaseRef.json"));
        Response response = requests.readTestCase(createdCaseId);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
        response.then().assertThat()
                .body("result.id", equalTo(createdCaseId))
                .body("result.title", equalTo("First Test Case"))
                .body("result.description", equalTo("Description of First Test Case"))
                .body("result.preconditions", equalTo("Pre-condition that is very funny"))
                .body("result.postconditions",equalTo("Post-condition extremely exciting"))
                .body("result.severity", equalTo(3))
                .body("result.priority", equalTo(3))
                .body("result.type", equalTo(2))
                .body("result.is_flaky", equalTo(1))
                .body("result.behavior", equalTo(2))
                .body("result.automation", equalTo(0))
                .body("result.status", equalTo(0))
                .body("result.suite_id",equalTo(3))
                .body("result.steps[0].action",equalTo("Do lucky action"))
                .body("result.steps[0].data",equalTo("Enter lucky data"))
                .body("result.steps[0].expected_result",equalTo("Check lucky result"))
                .body("result.steps[1].action",equalTo("Perform silly move"))
                .body("result.steps[1].data",equalTo("Set silly values"))
                .body("result.steps[1].expected_result",equalTo("Verify silly consequence"));
    }

    @Test(description = "Update Test Case", priority = 2)
    public void updateTestCase(){
        log.info("Checking PATCH request to Update Test Case");
        String updateString = "Truly Updated Description";
        Response response = requests.updateTestCase(createdCaseId, updateString);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
        response = requests.readTestCase(createdCaseId);
        response.then().assertThat()
                .body("result.description", equalTo(updateString));
    }

    @Test(description = "Delete Test Case", priority = 3)
    public void deleteTestCase(){
        log.info("Checking DELETE request to Delete Test Case");
        Response response = requests.deleteTestCase(createdCaseId);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "Create Test Plan")
    public void createTestPlan(){
        log.info("Checking POST request to Create Test Plan");
        Response response = requests.createTestPlan("WebAPI Cool Plan"
                , "Api Desc", new int[]{mockOneId, mockTwoId});
        int statusCode = response.statusCode();
        createPlanId = response.jsonPath().get("result.id");
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }

    @Test(description = "Read Test Plan", priority = 1)
    public void readTestPlan(){
        log.info("Checking GET request to Read Test Plan");
        Response response = requests.readTestPlan(createPlanId);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        response.then().assertThat().body("result.id",equalTo(createPlanId))
                .body("result.title", equalTo("WebAPI Cool Plan"))
                .body("result.description", equalTo("Api Desc"))
                .body("result.cases_count", equalTo(2))
                .body("result.cases[0].case_id", equalTo(mockOneId))
                .body("result.cases[1].case_id", equalTo(mockTwoId));
    }

    @Test(description = "Update Test Plan", priority = 2)
    public void updateTestPlan(){
        log.info("Checking PATCH request to Update Test Plan");
        String updateString = "OMG Plan is so WebAPI updated";
        Response response = requests.updateTestPlan(createPlanId, updateString);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
        response = requests.readTestPlan(createPlanId);
        response.then().assertThat()
                .body("result.description", equalTo(updateString));
    }

    @Test(description = "Delete Test Plan", priority = 3)
    public void deleteTestPlan(){
        log.info("Checking DELETE request to Delete Test Case");
        Response response = requests.deleteTestPlan(createPlanId);
        int statusCode = response.statusCode();
        log.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
    }


}
