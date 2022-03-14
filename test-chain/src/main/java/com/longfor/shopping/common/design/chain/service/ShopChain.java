package com.longfor.shopping.common.design.chain.service;


import com.longfor.shopping.common.design.chain.dto.ChainParam;
import com.longfor.shopping.common.design.chain.dto.ChainResult;

public interface ShopChain {
    // 获取通用参数
    ChainParam request();

    // 转发request
    ChainResult proceed(ChainParam request);
}
