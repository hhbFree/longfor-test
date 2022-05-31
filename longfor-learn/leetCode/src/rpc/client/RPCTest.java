package rpc.client;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RPCTest {
 
    public static void main(String[] args) throws IOException {
        //TODO 获取到代理类
        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHi("test"));
    }
}