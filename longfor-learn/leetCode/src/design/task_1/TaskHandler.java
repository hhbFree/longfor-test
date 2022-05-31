package design.task_1;

/**
 * @Description: 对外提供的handler接口
 * @Author: hbHao
 * @Date: 2020/11/16 8:56
 */
public class TaskHandler {

    private static TaskHandler taskHandler=new TaskHandler();

    private ManTask manTask;

    private WomenTask womenTask;

    public TaskHandler() {
        manTask=new ManTask();

        womenTask=new WomenTask(null);

    }


    public void consume(){

    }


    /**
     * 对外提供获取对象接口，使实现类更加灵活
     */

    public ManTask getManTask(){
        return manTask;

    }

    public WomenTask getWomenTask(){

        return womenTask;
    }
}
