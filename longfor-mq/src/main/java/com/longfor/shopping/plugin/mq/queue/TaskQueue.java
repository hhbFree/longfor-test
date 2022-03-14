package com.longfor.shopping.plugin.mq.queue;


import com.longfor.shopping.plugin.mq.config.TaskArgs;

import java.util.List;

public interface TaskQueue {
	String BEAN_ID = "helium:TaskQueue";

	void put(int partition, TaskArgs taskArgs);

	List<TaskArgs> poolList(int partition);
	TaskArgs pool(int partition);
	void delete(int partition, TaskArgs taskArgs);
	void delete(int partition, List<TaskArgs> list);
	boolean isEmpty(int partition);
}
