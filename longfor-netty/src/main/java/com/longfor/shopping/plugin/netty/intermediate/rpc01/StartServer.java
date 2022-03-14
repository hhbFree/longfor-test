package com.longfor.shopping.plugin.netty.intermediate.rpc01;


import com.longfor.shopping.plugin.netty.intermediate.rpc01.server.ServerSocket;

/**
 * 在我们实现开发RPC框架的时候，需要选择socket的通信方式。而我们知道一般情况下socket通信类似与qq聊天，发过去消息，
 * 什么时候回复都可以。但是我们RPC框架通信，从感觉上类似http调用，需要在一定时间内返回，否则就会发生超时断开。
 * 这里我们选择netty作为我们的socket框架，采用future方式进行通信。
 */
public class StartServer {

    public static void main(String[] args) {
        new Thread(new ServerSocket()).start();
        System.out.println("itstack-demo-netty server start done. {关注公众号：bugstack虫洞栈，获取源码}");
    }

}
