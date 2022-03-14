package com.longfor.shopping.plugin.netty.intermediate.file.client;

import com.longfor.shopping.plugin.netty.intermediate.file.codec.ObjDecoder;
import com.longfor.shopping.plugin.netty.intermediate.file.codec.ObjEncoder;
import com.longfor.shopping.plugin.netty.intermediate.file.domain.FileTransferProtocol;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：bugstack虫洞栈  ｛获取学习源码｝
 * 虫洞群：5360692
 * Create by fuzhengwei on @2019
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //对象传输处理
        channel.pipeline().addLast(new ObjDecoder(FileTransferProtocol.class));
        channel.pipeline().addLast(new ObjEncoder(FileTransferProtocol.class));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyClientHandler());
    }

}
