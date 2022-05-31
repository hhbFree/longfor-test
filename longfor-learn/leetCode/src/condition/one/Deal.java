package condition.one;

import java.util.concurrent.TimeUnit;

public class Deal {
    private String name;

    private int count=1;

    private boolean flag=false;

    public synchronized void consume(){
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"。。消费者。。"+name);
        flag=false;
        notifyAll();

    }

    public synchronized void product(String name){
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.name=name+count;
        System.out.println(Thread.currentThread().getName()+"。。生产者。。"+ this.name);
        count++;
        flag=true;
        notifyAll();
    }


}
