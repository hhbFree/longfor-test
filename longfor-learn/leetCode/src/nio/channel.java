package nio;



import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *     FileChannel：用于读取、写入、映射和操作文件的通道。
 *
 * 　　DatagramChannel：通过 UDP 读写网络中的数据通道。
 *
 * 　　SocketChannel：通过 TCP 读写网络中的数据。
 *
 * 　　ServerSocketChannel：可以监听新进来的 TCP 连接，对每一个新进来的连接都会创建一个 SocketChannel。
 *
 * 　　以上Channel都实现或者继承了相应的Channel读写接口或者读写抽象类，所以都是可读写的。
 *      但是因为FileChannel可以根据FileInputStream或者FileOutputStream获取，
 *      所以当根据以上类获取的FileChennel进行读或者写的时候会抛出异常。
 */
public class channel {

    @Test
    public void test() throws IOException {
        /**
         * 文件获取channel
         */
        //1. 使用FileInputStream获取FileChannel
        FileInputStream fis = new FileInputStream("d:\\1.txt");
        FileChannel fChannel = fis.getChannel();
        //2. 使用FileOutputStream获取FileChannel
        FileOutputStream ois = new FileOutputStream("d:\\1.txt");
        FileChannel fChannel1 = ois.getChannel();
        //3， 使用RandomAccessFile对象获取
        RandomAccessFile raf = new RandomAccessFile("d:\\1.txt", "rw");
        FileChannel fChannel2 = raf.getChannel();
        //4. FileChannel的open方法打开
        FileChannel fChannel3 = FileChannel.open(Paths.get("d:\\1.txt"), StandardOpenOption.READ,StandardOpenOption.WRITE);

        /**
         * 网络编程获取channel
         */
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        DatagramChannel datagramChannel = DatagramChannel.open();
    }
    
    @Test
    public void test2() throws Exception{
        //1.申请内存空间
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        FileInputStream fileInputStream=new FileInputStream("E:\\EE.txt");
        FileChannel inputChannel=fileInputStream.getChannel();

        FileOutputStream fileOutputStream=new FileOutputStream("E:\\GG.txt");
        FileChannel outFileChannel=fileOutputStream.getChannel();

        int len=-1;
        while ((len=inputChannel.read(byteBuffer))!=-1){
            //更换成读模式
            byteBuffer.flip();
            outFileChannel.write(byteBuffer);
            //清楚缓冲区，缓冲区的数据依然存在，但是处于被遗忘的状态
            byteBuffer.clear();
        }
        inputChannel.close();
        outFileChannel.close();
        fileInputStream.close();
        fileOutputStream.close();


    }

    /**
     * 直接从通道中读，在内存中分配空间，在物理内存中直接操作
     * @throws Exception
     */
    @Test
    public void test3() throws Exception{
        FileInputStream  fileInputStream=new FileInputStream("E:\\EE.txt");
        FileChannel intputChannel = fileInputStream.getChannel();

        FileOutputStream fileOutStream=new FileOutputStream("E:\\KK.txt");

        FileChannel outputChannel=fileOutStream.getChannel();

        outputChannel.transferFrom(intputChannel,0,intputChannel.size());
        fileInputStream.close();
        intputChannel.close();

        fileOutStream.close();
        outputChannel.close();

    }

    /**
     * 分散读取聚集写入
     */
    @Test
    public void test4() throws Exception{

        FileInputStream  fileInputStream=new FileInputStream("E:\\EE.txt");
        FileChannel inputChannel = fileInputStream.getChannel();

        FileOutputStream fileOutStream=new FileOutputStream("E:\\KK.txt");

        FileChannel outputChannel=fileOutStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
        ByteBuffer byteBuffer2 = ByteBuffer.allocateDirect(100);
        ByteBuffer byteBuffer3 = ByteBuffer.allocateDirect(100);

        ByteBuffer[] bufs={byteBuffer,byteBuffer2,byteBuffer3};

        while (inputChannel.read(bufs)!=-1){
            inputChannel.read(bufs);
            for(int i=0;i<bufs.length;i++){
                bufs[i].flip();
            }

            outputChannel.write(bufs);

            for(int i=0;i<bufs.length;i++){
                bufs[i].clear();
            }
        }
        fileInputStream.close();
        inputChannel.close();

        fileOutStream.close();
        outputChannel.close();
    }
    
}
