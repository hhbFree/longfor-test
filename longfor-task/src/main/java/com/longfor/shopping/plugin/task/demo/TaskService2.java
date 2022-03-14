package com.longfor.shopping.plugin.task.demo;

import com.longfor.shopping.plugin.task.TaskImplementation;
import com.longfor.shopping.plugin.task.callable.TaskCall;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2021/8/24 19:23
 */
@TaskImplementation(event = "bbb")
public class TaskService2 implements TaskCall<String> {

    @Override
    public Object processTask(String args) {
        System.out.println(args);
        return "helloWorld";
    }
}
