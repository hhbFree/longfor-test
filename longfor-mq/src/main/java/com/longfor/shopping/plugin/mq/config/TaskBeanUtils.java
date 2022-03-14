package com.longfor.shopping.plugin.mq.config;

public class TaskBeanUtils {
	public static String getBeanInstance(String name){
		return name;
	}

	public static String getBeanImpl(String name){
		return name + ":exc";
	}

	public static TaskInstance getTaskInstance(String beanName) {
		TaskInstance taskBeanInstance = SpringContextUtil.getBean(TaskBeanUtils.getBeanInstance(beanName), TaskInstance.class);
		taskBeanInstance.setBean(SpringContextUtil.getBean(TaskBeanUtils.getBeanImpl(beanName)));
		return taskBeanInstance;
	}

}
