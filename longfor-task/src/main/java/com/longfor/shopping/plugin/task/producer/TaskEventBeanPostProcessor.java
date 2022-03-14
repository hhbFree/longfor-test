package com.longfor.shopping.plugin.task.producer;

import com.longfor.shopping.plugin.task.callable.TaskProducerCallable;
import com.longfor.shopping.plugin.task.callable.TaskType;
import com.longfor.shopping.plugin.task.listener.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;


public class TaskEventBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskEventBeanPostProcessor.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TaskProducerFactory taskProducerFactory;

    /**
     * for springboot  support helium
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
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
                    if (StringUtils.isNullOrEmpty(key)) {
                        LOGGER.error("beanName process Error:{} and TaskProducer.value not be null", field);
                        continue;
                    }
                    try {
//                        Map<String, Object> beansOfType = (Map<String, Object>)this.applicationContext.getBeansOfType(field.getType());
//                        field.setAccessible(true);
//                        Object proxy = RoutingBeanProxyFactory.createProxy(key, field.getType(), beansOfType);
//                        field.set(bean, proxy);
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
