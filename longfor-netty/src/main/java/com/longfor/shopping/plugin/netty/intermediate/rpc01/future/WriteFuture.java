package com.longfor.shopping.plugin.netty.intermediate.rpc01.future;


import com.longfor.shopping.plugin.netty.intermediate.rpc01.msg.Response;

import java.util.concurrent.Future;

/**
 * extend future
 */
//TODO remote data 远程调用某个数据，可以封装一层，extends Future<T>
public interface WriteFuture<T> extends Future<T> {

    Throwable cause();

    void setCause(Throwable cause);

    boolean isWriteSuccess();

    void setWriteResult(boolean result);

    String requestId();

    T response();

    void setResponse(Response response);

    boolean isTimeout();


}
