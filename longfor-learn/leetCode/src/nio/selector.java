package nio;



import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 选择器（Selector） 是 SelectableChannle 对象的多路复用器，
 * Selector 可以同时监控多个 SelectableChannel 的 IO 状况，
 * 也就是说，利用 Selector可使一个单独的线程管理多个 Channel，selector 是非阻塞 IO 的核心。
 *
 * 选择器（Selector）的应用：
 * 当通道使用register（Selector sel, int ops）方法将通道注册选择器时，选择器对通道事件进行监听，
 * 通过第二个参数指定监听的事件类型。
 *
 * 其中可监听的事件类型包括以下：
 *
 * 　　读 : SelectionKey.OP_READ （1）
 *
 * 　　写 : SelectionKey.OP_WRITE （4）
 *
 * 　　连接 : SelectionKey.OP_CONNECT （8）
 *
 * 　　接收 : SelectionKey.OP_ACCEPT （16）
 *
 * 如果需要监听多个事件是：
 *
 * 　　int key = SelectionKey.OP_READ | SelectionKey.OP_WRITE ; //表示同时监听读写操作
 */
public class selector {

    /**
     * 客户端
     */
    public static void main(String[] args)  throws Exception{


        //1. 获取socketChannel
        SocketChannel sChannel = SocketChannel.open();
        //2. 创建连接
        sChannel.connect(new InetSocketAddress("127.0.0.1", 9898));
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //3. 设置通道为非阻塞
        sChannel.configureBlocking(false);

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String msg = scanner.nextLine();

            buf.put((new Date() + "：" + msg).getBytes());
            buf.flip();
            //4. 向通道写数据
            sChannel.write(buf);
            buf.clear();
        }
    }


    /**
     * 服务端
     */
    @Test
    public void server() throws Exception{
        //1. 获取服务端通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.bind(new InetSocketAddress(9898));
        //2. 设置为非阻塞模式
        ssChannel.configureBlocking(false);

        //3. 打开一个监听器
        Selector selector = Selector.open();
        //4. 向监听器注册接收事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            //5. 获取监听器上所有的监听事件值
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            //6. 如果有值
            while (it.hasNext()) {
                //7. 取到SelectionKey
                SelectionKey key = it.next();

                //8. 根据key值判断对应的事件
                if (key.isAcceptable()) {
                    //9. 接入处理
                    SocketChannel socketChannel = ssChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    //10. 可读事件处理
                    SocketChannel channel = (SocketChannel) key.channel();
                    readMsg(channel);
                }
                //11. 移除当前key
                it.remove();
            }
        }
    }
    private void readMsg(SocketChannel channel) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int len = 0;
        while ((len = channel.read(buf)) > 0) {
            buf.flip();
            byte[] bytes = new byte[1024];
            buf.get(bytes, 0, len);
            System.out.println(new String(bytes, 0, len));
        }
    }
}
