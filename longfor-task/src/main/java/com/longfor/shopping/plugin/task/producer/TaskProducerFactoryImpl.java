package com.longfor.shopping.plugin.task.producer;


import com.longfor.shopping.plugin.task.callable.TaskProducerCallable;

/**
 * Created by Coral on 7/5/15.
 */
public class TaskProducerFactoryImpl implements TaskProducerFactory {
	private TaskRouter router;

	public TaskProducerFactoryImpl() {
		router = new TaskRouter();
	}

	@Override
	public TaskProducer getProducer(String eventId) {
		TaskRouter.TaskRouteEntry routeEntry = router.getEntry(eventId);
		if (routeEntry == null) {
			throw new IllegalArgumentException("Unknown event id=" + eventId);
		}
		return new TaskProducer() {
			@Override
			public void produce(Object args) {
				 routeEntry.consume(args);
			}
		};
	}

	@Override
	public TaskProducerCallable getProducerCallable(String eventId) {
		TaskRouter.TaskRouteEntry routeEntry = router.getEntry(eventId);
		if (routeEntry == null) {
			throw new IllegalArgumentException("Unknown event id=" + eventId);
		}
		return new TaskProducerCallable() {
			@Override
			public Object produce(Object args) {
				return routeEntry.consumeCall(args);
			}
		};
	}
}
