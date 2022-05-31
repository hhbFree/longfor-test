package design.task_1;

import java.util.List;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/11/12 20:10
 */
public class ManTask  extends AbstractTask {

    public ManTask() {
        put("man",new TaskQueueMemory());

    }

    @Override
    public boolean runTest(String taskName, TaskQueue taskQueue) {

        List<Integer> list = taskQueue.poolList(1);
        if(list!=null&&list.size()>0){
            list.stream().forEach(System.out::print);
            return true;
        }
        return false;
    }
}
