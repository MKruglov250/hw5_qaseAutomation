package org.example;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

@Log4j2
public class Requests {


    String token = LoginUtils.getToken();

    public Requests() throws IOException, ParseException {
    }

    @Step("Create Test Case from local file")
    public Response createTestCase(String casePath) throws IOException, ParseException {
        log.info("Creating Test Case with POST Request");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(casePath));
        Response response = given().contentType("application/json")
                .accept("application/json").header("Token",token)
                .body(obj).when().post("/case/QASEAPP");
        log.debug("Test Case Created: " + response.jsonPath().get("result.id"));
        return response;
    }

    @Step("Create Mock Test Case")
    public Response createMockTestCase(String name){
        log.info("Creating Mock Test Case with POST Request");
        Response response = given().contentType("application/json")
                .accept("application/json").header("Token",token)
                .body(String.format("{\"title\":\"%s\",\"description\":\"lala\"}",name))
                .when().post("/case/QASEAPP");
        log.debug("Test Case Created: " + response.jsonPath().get("result.id"));
        return response;
    }

    @Step("Read Test Case")
    public Response readTestCase(int caseId){
        log.info("Reading test case with GET request");
        Response response = given().contentType("application/json")
                .accept("application/json").header("Token",token)
                .when().get("/case/QASEAPP/" + caseId);
        return response;
    }

    @Step("Update Test Case")
    public Response updateTestCase(int caseId, String updateString){
        log.info("Reading test case with GET request");
        Response response = given().contentType("application/json")
                .accept("application/json").header("Token",token)
                .body(String.format("{\"description\":\"%s\"}",updateString))
                .when().patch("/case/QASEAPP/" + caseId);
        return response;
    }

    @Step("Update Test Case")
    public Response deleteTestCase(int caseId){
        log.info("Reading test case with GET request");
        Response response = given().contentType("application/json")
                .accept("application/json").header("Token",token)
                .when().delete("/case/QASEAPP/" + caseId);
        return response;
    }

    @Step("Create Test Plan")
    public Response createTestPlan(String title, String description, int[] caseIds) {
        log.info("Creating Test Plan with POST Request");
        String body = String.format("{\n" +
                "  \"title\": \"%s\",\n" +
                "  \"description\": \"%s\",\n" +
                "  \"cases\": %s\n" +
                "}",title,description, Arrays.toString(caseIds));
        Response response = given().contentType("application/json")
                .accept("application/json").header("Token",token)
                .body(body).when().post("/plan/QASEAPP");
        log.debug("Test Plan Created: " + response.jsonPath().get("result.id"));
        return response;
    }

    @Step("Read Test Plan")
    public Response readTestPlan(int planId){
        log.info("Reading Test Plan with GET request");
        Response response = given().contentType("application/json")
                .accept("application/json").header("Token",token)
                .when().get("/plan/QASEAPP/" + planId);
        return response;
    }

    @Step("Update Test Plan")
    public Response updateTestPlan(int planId, String updateString){
        log.info("Updating Test Plan with PATCH request");
        Response response = given().contentType("application/json")
                .accept("application/json").header("Token",token)
                .body(String.format("{\"description\":\"%s\"}",updateString))
                .when().patch("/plan/QASEAPP/" + planId);
        return response;
    }

    @Step("Update Test Case")
    public Response deleteTestPlan(int planId){
        log.info("Deleting Test Plan with DELETE request");
        Response response = given().contentType("application/json")
                .accept("application/json").header("Token",token)
                .when().delete("/plan/QASEAPP/" + planId);
        return response;
    }

//    @Step("Create Test Case from model")

    /*

    Create Test Case (Code)
    Read Test Case (Code, Body, Model)
    Update Test Case (Code, Body, Model)
    Delete Test Case (Code)

    Create Test Plan (Code)
    Read Test Plan (Code, Body, Model)
    Update Test Plan (Code, Body, Model)
    Delete Test Plan (Code)

    Create Mock Test Cases (Code)
    Delete Mock Test Cases (Code)

     */
}
