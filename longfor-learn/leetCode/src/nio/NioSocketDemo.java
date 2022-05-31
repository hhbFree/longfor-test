package nio;

/**
 * java io 是面向流的
 * java nio 是面向缓存的
 *
 * nio：
 *       |----file文件传输
 *               |-----buffer缓存
 *  *            |-----channel通道
 *
 *         ------------channel------------
 *  client|            buffer             |application
 *        --------------channel-----------
 *
 *
 *       |----socket网络传输
 *              |-----buffer缓存
 *              |-----channel通道
 *              |-----selector选择器
 *
 *          1.channel先注册到选择器中
 *          2.客户端传递数据时，直接把数据通过通道传到选择器中就直接返回，线程不会等待
 *          3.服务端会去选择器中判断是否数据准备好了，好了就直接从select中获取数据
 *          总结：解除了客户端与服务端的强绑定关系类似于mq：数据进行了异步处理
 *         ------------channel------------ | -------|----------
 *         |            buffer             |        |          |
 *        --------------channel----------- |        |----------
 *        ------------channel------------  |        |----------
 * client|            buffer               |selector|         | application
 *        -------------channel------------ |        |----------
 *        -----------channel------------   |        |----------
 *        |            buffer              |        |          |
 *        ------------channel-----------   |--------|----------
 */
public class NioSocketDemo {
}
