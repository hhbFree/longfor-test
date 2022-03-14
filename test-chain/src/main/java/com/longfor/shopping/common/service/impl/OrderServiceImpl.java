package com.longfor.shopping.common.service.impl;

import com.longfor.shopping.common.design.chain.dto.ChainResult;
import com.longfor.shopping.common.design.chain.dto.ChainParam;
import com.longfor.shopping.common.design.chain.service.AbstractChainService;
import com.longfor.shopping.common.design.chain.service.ShopChain;
import com.longfor.shopping.common.design.chain.service.chainenum.Chain;
import com.longfor.shopping.common.design.chain.service.OrderPlugin;
import com.longfor.shopping.common.service.OrderService;
import org.springframework.stereotype.Service;

@Chain(OrderPlugin.ORDER)
@Service
public class OrderServiceImpl implements OrderService, AbstractChainService {

    @Override
    public ChainResult deal(ShopChain chain) {
        ChainParam request = chain.request();
        System.out.println("OrderService=====>request:" + request.toString());

        // 构建新的Request
        ChainParam newRequest = new ChainParam().build().add("order","这是订单处理逻辑");
        System.out.println("这是订单处理逻辑");

        return chain.proceed(newRequest);
    }

    @Override
    public boolean match(ChainParam chainParam) {

        //TODO 中断这次链路处理 true
        return false;
    }

    @Override
    public ChainResult order(ChainParam param) {

        System.out.println("普通注入的order流程");

        return null;
    }
}
