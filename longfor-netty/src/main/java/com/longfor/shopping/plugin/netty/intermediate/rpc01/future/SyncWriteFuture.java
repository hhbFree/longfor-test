package com.longfor.shopping.plugin.netty.intermediate.rpc01.future;


import com.longfor.shopping.plugin.netty.intermediate.rpc01.msg.Response;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *  SyncWriteFuture 实现WriteFuture
 */
public class SyncWriteFuture implements WriteFuture<Response> {

    private CountDownLatch latch = new CountDownLatch(1);
    private final long begin = System.currentTimeMillis();

    //配置时间
    private long timeout;
    private Response response;
    private final String requestId;
    private boolean writeResult;
    private Throwable cause;
    private boolean isTimeout = false;

    public SyncWriteFuture(String requestId) {
        this.requestId = requestId;
    }

    public SyncWriteFuture(String requestId, long timeout) {
        this.requestId = requestId;
        this.timeout = timeout;
        writeResult = true;
        isTimeout = false;
    }


    public Throwable cause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public boolean isWriteSuccess() {
        return writeResult;
    }

    public void setWriteResult(boolean result) {
        this.writeResult = result;
    }

    public String requestId() {
        return requestId;
    }

    //TODO response方法返回数据
    public Response response() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
        latch.countDown();
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return true;
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return false;
    }

    public Response get() throws InterruptedException, ExecutionException {
        latch.wait();
        return response;
    }

    public Response get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {

        //TODO remote data CountDownLatch 配置获取时间是否超时
        if (latch.await(timeout, unit)) {
            return response;
        }
        return null;
    }

    //
    public boolean isTimeout() {
        if (isTimeout) {
            return isTimeout;
        }
        return System.currentTimeMillis() - begin > timeout;
    }
}
