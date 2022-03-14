package com.longfor.shopping.plugin.task;

public class TaskConsumerHandler {


    private static TaskConsumerHandler INS=new TaskConsumerHandler();


    public static TaskConsumerHandler getInstance(){
        return INS;
    }

    public void consume(TaskBeanInstance task, Object args) {

        if (task.getBean() instanceof String){
            System.out.println("task===============>begin");
            //dedicatedTaskConsumer.consume(task, (DedicatedTaskArgs) args);
        } else if(task.getBean() instanceof Integer){
            //batchTaskConsumer.consume(task, args);
        } else {
           // simpleTaskConsumer.consume(task, args);
        }

    }



}
