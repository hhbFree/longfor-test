package com.longfor.shopping.plugin.mq.config;

import com.longfor.shopping.plugin.mq.producer.TaskProducerFactory;
import com.longfor.shopping.plugin.mq.producer.TaskProducerFactoryImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertyResolver;

import java.util.Set;

import static java.util.Collections.emptySet;

@Configuration
@EnableConfigurationProperties(TaskPluginProperties.class)
public class PluginConfiguration {

	/**
	 * task 生产者自动注入
	 *
	 * @return
	 */
	@Bean
	public TaskProducerFactory taskProducerFactoryPostProcessor() {
		return new TaskProducerFactoryImpl();
	}

	/**
	 * task 生产者自动注入
	 *
	 * @return
	 */
	@Bean
	public TaskEventBeanPostProcessor taskEventBeanPostProcessor() {
		return new TaskEventBeanPostProcessor();
	}

	/**
	 *
	 * @param propertyResolver
	 * @return
	 */
	@Bean
	public PluginProcessor taskImplementationAnnotationBeanPostProcessor( PropertyResolver propertyResolver) {
		Set<String> packagesToScan = propertyResolver.getProperty(TaskPluginProperties.PREFIX, Set.class, emptySet());
		return new PluginProcessor(packagesToScan);
	}

}