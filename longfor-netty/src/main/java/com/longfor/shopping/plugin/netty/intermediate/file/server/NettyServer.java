package com.longfor.shopping.plugin.netty.intermediate.file.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 在实际应用中我们经常使用到网盘服务，他们可以高效的上传下载较大文件。那么这些高性能文件传输服务，都需要实现的分片发送、断点续传功能。
 * 在Java文件操作中有RandomAccessFile类，他可以支持文件的定位读取和写入，这样就满足了我们对文件分片的最基础需求。
 * Netty服务端启动后，可以向客户端发送文件传输指令；允许接收文件、控制读取位点、记录传输标记、文件接收完成。
 * 为了保证传输性能我们采用protostuff二进制流进行传输。
 * 读取文件的时候需要注意，我们设定byte[1024]为默认读取范围，但当读取到最后的时候可能不足1024个字节，就会出现空字节。这个时候需要去掉空字节，否则我们的文件写入会多额外信息，导致文件不能打开｛zip、war、exe、jar等｝。
 */
public class NettyServer {

    //配置服务端NIO线程组
    private EventLoopGroup parentGroup = new NioEventLoopGroup(); //NioEventLoopGroup extends MultithreadEventLoopGroup Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
    private EventLoopGroup childGroup = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture bing(int port) {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)    //非阻塞模式
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new MyChannelInitializer());
            channelFuture = b.bind(port).syncUninterruptibly();
            this.channel = channelFuture.channel();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                System.out.println("itstack-demo-netty server start done. {关注公众号：bugstack虫洞栈，获取源码}");
            } else {
                System.out.println("itstack-demo-netty server start error. {关注公众号：bugstack虫洞栈，获取源码}");
            }
        }
        return channelFuture;
    }

    public void destroy() {
        if (null == channel) return;
        channel.close();
        parentGroup.shutdownGracefully();
        childGroup.shutdownGracefully();
    }

    public Channel getChannel() {
        return channel;
    }

}
