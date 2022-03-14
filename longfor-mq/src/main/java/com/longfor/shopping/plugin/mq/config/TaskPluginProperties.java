package com.longfor.shopping.plugin.mq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = TaskPluginProperties.PREFIX)
public class TaskPluginProperties {

    public static final String PREFIX = "shopping.plugin.mq";

}
