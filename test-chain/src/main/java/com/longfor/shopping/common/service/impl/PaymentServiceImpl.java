package com.longfor.shopping.common.service.impl;

import com.longfor.shopping.common.design.chain.dto.ChainParam;
import com.longfor.shopping.common.design.chain.dto.ChainResult;
import com.longfor.shopping.common.design.chain.service.AbstractChainService;
import com.longfor.shopping.common.design.chain.service.ShopChain;
import com.longfor.shopping.common.design.chain.service.chainenum.Chain;
import com.longfor.shopping.common.design.chain.service.OrderPlugin;
import com.longfor.shopping.common.service.PaymentService;
import org.springframework.stereotype.Service;

@Chain(OrderPlugin.PAYMENT)
@Service
public class PaymentServiceImpl  implements PaymentService, AbstractChainService {
    @Override
    public ChainResult deal(ShopChain chain) {
        ChainParam request = chain.request();
        System.out.println("PaymentService=====>request:" + request.toString());

        // 构建新的Request
        ChainParam newRequest = new ChainParam().build().add("payment","这是付款的处理逻辑");
        System.out.println("这是付款的处理逻辑");
        return chain.proceed(newRequest);
    }

    @Override
    public boolean match(ChainParam chainParam) {
        return false;
    }

    @Override
    public ChainResult payment(ChainParam chainParam) {
        System.out.println("payment 的普通流程");
        return null;
    }
}
