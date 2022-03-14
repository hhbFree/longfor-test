package com.longfor.shopping.plugin.mq.queue.memery;

import com.longfor.shopping.plugin.mq.config.TaskArgs;
import com.longfor.shopping.plugin.mq.queue.TaskQueue;

public interface TaskQueuePriority extends TaskQueue {
	String BEAN_ID = "helium:TaskQueuePriority";


    void putPriority(int partition, TaskArgs taskArgs);

}
