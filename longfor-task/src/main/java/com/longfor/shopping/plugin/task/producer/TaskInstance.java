package com.longfor.shopping.plugin.task.producer;

import com.longfor.shopping.plugin.task.customer.DedicatedTask;
import com.longfor.shopping.plugin.task.customer.TaskConsumerHandler;

import java.util.UUID;
import java.util.concurrent.Executor;

/**
 * Created by Coral on 7/28/15.
 */
public class TaskInstance implements TaskBean {

	private static TaskInvokerFactory taskInvokerFactory = null;

	private String id;
	private String event;
	private String storage;

	private Class<?> argClazz;
	private Executor executor;



	private Object bean;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public Class<?> getArgClazz() {
		return argClazz;
	}

	public void setArgClazz(Class<?> argClazz) {
		this.argClazz = argClazz;
	}

	public Executor getExecutor() {
		return executor;
	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	@Override
	public void consume(Object args) {
		if (this.getBean() instanceof DedicatedTask) {
			if (taskInvokerFactory == null){
				taskInvokerFactory = SpringContextUtil.getBean(TaskInvokerFactory.class);
			}
			TaskInvokerArgs taskInvokerArgs = new TaskInvokerArgs();
			taskInvokerArgs.setArgs(args);
			taskInvokerArgs.setEvent(event);
			taskInvokerArgs.setId(UUID.randomUUID().toString());
            taskInvokerFactory.getInvoker().invoke(taskInvokerArgs);
		} else {
			TaskConsumerHandler.getInstance().consume(this, args);
		}

	}

	@Override
	public Object consumeCallable(Object args) {

		return TaskConsumerHandler.getInstance().consumeCallable(this, args);
	}
}
