package org.example.utilities;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;


@Log4j2
public class LoginUtils {

    @Step("Get Login from local Credentials storage")
    public static String getLogin() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/resources/credentials.json"));
        return ((JSONObject) obj).get("login").toString();
    }

    @Step("Get Password from local Credentials storage")
    public static String getPassword() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/resources/credentials.json"));
        return ((JSONObject) obj).get("password").toString();
    }

}
