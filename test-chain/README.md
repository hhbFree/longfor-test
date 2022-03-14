#### 1.说明

整合bean对象与责任链，支持SpringMVC默认分层模型，支持bean顺序的外部化配置，支持代码层面自定义动态配置

参与责任链添加注解`@Chain`；实现接口`AbstractChainService`



#### 2.结构

`AbstractChainService` 

- deal 处理请求
- match 判断是否路由

 `ShopChain`

- request 获取通用参数
- proceed 转发request

3.单元测试

```java

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
```

