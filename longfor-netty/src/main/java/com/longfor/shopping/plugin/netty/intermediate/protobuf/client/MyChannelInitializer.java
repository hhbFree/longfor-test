package com.longfor.shopping.plugin.netty.intermediate.protobuf.client;

import com.longfor.shopping.plugin.netty.intermediate.protobuf.codec.ObjDecoder;
import com.longfor.shopping.plugin.netty.intermediate.protobuf.codec.ObjEncoder;
import com.longfor.shopping.plugin.netty.intermediate.protobuf.domain.MsgInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //对象传输处理
        channel.pipeline().addLast(new ObjDecoder(MsgInfo.class));
        channel.pipeline().addLast(new ObjEncoder(MsgInfo.class));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyClientHandler());
    }

}
