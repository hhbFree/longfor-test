package com.longfor.shopping.plugin.task;


import com.longfor.shopping.plugin.task.producer.SpringContextUtil;
import com.longfor.shopping.plugin.task.producer.TaskInstance;

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
