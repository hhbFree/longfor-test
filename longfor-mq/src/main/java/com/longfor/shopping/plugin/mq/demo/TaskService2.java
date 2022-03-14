package com.longfor.shopping.plugin.mq.demo;


import com.longfor.shopping.plugin.mq.config.TaskCall;
import com.longfor.shopping.plugin.mq.producer.TaskImplementation;

import java.util.HashSet;
import java.util.Set;

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

    public static void main(String[] args) {
        Set<String> stringSet=new HashSet<>();
        stringSet.add("1");
        stringSet.add("2");
        stringSet.add("3");
        for (String s : stringSet) {
            if("2".equals(s)){
                stringSet.remove("2");
            }
        }
        System.out.println(stringSet.toString());
    }
}
