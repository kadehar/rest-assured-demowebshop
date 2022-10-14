package com.github.kadehar.tests;

import com.github.kadehar.client.RegisterClient;
import com.github.kadehar.model.User;
import com.github.kadehar.steps.RegisterSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class RegisterTest extends TestBase {
    private final User user = new User();
    private final RegisterClient client = new RegisterClient();

    @Test
    public void registerUser() {
        user.setFirstName("Test")
                .setLastName("Test")
                .setEmail("nidoyef352@charav.com")
                .setPassword("111111")
                .setGender("M");

        Response register = client.register();
        String token = register.htmlPath().getString("**.find{it.@name == '__RequestVerificationToken'}.@value");

        Response newUser = client.createNewUser(token, register.cookies(), user);

        Map<String, String> cookies = newUser.cookies();
        System.out.println("Cookies: " + cookies);
    }
}
