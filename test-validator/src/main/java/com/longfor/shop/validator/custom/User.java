package com.longfor.shop.validator.custom;

public class User {

    @CheckCase(value = CaseMode.UPPER, message = "name需要大写")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}