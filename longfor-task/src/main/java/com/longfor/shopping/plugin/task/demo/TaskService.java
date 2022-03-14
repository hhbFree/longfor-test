package com.longfor.shopping.plugin.task.demo;

import com.longfor.shopping.plugin.task.Task;
import com.longfor.shopping.plugin.task.TaskImplementation;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2021/8/24 19:23
 */
@TaskImplementation(event = "aaa")
public class TaskService implements Task<String> {
    @Override
    public void processTask(String args) {
        System.out.println(args);
    }
}
