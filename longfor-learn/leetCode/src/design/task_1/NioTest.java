package design.task_1;

import java.util.Random;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/11/16 8:51
 */
public class NioTest {

    public static void main(String[] args) throws InterruptedException {
        TaskQueue taskQueue=new TaskQueueMemory();
        WomenTask womenTask=new WomenTask(taskQueue);
        while (true){
            Thread.sleep(1000);
            Random random=new Random();
            taskQueue.put(random.nextInt(10),2);
        }

    }
}
