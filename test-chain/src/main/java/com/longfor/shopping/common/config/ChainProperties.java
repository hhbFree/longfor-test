package com.longfor.shopping.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = ChainProperties.PREFIX)
public class ChainProperties {


    public static final String PREFIX = "order.shop.plugin";

    private final Map<String, List<String>> chain = new HashMap<>();

    @NestedConfigurationProperty
    private Strategy strategy;

    public Map<String,List<String>> getChain() {
        return chain;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
