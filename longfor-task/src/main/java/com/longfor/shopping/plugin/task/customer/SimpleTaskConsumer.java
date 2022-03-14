package com.longfor.shopping.plugin.task.customer;


import com.longfor.shopping.plugin.task.Task;
import com.longfor.shopping.plugin.task.TaskBeanUtils;
import com.longfor.shopping.plugin.task.producer.TaskInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * task 批量处理消费者
 */
public class SimpleTaskConsumer extends AbstractTaskConsumer {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public SimpleTaskConsumer() {
		//默认采用内存处理，增加内存实现
		putStorageInner(TaskStorageType.MEMORY_TYPE, new TaskQueueMemory());
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
	public boolean runTask(TaskQueue taskQueue, int partition, boolean memory) throws InterruptedException {
		List<TaskArgs> taskArgsList = taskQueue.poolList(partition);
		if (taskArgsList == null || taskArgsList.size() == 0) {
			return false;
		}
		CountDownLatch taskExecutor = null;
		if (!memory){
			taskExecutor = new CountDownLatch(taskArgsList.size());
		}

		for (TaskArgs taskArgs : taskArgsList) {
			TaskInstance taskInstance = TaskBeanUtils.getTaskInstance(taskArgs.getId());
			Task task = (Task) taskInstance.getBean();
			if (task != null) {
				try {
					Executor executor = taskInstance.getExecutor();
					if (executor == null) {
						executor = defaultExecutor;
					}
					CountDownLatch finalTaskExecutor = taskExecutor;
					executor.execute(() -> {
						try {
							task.processTask(taskArgs.getObject());
						} catch (Exception ex) {
							LOGGER.error("processTask {} failed {}", taskArgs.getEvent(), ex);
						} finally {
							if (!memory){
								finalTaskExecutor.countDown();
							}

						}
					});
				} catch (Exception ex) {
					if (!memory){
						taskExecutor.countDown();
					}
					LOGGER.error("When process task for event=" + taskArgs.getEvent() + " failed {}", ex);
				}
			} else {
				if (!memory){
					taskExecutor.countDown();
				}
				LOGGER.error("Unknown TaskImplementation event=", taskArgs.getEvent());
			}
		}
		if (!memory){
			taskExecutor.await();
		}
		taskQueue.delete(partition, taskArgsList);
		return true;
	}



}

