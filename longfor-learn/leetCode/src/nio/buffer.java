package nio;



import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * Buffer的存取方法：
 * 　　获取 Buffer 中的数据
 *
 * 　　   get() ：读取单个字节
 *
 * 　　   get(byte[] dst)：批量读取多个字节到 dst 中
 *
 * 　　　get(int index)：读取指定索引位置的字节(不会移动 position)
 *
 *
 *
 * 　  放入数据到 Buffer 中
 *
 * 　　　put(byte b)：将给定单个字节写入缓冲区的当前位置
 *
 * 　　　put(byte[] src)：将 src 中的字节写入缓冲区的当前位置
 *
 * 　　　put(int index, byte b)：将指定字节写入缓冲区的索引位置(不会移动 position)
 *
 * 非直接缓冲和直接缓冲区(对于字节缓冲区而言)
 * 　  1. 非直接缓冲区=========>虚拟机内存   非直接缓冲区    在虚拟机内存中创建，易回收    但占用虚拟机内存开销
 *      当我们的程序想要从硬盘中读取数据 需要
 *      1.先从物理硬盘把数据读取到物理内存中
 *
 *      2再将内容复制到JVM的内存中
 *
 *      3然后读取应用程序才可以读取到内容
 *
 *      读写都是这样需要复制这一个动作 当遇到大文本的文件时 效率及其低下
 *
 * 　　　　ByteBuffer  buf =  ByteBuffer.allocate(1024);
 *
 * 　  2. 直接缓冲区=========>在虚拟机内存外的物理内存 IO操作直接进行,没有再次复制    创建和销毁开销大
 *
 *     直接缓冲区的是图中红线所标识的 直接在应用程序和物理磁盘中直接在内存中建立一个缓冲区在物理内存中,
 *     这样省略了复制的步骤 效率由此提高.
 * 　　　　ByteBuffer buf = ByteBuffer.allocateDirect(1024);
 *
 *    ① 字节缓冲区要么是直接的，要么是非直接的。如果为直接字节缓冲区，
 *    则 Java 虚拟机会尽最大努力直接在此缓冲区上执行本机 I/O 操作。
 *    也就是说，在每次调用基础操作系统的一个本机 I/O 操作之前（或之后），
 *    虚拟机都会尽量避免将缓冲区的内容复制到中间缓冲区中（或从中间缓冲区中复制内容）。　　　
 *
 *    ② 直接字节缓冲区可以通过调用此类的 allocateDirect() 工厂方法来创建。
 *    此方法返回的缓冲区进行分配和取消分配所需成本通常高于非直接缓冲区。
 *    直接缓冲区的内容可以驻留在常规的垃圾回收堆之外，因此，它们对应用程序的内存需求量造成的影响可能并不明显。
 *    所以，建议将直接缓冲区主要分配给那些易受基础系统的本机 I/O 操作影响的大型、持久的缓冲区。
 *    一般情况下，最好仅在直接缓冲区能在程序性能方面带来明显好处时分配它们。
 */
public class buffer {
    @Test
    public void test(){
        String str = "abcde";

        //1. 分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(2048);//直接缓存区
        System.out.println("-----------------allocate()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //2. 利用 put() 存入数据到缓冲区中
        buf.put(str.getBytes());

        System.out.println("-----------------put()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //3. 切换读取数据模式
        buf.flip();

        System.out.println("-----------------flip()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //4. 利用 get() 读取缓冲区中的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));

        System.out.println("-----------------get()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //5. rewind() : 可重复读
        buf.rewind();

        System.out.println("-----------------rewind()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //6. clear() : 清空缓冲区. 但是缓冲区中的数据依然存在，但是处于“被遗忘”状态
        buf.clear();

        System.out.println("-----------------clear()----------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        System.out.println((char)buf.get());

    }

}
