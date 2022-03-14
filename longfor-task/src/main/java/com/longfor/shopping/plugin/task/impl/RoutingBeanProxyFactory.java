package com.longfor.shopping.plugin.task.impl;

import com.longfor.shopping.plugin.task.producer.TaskProducer;
import com.longfor.shopping.plugin.task.producer.TaskProducerFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import java.util.Map;

public class RoutingBeanProxyFactory {
 
    private final static String DEFAULT_BEAN_NAME = "helloServiceImpl1";

 
    public static Object createProxy(String name, Class type, Map<String, Object> candidates) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(type);
        proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(name, candidates));
        return proxyFactory.getProxy();
    }
 
    static class VersionRoutingMethodInterceptor implements MethodInterceptor {
        private Object targetObject;
 
        public VersionRoutingMethodInterceptor(String name, Map<String, Object> beans) {
            this.targetObject = beans.get(name);

        }
 
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            return invocation.getMethod().invoke(this.targetObject, invocation.getArguments());
        }
    }
}