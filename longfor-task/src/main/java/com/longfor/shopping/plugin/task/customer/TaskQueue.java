package com.longfor.shopping.plugin.task.customer;


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
