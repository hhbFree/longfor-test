package design.task_1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/11/12 19:50
 */
public abstract class AbstractTask {

    protected Map<String, TaskQueue> con;

    private Map<String,Thread> map;

    ExecutorService executorService;

    public AbstractTask() {
         executorService = Executors.newFixedThreadPool(5);

         con=new ConcurrentHashMap<>();

        map=new ConcurrentHashMap<>();

    }


    public synchronized void put(String taskName, TaskQueue taskQueue){

        Thread thread=new Thread(()->{
            while (true){
                if(runTest(taskName,taskQueue)){
                    System.out.println("有数据");
                }else{
                    try {
                        Thread.sleep(1000);
                        System.out.println("无数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }


        });
        thread.setDaemon(true);
        thread.start();

        con.put(taskName,taskQueue);

        map.put(taskName,thread);
    }


     public abstract boolean runTest(String taskName, TaskQueue taskQueue);
}
