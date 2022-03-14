package com.longfor.shopping.plugin.mq;


import com.longfor.shopping.plugin.mq.config.TaskEvent;
import com.longfor.shopping.plugin.mq.producer.TaskProducer;
import com.longfor.shopping.plugin.mq.producer.TaskProducerCallable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTest {


    @TaskEvent("aaa")
    private TaskProducer taskProducer;

    @TaskEvent(value = "bbb",type = "call")
    private TaskProducerCallable taskProducerCallable;

    @Test
    public void test(){
        taskProducer.produce("sss");
        System.out.println();
    }

    @Test
    public void call(){
        Object aa = taskProducerCallable.produce("aa");
        System.out.println(aa.toString());
    }


}
