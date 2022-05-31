package automic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicObj<E> {

    //设置一个哨兵节点====>用于作用与cas比较操作
    Node<E> sentinel=new Node<>(null);

    //头节点指向哨兵节点
    Node<E> head=sentinel;

    //尾节点初始化哨兵节点 ====>初始化的对象能够进行cas操作
    AtomicReference<Node<E>> tail =new AtomicReference<>(sentinel);


    /**
     *
     * @param e
     * @return
     */
    public boolean put(E e){

        //1.创建一个新的节点
        Node<E> newNode=new Node<>(e);

        //2.开始一个死循环
        for(;;){
            //2.1获取当前尾节点
            Node<E> currentTail = tail.get();

            //2.2 获取当前队列中尾节点的下一个节点
            Node<E> currentNextNode = currentTail.next;

            //2.3 接口入栈是2的混乱情况
            if(sentinel.next==null&&currentTail!=sentinel){
                //把没有设置成的节点完成
                tail.compareAndSet(currentTail,sentinel);
            }
            //2.4 判断当前没有其他线程将数据插入队列
            if(tail.get()==currentTail){
                if(currentNextNode!=null){//说明有其他线程正在入队
                    //cas操作将尾节点更新  帮助其他线程更新尾指针
                    tail.compareAndSet(currentTail,currentNextNode);//unsafe.compareAndSwapObject(this, valueOffset, expect, update);tail的指针（valueOffset）指向currentNextNode
                }else if(currentNextNode.casNext(null,newNode)){
                    //如果当前线程的下一个节点为null就把尾节点指向新增的节点
                    //cas更新尾指针  将尾指针指向新增的节点
                    tail.compareAndSet(currentTail,newNode);
                    return true;//入队成功
                }
            }





            return false;
        }


    }


    /**
     * 出队
     *
     *
     * head---->sentinel--------------> node
     *                                   ^
     *                                  |
     * tail-------------------------currentTail
     * * head---->sentinel
     *                 ^
     *                |
     * tail------->currentTail
     *
     * @return
     */
    public E get() {


        for(;;){
            //队列首元素节点(就是哨兵节点之后的节点)
            Node<E> currentHead = head.next;
            //当前尾节点
            Node<E> currentTail = tail.get();

            //1.如果以head为空,队列为空
            if(currentHead==null){
                return null;
            }else if(currentHead==currentTail){//2.只包含一个node节点
            //  这里分为了两步,首先以CAS方式更新哨兵结点的next指针指向null,如果执行成功再以CAS方式修改tail指针指向哨兵结点。
            //  当然这里第二部修改tail指针的操作可能会失败,也可能还没来得及执行就失去cpu执行权导致队列处于一种混乱状态
                //2.1将哨兵之后的头节点设置为null
                if(currentHead.casNext(currentHead,null)){
                    //2.2.将尾指针指向哨兵//尾指针只是一个引用
                    tail.compareAndSet(currentTail,sentinel);//这一步可能会失败，造成混乱的情况
                    return currentHead.item;
                }
            }else if(sentinel.casNext(currentHead,currentHead.next)){//3.多个队列的情况
                //将哨兵指向头节点的的下一个节点
                //返回头节点的值
                return currentHead.item;

            }

        }

    }


    private static class Node<E>{

        //UNSAFE对象,用来进行CAS操作
        private static final sun.misc.Unsafe UNSAFE;
        //next指针域在Node对象中的偏移量
        private static final long nextOffset;



        static {
            try {

                //类加载时执行,反射方式创建UNSAFE对象,我们要通过该对象以CAS的方式更新
                //Node对象中的next指针
                Class<?> unsafeClass = Unsafe.class;
                Field f = unsafeClass.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                UNSAFE = (Unsafe) f.get(null);
//                UNSAFE = sun.misc.Unsafe.getUnsafe();
                Class<?> k = AtomicObj.Node.class;
                nextOffset = UNSAFE.objectFieldOffset
                        (k.getDeclaredField("next"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }
        //CAS方式更新next指针,expect:cmp update:val
        private boolean casNext(Node<E> cmp, Node<E> val) {
            return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
        }
        private E item;
        private volatile Node<E> next;

        public Node(E item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }
    }
}
