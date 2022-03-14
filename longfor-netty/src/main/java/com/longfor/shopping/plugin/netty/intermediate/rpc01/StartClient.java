package com.longfor.shopping.plugin.netty.intermediate.rpc01;

import com.alibaba.fastjson.JSON;
import com.longfor.shopping.plugin.netty.intermediate.rpc01.client.ClientSocket;
import com.longfor.shopping.plugin.netty.intermediate.rpc01.future.SyncWrite;
import com.longfor.shopping.plugin.netty.intermediate.rpc01.msg.Request;
import com.longfor.shopping.plugin.netty.intermediate.rpc01.msg.Response;
import io.netty.channel.ChannelFuture;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：bugstack虫洞栈  ｛关注获取学习源码｝
 * 虫洞群：①群5398358 ②群5360692
 * Create by fuzhengwei on 2019
 */
public class StartClient {

    private static ChannelFuture future;

    public static void main(String[] args) {
        System.out.println("hi 微信公众号：bugstack虫洞栈");
        ClientSocket client = new ClientSocket();
        new Thread(client).start();

        while (true) {
            try {
                //获取future，线程有等待处理时间
                if (null == future) {
                    future = client.getFuture();
                    Thread.sleep(500);
                    continue;
                }
                //构建发送参数
                Request request = new Request();
                request.setResult("查询｛bugstack虫洞栈｝用户信息");
                SyncWrite s = new SyncWrite();
                Response response = s.writeAndSync(future.channel(), request, 5000);
                System.out.println("调用结果：" + JSON.toJSON(response));
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

