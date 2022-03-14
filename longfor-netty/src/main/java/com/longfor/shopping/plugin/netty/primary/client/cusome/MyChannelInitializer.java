package com.longfor.shopping.plugin.netty.primary.client.cusome;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 * 在实际应用场景里，只要是支持sokcet通信的都可以和Netty交互，比如中继器、下位机、PLC等。这些场景下就非常需要自定义编码解码器，
 * 来处理字节码传输，并控制半包、粘包以及安全问题。那么本章节我们通过实现ByteToMessageDecoder、MessageToByteEncoder来实现我们的需求。
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        //自定义解码器
        channel.pipeline().addLast(new MyDecoder());
        //自定义编码器
        channel.pipeline().addLast(new MyEncoder());
        //在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyServerHandler());
    }

}