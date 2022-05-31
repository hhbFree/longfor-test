package condition.more;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deal_More {
    private String name;

    private int count=1;

    private boolean flag=false;

    Lock lock=new ReentrantLock();

    //通过已有的锁获取两组监视器，一组监视生产者，一组监视消费者。
    Condition producer_con = lock.newCondition();
    Condition consumer_con = lock.newCondition();


    public void consume(){
        lock.lock();

        try {
            if(!flag){
                consumer_con.await();
            }
            System.out.println(Thread.currentThread().getName()+"<<<消费者>>>"+name);
            flag=false;
            producer_con.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void product(String name){
        lock.lock();
        try {
            if (flag){
                producer_con.await();
            }
            this.name=name+count;
            System.out.println(Thread.currentThread().getName()+">>>生产者<<<"+this.name);
            count++;
            flag=true;
            consumer_con.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
