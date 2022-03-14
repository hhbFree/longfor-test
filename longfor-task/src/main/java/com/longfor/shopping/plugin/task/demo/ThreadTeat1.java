package com.longfor.shopping.plugin.task.demo;

import java.util.concurrent.TimeUnit;

public class ThreadTeat1 {
    /*volatile*/ boolean flag = true;

    public void run(){

        System.out.println("start");
        while (flag){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("flag:"+flag);
//            synchronized (this){}
        }
        System.out.println("end");
    }

    public synchronized void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        ThreadTeat1 t = new ThreadTeat1();
        new Thread(t::run).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        t.flag = false;
        t.stop();
        System.out.println("main end");
    }
}