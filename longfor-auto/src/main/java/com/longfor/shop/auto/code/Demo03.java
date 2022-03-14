package com.longfor.shop.auto.code;

import java.util.*;
import java.util.stream.Collectors;

public class Demo03 {

    public static final Map<String,Boolean> map=new HashMap<>();

    static {
        map.put("品牌",true);
        map.put("计量单位",true);
        map.put("供货周期(天)",true);
        map.put("单价(不含税)",true);
        map.put("税率",true);
        map.put("数量",true);
    }

    public static void main(String[] args) {

        long times = 1638288000000L;
        Date date = new Date(times);
        System.out.println(date);
        System.out.println(date.getTime());
    }
}
