package com.github.kadehar.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DemowebshopTest extends TestBase {
    private String response;

    @Test
    @DisplayName("Добавление нового товара в корзину")
    void addNewItemToCart() {
        step("Товар успешно добавлен в корзину", () -> {
            steps.addItemToCart();
        });
    }

    @Test
    @DisplayName("Удаление добавленного товара из корзины")
    void deleteItemFromCart() {
        step("Удаление товара из корзины", () -> {
            step("Добавить товар в корзину", () -> {
                steps.addItemToCart();
            });
            step("Удалить товар из корзины", () -> {
                response = steps.deleteItemFromCart();
            });
            step("Проверить что ответ равен 'You have no items in your shopping cart.'", () -> {
                assertThat(response, equalTo("You have no items in your shopping cart."));
            });
        });
    }

    @Test
    void getToken() {
        String value = steps.token();
        System.out.println("\nTOKEN VALUE IS " + value);
    }
}