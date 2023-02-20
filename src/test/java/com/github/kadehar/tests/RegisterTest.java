package com.github.kadehar.tests;

import com.github.javafaker.Faker;
import com.github.kadehar.client.RegisterClient;
import com.github.kadehar.model.User;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class RegisterTest extends TestBase {
    private final User user = new User();
    private final RegisterClient client = new RegisterClient();
    private final Faker faker = new Faker();

    @Test
    public void registerUser() {
        user.setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password(6, 14, true))
                .setGender(faker.demographic().sex());

        Response register = client.register();
        String token = register.htmlPath().getString("**.find{it.@name == '__RequestVerificationToken'}.@value");

        Response newUser = client.createNewUser(token, register.cookies(), user);

        Map<String, String> cookies = newUser.cookies();
        System.out.println("Cookies: " + cookies);
    }
}
