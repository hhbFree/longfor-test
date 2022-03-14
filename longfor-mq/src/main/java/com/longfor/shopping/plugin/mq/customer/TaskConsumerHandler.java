package com.longfor.shopping.plugin.mq.customer;


import com.longfor.shopping.plugin.mq.config.TaskInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TaskConsumerHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskConsumerHandler.class);


    private static TaskConsumerHandler INS = new TaskConsumerHandler();

    private Map<String, TaskInstance> tasks;

    private SimpleTaskConsumer simpleTaskConsumer;


    private TaskConsumerHandler() {
        tasks = new ConcurrentHashMap<>();
        //初始化，启动消费者
        simpleTaskConsumer = new SimpleTaskConsumer();
    }

    public static TaskConsumerHandler getInstance() {
        return INS;
    }


    public void consume(TaskInstance task, Object args) {
        LOGGER.info("TaskConsumerHandler.consume:{}", task.getBean());
        simpleTaskConsumer.consume(task, args);

    }

    public Object consumeCallable(TaskInstance task, Object args) {
        LOGGER.info("TaskConsumerHandler.consume:{}", task.getBean());
        return simpleTaskConsumer.consumeCall(task, args);
    }

    public TaskInstance getTaskInstance(String beanId) {
        TaskInstance task = tasks.get(beanId);
        return task;
    }
}
