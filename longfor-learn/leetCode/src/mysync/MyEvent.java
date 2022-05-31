package mysync;

import design.listenter.demo4.EventHandler;
import unsafe.AtomicReferenceDemo2;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/8/18 9:06
 */
public class MyEvent<E> {

    private AtomicReferenceArray<EventHandler<E>> listener;



    private void addListener(EventHandler<E> eventHandler){
        listener.compareAndSet(0,null,eventHandler);
    }

}
