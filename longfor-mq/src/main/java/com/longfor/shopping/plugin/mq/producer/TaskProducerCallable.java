package com.longfor.shopping.plugin.mq.producer;

public interface TaskProducerCallable<E> {
    Object produce(E args);
}
