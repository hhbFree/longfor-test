package mysync;

/**
 * @Description: 自定义事件处理器
 * @Author: hbHao
 * @Date: 2020/8/17 20:58
 */
public interface MyEventHandler<E> {
    void run(Object sender,E e);
}
