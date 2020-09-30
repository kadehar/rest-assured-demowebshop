package com.github.kadehar.config;

import org.aeonbits.owner.ConfigFactory;

public class Config {
    public static String baseURL() {
        return config().baseURL();
    }

    public static String cartPath() {
        return config().cartPath();
    }

    public static String addProductToCartPath() { return config().addProductToCartPath(); }

    private static ApiConfig config() {
        return ConfigFactory.newInstance().create(ApiConfig.class);
    }
}
