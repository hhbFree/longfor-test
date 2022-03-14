//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.longfor.shopping.plugin.mq.queue.memery;

import com.longfor.shopping.plugin.mq.config.TaskArgs;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;


public class TaskQueuePriorityMemory implements TaskQueuePriority {
    private Queue<TaskArgs> queue = new PriorityBlockingQueue();

    public TaskQueuePriorityMemory() {
    }

    public void put(int partition, TaskArgs taskArgs) {
        taskArgs.setPriority(0);
        this.queue.add(taskArgs);
    }

    public List<TaskArgs> poolList(int partition) {
        List<TaskArgs> list = new ArrayList();

        for(int i = 0; i < this.getLimit(); ++i) {
            TaskArgs taskArgs = (TaskArgs)this.queue.poll();
            if (taskArgs == null) {
                break;
            }

            list.add(taskArgs);
        }

        return list;
    }

    public void putPriority(int partition, TaskArgs taskArgs) {
        taskArgs.setPriority(taskArgs.getPriority() + 1);
        this.queue.add(taskArgs);
    }

    public TaskArgs pool(int partition) {
        return (TaskArgs)this.queue.poll();
    }

    public void delete(int partition, TaskArgs taskArgs) {
    }

    public void delete(int partition, List<TaskArgs> list) {
    }

    public boolean isEmpty(int partition) {
        return this.queue.isEmpty();
    }

    private int getLimit() {
        return 128;
    }
}
