package org.example;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

@Log4j2
public class Requests extends BaseApi{


    public Requests() throws IOException, ParseException {
    }

    @Step("Create Test Case from local file")
    public Response createTestCase(String casePath) throws IOException, ParseException {
        log.info("Creating Test Case with POST Request");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(casePath));
        Response response = post("/case/QASEAPP",obj.toString());
        log.debug("Test Case Created: " + response.jsonPath().get("result.id"));
        return response;
    }

    @Step("Create Mock Test Case")
    public Response createMockTestCase(String name, int suite){
        log.info("Creating Mock Test Case with POST Request");
        String body = String.format("{\"title\":\"%s\",\"description\":\"lala\"," +
                "\"suite_id\":\"%d\"}",name, suite);
        Response response = post("/case/QASEAPP", body);
        log.debug("Test Case Created: " + response.jsonPath().get("result.id"));
        return response;
    }

    @Step("Read Test Case")
    public Response readTestCase(int caseId){
        log.info("Reading test case with GET request");
        return get("/case/QASEAPP/" + caseId, HttpStatus.SC_OK);
    }

    @Step("Update Test Case")
    public Response updateTestCase(int caseId, String updateString){
        log.info("Reading test case with GET request");
        String body = String.format("{\"description\":\"%s\"}",updateString);
        return patch("/case/QASEAPP/" + caseId, HttpStatus.SC_OK,body);
    }

    @Step("Update Test Case")
    public Response deleteTestCase(int caseId){
        log.info("Deleting Test Case with DELETE request");
        return delete("/case/QASEAPP/" + caseId, HttpStatus.SC_OK);
    }

    @Step("Create Test Plan")
    public Response createTestPlan(String title, String description, int[] caseIds) {
        log.info("Creating Test Plan with POST Request");
        String body = String.format("{\n" +
                "  \"title\": \"%s\",\n" +
                "  \"description\": \"%s\",\n" +
                "  \"cases\": %s\n" +
                "}",title,description, Arrays.toString(caseIds));
        Response response = post("/plan/QASEAPP",body);
        log.debug("Test Plan Created: " + response.jsonPath().get("result.id"));
        return response;
    }

    @Step("Read Test Plan")
    public Response readTestPlan(int planId){
        log.info("Reading Test Plan with GET request");
        return get("/plan/QASEAPP/" + planId, HttpStatus.SC_OK);
    }

    @Step("Update Test Plan")
    public Response updateTestPlan(int planId, String updateString){
        log.info("Updating Test Plan with PATCH request");
        String body = String.format("{\"description\":\"%s\"}",updateString);
        return patch("/plan/QASEAPP/" + planId, HttpStatus.SC_OK, body);
    }

    @Step("Update Test Case")
    public Response deleteTestPlan(int planId){
        log.info("Deleting Test Plan with DELETE request");
        return delete("/plan/QASEAPP/" + planId, HttpStatus.SC_OK);
    }

}
