package com.longfor.shopping.plugin.task.callable;

public enum TaskType {
    RUNNABLE("run"),CALLABLE("call");

    private String type;

    TaskType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
