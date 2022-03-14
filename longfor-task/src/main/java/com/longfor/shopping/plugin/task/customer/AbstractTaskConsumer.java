package com.longfor.shopping.plugin.task.customer;


import com.longfor.shopping.plugin.task.Task;
import com.longfor.shopping.plugin.task.TaskBeanUtils;
import com.longfor.shopping.plugin.task.callable.TaskCall;
import com.longfor.shopping.plugin.task.producer.TaskInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

/**
 * task 通用消费者处理逻辑
 */
public abstract class AbstractTaskConsumer {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    protected final int TASK_EXECUTOR_SIZE = 16;

    protected final int TASK_EXECUTOR_QUEUE_SIZE = 16 * 64;

    protected Map<String, TaskQueue> queueMap;

    protected Map<String, TaskQueue> queueMapCall;

    ExecutorService defaultExecutor = Executors.newFixedThreadPool(2);

    public AbstractTaskConsumer() {
        //Task消费处理
        queueMap = new ConcurrentHashMap<>();
    }


    /**
     * 处理单队列设置
     *
     * @param stoageType
     * @param taskQueue
     */
    public synchronized void putStorageInner(String stoageType, TaskQueue taskQueue) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        boolean entity = true;
                        //处理内存队列-不采用分片处理
                        if (taskQueue instanceof TaskQueueMemory || taskQueue instanceof TaskQueuePriorityMemory) {
                            if (runTask(taskQueue, 0, true)) {
                                entity = false;
                            }
                        } else {
                            List<PartitionBean> beanList = TaskConsumerAssignor.getAssignor();
                            for (PartitionBean partitionBean : beanList) {
                                if (runTask(taskQueue, partitionBean.getIndex(), false)) {
                                    entity = false;
                                }
                            }
                        }
                        if (entity) {
                            Thread.sleep(500);
                        }
                    } catch (Throwable t) {
                        LOGGER.error("TaskManager run failed:", t);
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        queueMap.put(stoageType, taskQueue);
    }


    public void consume(TaskInstance task, Object args) {
        TaskArgs taskArgs = new TaskArgs();
        taskArgs.setEvent(task.getEvent());

        taskArgs.setObject(args);
        taskArgs.setId(task.getEvent());
        TaskQueue queue = queueMap.get(task.getStorage());
        if (queue == null) {
            LOGGER.warn("task.getStorageType() Must Set TaskQueue:{}. And Use MEMORY", task.getStorage());
            queue = queueMap.get(TaskStorageType.MEMORY_TYPE);
        }
        int partition = new Random().nextInt(TaskConsumerAssignor.getPartition());
        if (args instanceof DedicatedTaskArgs) {
            DedicatedTaskArgs dedicatedTaskArgs = (DedicatedTaskArgs) args;
            taskArgs.setTag(dedicatedTaskArgs.getTag());
            partition = getIntCode(TaskConsumerAssignor.getPartition(), dedicatedTaskArgs.getTag());
        }
        queue.put(partition, taskArgs);
    }


    public Object consumeCall(TaskInstance taskInstance, Object args) {
        TaskCall task = (TaskCall) taskInstance.getBean();
            Future<?> submit = defaultExecutor.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    return task.processTask(args);
                }
            });
		try {
			return submit.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}


    /**
     * 计算用户归属区块
     *
     * @param partition
     * @param value
     * @return
     */
    public int getIntCode(int partition, String value) {
        int h = 0;
        if (h == 0 && value.length() > 0) {
            char val[] = value.toCharArray();
            for (int i = 0; i < value.length(); i++) {
                h = 31 * h + val[i];
            }
        }
        return Math.abs(h % partition);
    }


    /**
     * runask返回用来区分是否存在task运行
     *
     * @param taskQueue
     * @param partition
     * @param memory
     * @return
     * @throws InterruptedException
     */
    abstract public boolean runTask(TaskQueue taskQueue, int partition, boolean memory) throws InterruptedException;


}

