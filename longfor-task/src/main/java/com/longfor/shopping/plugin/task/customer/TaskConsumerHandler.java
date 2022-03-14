package com.longfor.shopping.plugin.task.customer;


import com.longfor.shopping.plugin.task.producer.TaskInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TaskConsumerHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskConsumerHandler.class);


	private static TaskConsumerHandler INS = new TaskConsumerHandler();

	private Map<String, TaskInstance> tasks;

	private SimpleDedicatedTaskConsumer dedicatedTaskConsumer;

	private SimpleTaskConsumer simpleTaskConsumer;


	private TaskConsumerHandler() {
		tasks = new ConcurrentHashMap<>();
//		dedicatedTaskConsumer = new SimpleDedicatedTaskConsumer();
		simpleTaskConsumer = new SimpleTaskConsumer();
	}

	public static TaskConsumerHandler getInstance(){
		return INS;
	}


	public void consume(TaskInstance task, Object args) {
		LOGGER.info("TaskConsumerHandler.consume:{}", task.getBean());
		if (task.getBean() instanceof DedicatedTask){
//			dedicatedTaskConsumer.consume(task, (DedicatedTaskArgs) args);
		} else {
			simpleTaskConsumer.consume(task, args);
		}
	}

	public Object consumeCallable(TaskInstance task, Object args) {
		LOGGER.info("TaskConsumerHandler.consume:{}", task.getBean());
			return simpleTaskConsumer.consumeCall(task, args);
	}

	public void putDtStorage(String stoageType, TaskQueuePriority taskQueue) {
		dedicatedTaskConsumer.putStorageInner(stoageType, taskQueue);
	}


	public TaskInstance getTaskInstance(String beanId) {
		TaskInstance task = tasks.get(beanId);
		return task;
	}


	public SimpleDedicatedTaskConsumer getDedicatedTaskConsumer() {
		return dedicatedTaskConsumer;
	}
}
