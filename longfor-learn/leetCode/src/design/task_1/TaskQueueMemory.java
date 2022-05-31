package design.task_1;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/11/12 19:53
 */
public class TaskQueueMemory implements TaskQueue {

    Map<Integer, Queue> map=new ConcurrentHashMap<>();

    private int shard=6;

    private int limit=100;


    public TaskQueueMemory(int shard,int limit) {
        for (int i = 0; i < shard; i++) {
            Queue queue=new PriorityBlockingQueue();
            map.put(i,queue);
        }
       this.shard=shard;
       this.limit=limit;
    }

    public TaskQueueMemory() {
        for (int i = 0; i < shard; i++) {
            Queue queue=new PriorityBlockingQueue();
            map.put(i,queue);
        }
    }

    @Override
    public void put(int partition, Integer taskArgs) {
        Queue queue = map.get(partition % shard);
        queue.add(1);
        map.put(partition%shard,queue);
    }

    @Override
    public List<Integer> poolList(int partition) {
        Set<Map.Entry<Integer, Queue>> entries = map.entrySet();
        List<Integer> list=new ArrayList<>();
        for (Map.Entry<Integer, Queue> entry : entries) {
            Queue<Integer> queue = entry.getValue();
                    list.add(queue.poll());
        }
        return list;
//        if (isEmpty(partition)) {
//            List<Integer> list=new ArrayList<>();
//            Queue<Integer> queue = map.get(partition % shard);
//            for (int i = 0; i < limit; i++) {
//                if (queue.poll() != null) {
//                    list.add(queue.poll());
//                }else{
//                    break;
//                }
//
//            }
//            return list;
//        }
//        return null;
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
        return map.get(partition % shard).poll()==null;
    }
}
