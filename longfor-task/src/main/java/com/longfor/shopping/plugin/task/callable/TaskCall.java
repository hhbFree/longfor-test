package com.longfor.shopping.plugin.task.callable;

/**
 * 简单task任务
 * 以下几种场景的基础类
 * 1. 某种用于解耦的任务
 * 2. 用于处理Message Queue的任务
 * 3. 定时任务
 */
public interface TaskCall<E> {
	Object processTask(E args);
}
