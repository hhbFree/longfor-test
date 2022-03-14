package com.longfor.shopping.common;

import com.longfor.shopping.common.design.chain.dto.ChainParam;
import com.longfor.shopping.common.design.chain.dto.ChainResult;
import com.longfor.shopping.common.design.chain.service.supplier.ShopChainContext;
import com.longfor.shopping.common.service.OrderService;
import com.longfor.shopping.common.service.OutWareHouseService;
import com.longfor.shopping.common.service.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChainTest {

    @Autowired
    private ShopChainContext client ;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OutWareHouseService outWareHouseService;

    /**
     * 外部化调用责任链
     */
    @Test
    public void chainCommon(){
        ChainParam request = new ChainParam().build();
        request.setChannelType("c2");
        request.setChainIndex(0);
        ChainResult result = client.execute(request);
        System.out.println("结果：" + result.getParam().get("end"));
    }

    /**
     * Spring 普通的调用
     */
    @Test
    public void order(){
        ChainParam request = new ChainParam().build();
        orderService.order(request);
    }

    /**
     * Spring 普通的调用
     */
    @Test
    public void payment(){
        ChainParam request = new ChainParam().build();
        paymentService.payment(request);
    }

    /**
     * SpringMVC 调用责任链
     */
    @Test
    public void dynamicChain(){
        ChainParam request = new ChainParam().build();
        request.setChannelType("c1-1");
        outWareHouseService.outWareHouse(request);
    }

}
