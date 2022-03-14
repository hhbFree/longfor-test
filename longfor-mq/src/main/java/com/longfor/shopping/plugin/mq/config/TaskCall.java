package com.longfor.shopping.plugin.mq.config;

public interface TaskCall<E> {
	Object processTask(E args);
}
