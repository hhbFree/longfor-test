package com.longfor.shop.asm.code;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.cglib.proxy.FixedValue;

import java.util.*;

public class AsmTest {
    public static void main(String[] args) {
        long times = 1643558400000L;
        Date date = new Date(times);
        System.out.println(date);

        Map<String,Object> map=new HashMap<>();

        map.put("1","1");
        map.put("2","2");
        map.put("3","3");

        /**
         * 4.putIfAbsent 方法
         * 如果key关联的value不存在，则关联新的value值，返回key关联的旧的值
         */
        map.putIfAbsent("1","200");

        System.out.println(map.get("1"));

        /**
         * 1.getOrDefault 方法
         *
         * 如果指定的key存在，则返回该key对应的value，
         * 如果不存在，则返回指定的值。
         *
         * 例子:key为4不存在，输出d
         */
        Object orDefault = map.getOrDefault("4", "1000");
        System.out.println(orDefault);


        map.replace("2","22");
        System.out.println(map.get("2"));

        map.replaceAll((k,v)->"1000");
        map.forEach((k,v)->{
            System.out.println("k:"+k+"||"+"v:"+v);
        });

        System.out.println("------------------------------");
        map.remove("1","2");
        map.forEach((k,v)->{
            System.out.println("k:"+k+"||"+"v:"+v);
        });

        System.out.println("------------------------------");
        map.remove("1","1000");
        map.forEach((k,v)->{
            System.out.println("k:"+k+"||"+"v:"+v);
        });


        /**
         * 8.computeIfAbsent 方法 不存在就更新，添加；存在不更新
         * 如果指定的key不存在，则通过指定的K -> V计算出新的值设置为key的值
         */
        System.out.println("------------------------------");
        map.computeIfAbsent("1",k->"new1");
        map.computeIfAbsent("2",k->"new2");
        map.computeIfAbsent("9",k->"new2");
        map.forEach((k,v)->{
            System.out.println("k:"+k+"||"+"v:"+v);
        });


        /**
         * 9.computeIfPresent 方法   如果存在就更新，value为 null直接删除
         * 如果指定的key存在，则根据旧的key和value计算新的值newValue,
         * 如果newValue不为null，则设置key新的值为newValue,
         * 如果newValue为null, 则删除该key的值，
         */
        System.out.println("------------------------------");
        map.computeIfPresent("1",(k,v)->null);
        map.computeIfPresent("2",(k,v)->"new2");
        map.forEach((k,v)->{
            System.out.println("k:"+k+"||"+"v:"+v);
        });
    }
}
