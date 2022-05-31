package finalDemo;

import java.util.Map;

/**
 * @Description: final 成员变量，子类不能对其发类赋值，方法不能覆盖，一般模板模式使用
 * @Author: hbHao
 * @Date: 2021/4/14 16:14
 */
public class Man extends Person {

    public Man(Map<String,Object> map) {

//        super.map=map;
    }
}
