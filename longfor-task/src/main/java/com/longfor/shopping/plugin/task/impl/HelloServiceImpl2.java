package com.longfor.shopping.plugin.task.impl;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl2 implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("你好我是HelloServiceImpl2");
    }
}