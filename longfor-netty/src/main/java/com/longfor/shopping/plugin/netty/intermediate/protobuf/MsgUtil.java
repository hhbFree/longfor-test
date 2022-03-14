package com.longfor.shopping.plugin.netty.intermediate.protobuf;

import com.longfor.shopping.plugin.netty.intermediate.protobuf.domain.MsgInfo;

public class MsgUtil {

    public static MsgInfo buildMsg(String channelId, String msgContent) {
        return new MsgInfo(channelId, msgContent);
    }

}