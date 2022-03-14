package com.longfor.shopping.common.service.impl;

import com.longfor.shopping.common.service.OutWareHouseService;
import com.longfor.shopping.common.design.chain.dto.ChainParam;
import com.longfor.shopping.common.design.chain.dto.ChainResult;
import com.longfor.shopping.common.design.chain.service.AbstractChainService;
import com.longfor.shopping.common.design.chain.service.ShopChain;
import com.longfor.shopping.common.design.chain.service.supplier.ShopChainContext;
import com.longfor.shopping.common.design.chain.service.supplier.ShopRealChain;
import com.longfor.shopping.common.design.chain.service.chainenum.Chain;
import com.longfor.shopping.common.design.chain.service.OrderPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Chain(OrderPlugin.OUTWAREHOUSE)
@Service
public class OutWareHouseServiceImpl implements OutWareHouseService, AbstractChainService {

    @Autowired
    private ShopChainContext shopChainContext;

    @Override
    public ChainResult outWareHouse(ChainParam chainParam) {
        System.out.println("动态添加c1链路,放到订单后面");
        List<AbstractChainService> c1List = shopChainContext.getChannelMapList("c1-1");

        //动态添加c1链路,放到订单后面
        ShopRealChain shopRealChain=new ShopRealChain(c1List,chainParam,1);

        return deal(shopRealChain);
    }

    @Override
    public ChainResult deal(ShopChain chain) {
        ChainParam request = chain.request();
        System.out.println("OutWareHouseService=====>request:" + request.toString());

        // 构建新的Request
        ChainParam newRequest = new ChainParam().build().add("order","这是出库处理逻辑");
        System.out.println("这是出库处理逻辑");

        return chain.proceed(newRequest);
    }

    @Override
    public boolean match(ChainParam chainParam) {
        return false;
    }
}
