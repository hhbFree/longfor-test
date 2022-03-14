package com.longfor.shopping.plugin.netty.primary.server.encode;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;


/**
 * netty通信就向一个流水channel管道，我们可以在管道的中间插入一些‘挡板’为我们服务。比如字符串的编码解码，
 * 在前面我们使用new StringDecoder(Charset.forName(“GBK”))进行字符串解码，这样我们在收取数据就不需要手动处理字节码。
 * 那么本章节我们使用与之对应的new StringEncoder(Charset.forName(“GBK”))进行进行字符串编码，用以实现服务端在发送数据的时候只需要传输字符串内容即可。
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        // 基于换行符号
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // TODO 解使用new StringDecoder(Charset.forName(“GBK”))进行字符串解码  客户端接受的数据 ===> 服务端接受的数据
        channel.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));


        //TODO new StringEncoder(Charset.forName(“GBK”))进行进行字符串编码  , 客户端接受的数据 <=== 服务端接受的数据
        channel.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyServerHandler());
    }

}