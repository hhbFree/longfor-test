package com.longfor.shopping.plugin.task.listener;

public class Test {
    public static void main(String[] args) {
        BeanContextService beanContextService=new AbstractBeanContextService();


        ServletRouter servletRouter=new ServletRouter("test",beanContextService);


    }
}
