package com.github.kadehar.steps;

import com.github.kadehar.config.Config;
import com.github.kadehar.spec.Request;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Steps {
    public void addItemToCart() {
        // @formatter:off
        given()
            .spec(Request.spec())
            .basePath(Config.addProductToCartPath())
            .log().uri()
        .when()
            .post("/catalog/31/1/1")
        .then()
            .log().body()
            .statusCode(200)
            .body("success", is(true));
        // @formatter:on
    }

    public String deleteItemFromCart() {
        // @formatter:off
        Response response = given()
                                .spec(Request.spec())
                                .basePath(Config.cartPath())
                                .log().uri()
                            .when()
                                .post()
                            .then()
                                .log().body()
                                .extract()
                                .response();
        // @formatter:on
        String value = response.htmlPath().getString("**.find{it.@class == 'count'}");
        return StringUtils.substringBetween(value, "\n", "    ");
    }

    public String token() {
        // @formatter:off
        Response response = given()
                                .spec(Request.spec())
                                .log().uri()
                            .when()
                                .get("/register")
                            .then()
                                .log().body()
                                .extract()
                                .response();
        // @formatter:on
        return response.htmlPath().getString("**.find{it.@name == '__RequestVerificationToken'}.@value");
    }
}
