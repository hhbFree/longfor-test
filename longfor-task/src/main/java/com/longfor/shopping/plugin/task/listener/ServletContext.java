package com.longfor.shopping.plugin.task.listener;

public class ServletContext implements BeanContext {
    @Override
    public boolean isLocal() {
        return false;
    }

    @Override
    public BeanIdentity getId() {
        return null;
    }

    @Override
    public Object getBean() {
        return null;
    }

    @Override
    public Throwable getLastError() {
        return null;
    }

    @Override
    public Object putAttachment(String key, Object value) {
        return null;
    }

    @Override
    public Object getAttachment(String key) {
        return null;
    }
}
