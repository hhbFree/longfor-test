package mymap;

import java.util.HashMap;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/8/17 16:33
 */
public class MyMap<K,V> extends HashMap<K,V> {

    private static final int outTimes=5;


    private static HashMap<String,Object> map;

    static {
        map=new HashMap<>();
    }

    @Override
    public V put(K key, V value) {

        if (map.get(key) == null) {
            map.put(String.valueOf(key),1);
        }else{
            int times = (int) map.get(key);
            times++;
            if(times>outTimes){
                super.remove(key, value);
                return null;
             }else{
                map.put(String.valueOf(key),times);
            }

        }
        return super.put(key, value);
    }
}
