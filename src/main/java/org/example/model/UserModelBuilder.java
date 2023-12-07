package org.example.model;

import com.github.javafaker.Faker;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class UserModelBuilder {

    public static UserModel getValidUser() throws IOException, ParseException {
        return new UserModel.UserModelBuilder()
                .email(LoginUtils.getLogin())
                .password(LoginUtils.getPassword())
                .build();
    }


    public static UserModel getIncorrectUser(){
        Faker faker = new Faker();

        return new UserModel.UserModelBuilder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
    }
}
