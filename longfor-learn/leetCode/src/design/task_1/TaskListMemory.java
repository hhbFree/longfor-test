package design.task_1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/11/12 20:56
 */
public class TaskListMemory implements TaskQueue {

    Map<Integer, List> map=new ConcurrentHashMap<>();

    private int shard=6;

    private int limit=100;


    public TaskListMemory(int shard,int limit) {
        for (int i = 0; i < shard; i++) {
            List<Integer> list=new LinkedList<>();
            map.put(i,list);
        }
        this.shard=shard;
        this.limit=limit;
    }

    public TaskListMemory() {
        for (int i = 0; i < shard; i++) {
            List<Integer> list=new LinkedList<>();
            map.put(i,list);
        }
    }


    @Override
    public void put(int partition, Integer taskArgs) {
        map.get(partition%shard).add(taskArgs);
    }

    @Override
    public List<Integer> poolList(int partition) {
        if (isEmpty(partition)) {
            List<Integer> list=new ArrayList<>();
            List<Integer> queue = map.get(partition % shard);
            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i) != null) {
                    list.add(queue.get(i));
                }else{
                    break;
                }

            }
            return list;
        }
        return null;
    }

    @Override
    public Integer pool(int partition) {
        return null;
    }

    @Override
    public void delete(int partition, Integer taskArgs) {

    }

    @Override
    public void delete(int partition, List<Integer> list) {

    }

    @Override
    public boolean isEmpty(int partition) {
        return map.get(partition % shard).size()==0;
    }
}
