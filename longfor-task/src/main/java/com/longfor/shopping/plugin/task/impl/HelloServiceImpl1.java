package com.longfor.shopping.plugin.task.impl;

import org.springframework.stereotype.Service;

@Service(value = "hihi")
public class HelloServiceImpl1 implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("你好我是HelloServiceImpl2");
    }
}