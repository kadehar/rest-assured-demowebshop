package com.github.kadehar.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config.properties"
})
public interface ApiConfig extends Config {
    @Key("api.base.url")
    String baseURL();

    @Key("api.cart.path")
    String cartPath();

    @Key("api.addproduct.path")
    String addProductToCartPath();
}
