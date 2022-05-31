package design.listenter.demo4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/6/10 15:04
 */
public class Test2  implements Runnable{

    private static Lock lock=new ReentrantLock();
    public static void main(String[] args) throws Exception {
        new Thread(new Test2()).start();
        Thread.sleep(222);
        new Thread(new Test2()).start();
    }


    @Override
    public void run() {
        lock.lock();
        System.out.println("睡眠:"+Thread.currentThread().getName());
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }


    public static void get(){

    }
}
