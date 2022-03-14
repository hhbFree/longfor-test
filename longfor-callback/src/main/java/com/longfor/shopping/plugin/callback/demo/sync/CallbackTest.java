package com.longfor.shopping.plugin.callback.demo.sync;

public class CallbackTest {

    public static void main(String[] args) {
        People people = new People();

        //同步
        Callback callback = new Callback() {
            @Override
            public void printFinished(String msg) {
                System.out.println("打印机告诉我的消息是 ---> " + msg);
            }
        };
        System.out.println("需要打印的内容是 ---> " + "打印一份简历");
        people.goToPrintSyn(callback, "打印一份简历");
        System.out.println("我在等待 打印机 给我反馈");

        //异步
        Callback callback2 = new Callback() {
            @Override
            public void printFinished(String msg) {
                System.out.println("打印机告诉我的消息是 ---> " + msg);
            }
        };
        System.out.println("需要打印的内容是 ---> " + "打印一份简历");
        people.goToPrintASyn(callback2, "打印一份简历");
        System.out.println("我在等待 打印机 给我反馈");
    }
}
