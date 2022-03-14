package com.longfor.shop.asm.code;

import org.springframework.util.StopWatch;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("业务查询");
        doSomething();
        stopWatch.stop();

        stopWatch.start("业务计算处理");
        doSomething();
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
    
    // 模拟业务处理的过程...
    private static void doSomething() {
        int time = Math.abs(new Random().nextInt(2000));
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

