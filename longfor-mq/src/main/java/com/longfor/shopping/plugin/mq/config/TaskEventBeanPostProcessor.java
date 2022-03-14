package com.longfor.shopping.plugin.mq.config;

import com.longfor.shopping.plugin.mq.producer.TaskProducer;
import com.longfor.shopping.plugin.mq.producer.TaskProducerCallable;
import com.longfor.shopping.plugin.mq.producer.TaskProducerFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * 前置处理器，根据注解注入对象
 */
public class TaskEventBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskEventBeanPostProcessor.class);

    @Autowired
    private TaskProducerFactory taskProducerFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        processFieldSetter(bean, beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    public void processFieldSetter(Object bean, String beanName) {
        Class<?> objClz;
        if (AopUtils.isAopProxy(bean)) {
            objClz = AopUtils.getTargetClass(bean);
        } else {
            objClz = bean.getClass();
        }
        try {
            for (Field field : objClz.getDeclaredFields()) {
                TaskEvent taskEvent = field.getAnnotation(TaskEvent.class);
                if (taskEvent != null) {
                    String key = taskEvent.value();
                    if (StringUtils.isBlank(key)) {
                        LOGGER.error("beanName process Error:{} and TaskProducer.value not be null", field);
                        continue;
                    }
                    try {
                        if (TaskType.RUNNABLE.getType().equals(taskEvent.type())) {
                            TaskProducer taskProducer = taskProducerFactory.getProducer(key);
                            SetterInjector.setField(bean, field, taskProducer);
                        }
                        if (TaskType.CALLABLE.getType().equals(taskEvent.type())) {
                            TaskProducerCallable taskProducerCallable = taskProducerFactory.getProducerCallable(key);
                            SetterInjector.setField(bean, field, taskProducerCallable);
                        }
                        LOGGER.warn("TaskProducer set:{}-{}.", field, key);
                    } catch (Exception e) {
                        LOGGER.error("TaskProducer set Error:{} ", field, e);
                    }
                }
            }
        } catch (Exception e) {
            throw new BeanCreationException(beanName, e);
        }
    }

}
