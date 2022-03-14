package com.longfor.shopping.plugin.netty.primary.server.decode;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * 解码器
 *
 *
 * 在netty中是否可以自动的把接收的Bytebuf数据转String，不需要我手动处理？ 答；有，可以在管道中添加一个StringDecoder。
 * 在网络传输过程中有半包粘包的问题，netty能解决吗？ 答：能，netty提供了很丰富的解码器，在正确合理的使用下就能解决半包粘包问题。
 * 常用的String字符串下有什么样的解码器呢？ 答：不仅在String下有处理半包粘包的解码器在处理其他的数据格式也有，其中谷歌的protobuf数据格式就是其中一个。对于String的有以下常用的三种：
 * LineBasedFrameDecoder 基于换行
 * DelimiterBasedFrameDecoder 基于指定字符串
 * FixedLengthFrameDecoder 基于字符串长度
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        /* 解码器 */
        // 基于换行符号
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 基于指定字符串【换行符，这样功能等同于LineBasedFrameDecoder】
        // e.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, false, Delimiters.lineDelimiter()));
        // 基于最大长度
        // e.pipeline().addLast(new FixedLengthFrameDecoder(4));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        //TODO 在netty中是否可以自动的把接收的Bytebuf数据转String
        channel.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
        //在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyServerHandler());
    }

}