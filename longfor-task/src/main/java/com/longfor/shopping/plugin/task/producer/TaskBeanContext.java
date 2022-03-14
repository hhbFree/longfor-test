package com.longfor.shopping.plugin.task.producer;


import com.longfor.shopping.plugin.task.listener.BeanContext;

/**
 * Created by Coral on 9/12/15.
 */
public interface TaskBeanContext extends BeanContext {
	/**
	 * 获取事件名称
	 * @return
	 */
	String getEventName();

	/**
	 * 获取存储类型
	 * @return
	 */
	String getStorageType();

	/**
	 * 消费一个args
	 * @param args
	 */
	void consume(Object args);
}
