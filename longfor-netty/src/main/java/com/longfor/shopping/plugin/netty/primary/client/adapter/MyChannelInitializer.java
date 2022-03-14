package com.longfor.shopping.plugin.netty.primary.client.adapter;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * ChannelInboundHandler拦截和处理入站事件，ChannelOutboundHandler拦截和处理出站事件。
 * ChannelHandler和ChannelHandlerContext通过组合或继承的方式关联到一起成对使用。
 * 事件通过ChannelHandlerContext主动调用如read(msg)、write(msg)和fireXXX()等方法，
 * 将事件传播到下一个处理器。注意：入站事件在ChannelPipeline双向链表中由头到尾正向传播，
 * 出站事件则方向相反。 当客户端连接到服务器时，Netty新建一个ChannelPipeline处理其中的事件，
 * 而一个ChannelPipeline中含有若干ChannelHandler。如果每个客户端连接都新建一个ChannelHandler实例，
 * 当有大量客户端时，服务器将保存大量的ChannelHandler实例。为此，Netty提供了Sharable注解，
 * 如果一个ChannelHandler状态无关，那么可将其标注为Sharable，如此，服务器只需保存一个实例就能处理所有客户端的事件。
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 基于换行符号
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        channel.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        channel.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyOutMsgHandler());//消息出站处理器，在Client发送消息时候会触发此处理器
        channel.pipeline().addLast(new MyInMsgHandler()); //消息入站处理器
    }
}