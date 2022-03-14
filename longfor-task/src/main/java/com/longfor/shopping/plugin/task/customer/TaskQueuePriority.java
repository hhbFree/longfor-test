package com.longfor.shopping.plugin.task.customer;

public interface TaskQueuePriority extends TaskQueue{
	String BEAN_ID = "helium:TaskQueuePriority";


    void putPriority(int partition, TaskArgs taskArgs);

}
