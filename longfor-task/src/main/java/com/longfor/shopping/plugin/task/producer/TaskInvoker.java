package com.longfor.shopping.plugin.task.producer;

public interface TaskInvoker {
	void invoke(TaskInvokerArgs taskInvokerArgs);

	Object invokeCallable(TaskInvokerArgs taskInvokerArgs);
}
