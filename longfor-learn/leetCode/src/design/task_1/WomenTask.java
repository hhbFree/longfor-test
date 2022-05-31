package design.task_1;

import java.util.List;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/11/12 20:10
 */
public class WomenTask extends AbstractTask {

    public WomenTask(TaskQueue taskQueueMemory) {
        put("women",taskQueueMemory);

    }

    @Override
    public boolean runTest(String taskName, TaskQueue taskQueue) {

        List<Integer> list = taskQueue.poolList(1);
        if(list!=null&&list.size()>0){
            return true;
        }
        return false;
    }
}
