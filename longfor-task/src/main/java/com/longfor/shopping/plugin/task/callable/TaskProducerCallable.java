package com.longfor.shopping.plugin.task.callable;

public interface TaskProducerCallable<E> {
    Object produce(E args);
}
