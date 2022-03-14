package com.longfor.shopping.plugin.netty.primary.udp.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;


/**
 * 在Netty通信中UDP的实现方式也非常简单，只要注意部分代码区别于TCP即可。本章节需要注意的知识点 ；NioDatagramChannel、ChannelOption.SO_BROADCAST
 *
 * Internet 协议集支持一个无连接的传输协议，
 * 该协议称为用户数据报协议（UDP，User Datagram Protocol）。
 * UDP 为应用程序提供了一种无需建立连接就可以发送封装的 IP 数据报的方法。
 * RFC 768 [1] 描述了 UDP。 Internet 的传输层有两个主要协议，互为补充。
 * 无连接的是 UDP，它除了给应用程序发送数据包功能并允许它们在所需的层次上架构自己的协议之外，
 * 几乎没有做什么特别的的事情。面向连接的是 TCP，该协议几乎做了所有的事情。
 *
 * 无状态的，不需要连接的
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)//TODO  NioDatagramChannel
                    .option(ChannelOption.SO_BROADCAST, true) //TODO    //广播
                    .option(ChannelOption.SO_RCVBUF, 2048 * 1024)// 设置UDP读缓冲区为2M
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024)// 设置UDP写缓冲区为1M
                    .handler(new MyChannelInitializer());

            ChannelFuture f = b.bind(7397).sync();
            System.out.println("itstack-demo-netty udp server start done. {关注公众号：bugstack虫洞栈，获取源码}");
            f.channel().closeFuture().sync();
        } finally {
            //优雅的关闭释放内存
            group.shutdownGracefully();
        }

    }

}