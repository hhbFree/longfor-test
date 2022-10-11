package com.longfor.anno.aqs;

import java.util.concurrent.CountDownLatch;

public class ThreadDemo {

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
        //等待一百个线程全部执行完
        end.await();
        System.out.println("result is :" + j);
    }
}