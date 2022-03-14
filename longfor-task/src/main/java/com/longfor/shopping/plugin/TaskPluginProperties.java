package com.longfor.shopping.plugin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = TaskPluginProperties.PREFIX)
public class TaskPluginProperties {

    public static final String PREFIX = "shopping.plugin.task";

    @NestedConfigurationProperty
    private Strategy strategy;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
