package com.longfor.shopping.plugin.callback.demo.ano.en;

import com.longfor.shopping.plugin.callback.demo.ano.MyService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * @author Created by yawn on 2018-01-21 14:46
 */
@Component
public class WorkListenerParser implements ApplicationContextAware, InitializingBean {
  @Resource
  private MyService myService;

  private ApplicationContext applicationContext;

  @Override
  public void afterPropertiesSet() throws Exception {
    Map<String, Object> listenerBeans = getExpectListenerBeans(Controller.class, RestController.class, Service.class, Component.class);
    for (Object listener : listenerBeans.values()) {
      for (Method method : listener.getClass().getDeclaredMethods()) {
        if (!method.isAnnotationPresent(WorkListener.class)) {
          continue;
        }
        myService.setWorkListener(name -> {
          try {
            method.invoke(listener, name);
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
      }
    }
  }

  /**
   * 找到有可能使用注解的bean
   * @param annotationTypes 需要进行扫描的类级注解类型
   * @return 扫描到的beans的map
   */
  private Map<String, Object> getExpectListenerBeans(Class<? extends Annotation>... annotationTypes) {
    Map<String, Object> listenerBeans = new LinkedHashMap<>();
    for (Class<? extends Annotation> annotationType : annotationTypes) {
      Map<String, Object> annotatedBeansMap = applicationContext.getBeansWithAnnotation(annotationType);
      listenerBeans.putAll(annotatedBeansMap);
    }
    return listenerBeans;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}