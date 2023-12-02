package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BaseApi {
    Gson gson;
    public RequestSpecification requestSpecification;
    String token = LoginUtils.getToken();

    public BaseApi() throws IOException, ParseException {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        RestAssured.baseURI="https://api.qase.io/v1";
        requestSpecification = given().contentType(ContentType.JSON)
                .header("Token",token)
                .accept(ContentType.JSON)
                .log().all();
    }

    public Response get(String endpoint, int expectedCode){
        return requestSpecification.when()
                .get(endpoint).then().statusCode(expectedCode)
                .contentType(ContentType.JSON)
                .extract().response();
    }

    public Response post(String endpoint, String body){
        return requestSpecification.body(body).when()
                .post(endpoint).then()
                .extract().response();
    }


    public Response patch(String endpoint, int expectedCode, String body){
        return requestSpecification.body(body).when()
                .patch(endpoint).then().statusCode(expectedCode)
                .extract().response();
    }

    public Response delete(String endpoint, int expectedCode){
        return requestSpecification.when()
                .delete(endpoint).then().statusCode(expectedCode)
                .extract().response();
    }

}
