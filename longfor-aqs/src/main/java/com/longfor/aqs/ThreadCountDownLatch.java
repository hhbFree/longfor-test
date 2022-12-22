package com.longfor.aqs;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch(int count)：count为计数器的初始值（一般需要多少个线程执行，count就设为几）。
 * countDown(): 每调用一次计数器值-1，直到count被减为0，代表所有线程全部执行完毕。
 * getCount()：获取当前计数器的值。
 * await(): 等待计数器变为0，即等待所有异步线程执行完毕。
 * boolean await(long timeout, TimeUnit unit)： 此方法与await()区别：
 * ①此方法至多会等待指定的时间，超时后会自动唤醒，若 timeout 小于等于零，则不会等待
 * ②boolean 类型返回值：若计数器变为零了，则返回 true；若指定的等待时间过去了，则返回 false
 * ————————————————
 * 版权声明：本文为CSDN博主「三寸旧城。」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/liu_da_da/article/details/124983187
 */
public class ThreadCountDownLatch  {

    private static int j = 0;


    static class IncrTask implements Runnable {
        CountDownLatch start;
        CountDownLatch end;

        public IncrTask(CountDownLatch start, CountDownLatch end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                start.await();
                for (int i = 0; i < 100; i++) {
                    j++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                end.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new IncrTask(start, end));
            t.start();
        }
        //模拟100个并发
        start.countDown();
        //等待一百个线程全部执行完,输出结果
        end.await();
        System.out.println("result is :" + j);
    }
}