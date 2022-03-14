package com.longfor.shopping.common.design.chain.service.supplier;


import com.longfor.shopping.common.design.chain.dto.ChainParam;
import com.longfor.shopping.common.design.chain.dto.ChainResult;
import com.longfor.shopping.common.design.chain.service.AbstractChainService;
import com.longfor.shopping.common.design.chain.service.ShopChain;

import java.util.List;

public class ShopRealChain implements ShopChain {

    public ChainParam request;
    public List<AbstractChainService> ratifyList;
    public int index;

    public ShopRealChain(List<AbstractChainService> ratifyList, ChainParam request, int index) {
        this.ratifyList = ratifyList;
        this.request = request;
        this.index = index;
    }

    @Override
    public ChainParam request() {
        return this.request;
    }

    //将注册的对象处理
    @Override
    public ChainResult proceed(ChainParam request) {
        ChainResult proceed = null;
        if (ratifyList.size() > index) {
            ShopRealChain realChain = new ShopRealChain(ratifyList, request, index + 1);
            AbstractChainService abstractChainService = ratifyList.get(index);

            //TODO 判断是否中断这一次处理
            boolean match = abstractChainService.match(request);
            if(!match){
                proceed = abstractChainService.deal(realChain);
            }else{
                return new ChainResult().build().add("break","中断责任链");
            }
        }
        return proceed==null?new ChainResult().build().add("end","处理完毕"):proceed;
    }
}
