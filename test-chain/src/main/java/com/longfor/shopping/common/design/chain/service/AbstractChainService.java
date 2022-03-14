package com.longfor.shopping.common.design.chain.service;


import com.longfor.shopping.common.design.chain.dto.ChainParam;
import com.longfor.shopping.common.design.chain.dto.ChainResult;

public interface AbstractChainService {
    // 处理请求
    ChainResult deal(ShopChain chain);

    //判断是否路由
    boolean match(ChainParam chainParam);
}
