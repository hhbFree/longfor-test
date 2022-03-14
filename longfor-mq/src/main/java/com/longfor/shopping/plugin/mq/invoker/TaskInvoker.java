package com.longfor.shopping.plugin.mq.invoker;

public interface TaskInvoker {
	void invoke(TaskInvokerArgs taskInvokerArgs);

	Object invokeCallable(TaskInvokerArgs taskInvokerArgs);
}
