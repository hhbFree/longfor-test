package com.longfor.shopping.plugin.task.invoker;


import com.longfor.shopping.plugin.task.TaskBeanUtils;
import com.longfor.shopping.plugin.task.customer.TaskConsumerHandler;
import com.longfor.shopping.plugin.task.producer.TaskInstance;
import com.longfor.shopping.plugin.task.producer.TaskInvoker;
import com.longfor.shopping.plugin.task.producer.TaskInvokerArgs;

public class TaskInvokerImpl implements TaskInvoker {
	@Override
	public void invoke(TaskInvokerArgs taskInvokerArgs) {
		TaskInstance taskInstance = TaskBeanUtils.getTaskInstance(taskInvokerArgs.getEvent());
		TaskConsumerHandler.getInstance().consume(taskInstance, taskInvokerArgs.getArgs());
	}

	@Override
	public Object invokeCallable(TaskInvokerArgs taskInvokerArgs) {
		TaskInstance taskInstance = TaskBeanUtils.getTaskInstance(taskInvokerArgs.getEvent());
		return TaskConsumerHandler.getInstance().consumeCallable(taskInstance, taskInvokerArgs.getArgs());
	}


}
