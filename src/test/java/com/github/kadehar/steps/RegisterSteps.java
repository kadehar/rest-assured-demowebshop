package com.github.kadehar.steps;

import com.github.kadehar.spec.Request;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RegisterSteps {
    public static Response callRegister() {
        // @formatter:off
        return given()
                .spec(Request.spec())
                .log().uri()
                .when()
                .get("/register")
                .then()
                .log().all()
                .extract()
                .response();
        // @formatter:on
    }

    public static Response getLocation(String token, Map<String, String> cookies,
                                       String email) {
        // @formatter:off
        return given()
                .spec(Request.spec())
                .log().uri()
                .log().cookies()
                .log().headers()
                .cookies(cookies)
                .contentType(ContentType.URLENC)
                .formParam(
                        "__RequestVerificationToken",
                        token
                )
                .formParam(
                        "Gender",
                        "M"
                )
                .formParam(
                        "FirstName",
                        "Test"
                )
                .formParam(
                        "LastName",
                        "Test"
                )
                .formParam(
                        "Email",
                        email
                )
                .formParam(
                        "Password",
                        "123456"
                )
                .formParam(
                        "ConfirmPassword",
                        "123456"
                )
                .formParam(
                        "register-button",
                        "Register"
                )
                .when()
                .post("/register")
                .then()
                .log().headers()
                .log().cookies()
                .log().body()
                .extract()
                .response();
        // @formatter:on
    }

    public static Response location(String location, Map<String, String> cookies) {
        // @formatter:off
        return given()
                .spec(Request.spec())
                .log().uri()
                .log().cookies()
                .log().headers()
                .cookies(cookies)
                .when()
                .get(location)
                .then()
                .log().headers()
                .log().cookies()
                .log().body()
                .extract()
                .response();
        // @formatter:on
    }
}
