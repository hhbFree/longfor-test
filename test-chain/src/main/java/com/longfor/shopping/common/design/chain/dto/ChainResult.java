package com.longfor.shopping.common.design.chain.dto;


import java.util.HashMap;
import java.util.Map;

public class ChainResult {

    private Map<String,Object> param=new HashMap<>();

    public ChainResult build(){
        return this;
    }

    public ChainResult add(String key, Object value){
        param.put(key, value);
        return this;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }
}
