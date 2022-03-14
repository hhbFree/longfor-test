package com.longfor.shopping.plugin.task.impl;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceTest {
 
    @RountingInjected(value = "helloServiceImpl2")
    private HelloService helloService;
 
    public void testSayHello() {
        helloService.sayHello();
    }
 
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.longfor.shopping.plugin.task.impl");
        HelloServiceTest helloServiceTest = applicationContext.getBean(HelloServiceTest.class);
        helloServiceTest.testSayHello();
    }
}