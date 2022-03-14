package com.longfor.shopping.plugin.netty.intermediate.keep.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

/**
 *在我们使用netty中，需要监测服务是否稳定以及在网络异常链接断开时候可以自动重连。需要实现监听；f.addListener(new MyChannelFutureListener())
 */
public class MyChannelFutureListener implements ChannelFutureListener {
    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if (channelFuture.isSuccess()) {
            System.out.println("23333333 itstack-demo-netty client start done.");
            return;
        }
        final EventLoop loop = channelFuture.channel().eventLoop();
        loop.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    new NettyClient().connect("127.0.0.1", 7397);
                    System.out.println("23333333  itstack-demo-netty client start done. ");
                    Thread.sleep(500);
                } catch (Exception e){
                    System.out.println("23333333  itstack-demo-netty client start error go reconnect :"+e.getMessage());
                }
            }
        }, 1L, TimeUnit.SECONDS);
    }
}
