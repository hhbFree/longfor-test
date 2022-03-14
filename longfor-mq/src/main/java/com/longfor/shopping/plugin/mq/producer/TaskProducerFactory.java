package com.longfor.shopping.plugin.mq.producer;

/**
 * Created by Coral on 7/5/15.
 */
public interface TaskProducerFactory {
	/**
	 * 生成一个用于处理eventId的TaskProducer
	 * @return
	 */
	TaskProducer getProducer(String eventId);

	TaskProducerCallable getProducerCallable(String eventId);
}
