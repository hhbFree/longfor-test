package com.longfor.shopping.common.design.chain.dto;

import java.util.HashMap;
import java.util.Map;

public class ChainParam {
    //统一的处理参数
    private Map<String,Object> param=new HashMap<>();

    //航道类型
    private String channelType;

    //责任链的下标
    private int chainIndex;

    public int getChainIndex() {
        return chainIndex;
    }

    public void setChainIndex(int chainIndex) {
        this.chainIndex = chainIndex;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public ChainParam build(){
        return this;
    }

    public ChainParam add(String key, Object value){
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
