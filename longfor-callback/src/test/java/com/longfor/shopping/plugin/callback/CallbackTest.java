package com.longfor.shopping.plugin.callback;

import com.longfor.shopping.plugin.callback.demo.ano.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallbackTest {


    @Resource
    private MyService myService;

    @Test
    public void test1() {
        // 接口设置监听器
        myService.setWorkListener(name -> {

            System.out.println("Start2 work for " + name + " !");

        });
//    // lambda 表达式设置监听器
//    myService.setWorkListener(name -> System.out.println("Start work for " + name + " !"));
        // 工作
        myService.work("boss");
    }

    @Test
    public void test2() {
        myService.work("boss");
    }


}
