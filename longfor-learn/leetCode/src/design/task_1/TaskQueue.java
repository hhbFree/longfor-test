package design.task_1;

import java.util.List;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/11/12 19:54
 */
public interface TaskQueue {

    void put(int partition, Integer taskArgs);

    List<Integer> poolList(int partition);
    Integer pool(int partition);
    void delete(int partition, Integer taskArgs);
    void delete(int partition, List<Integer> list);
    boolean isEmpty(int partition);
}
