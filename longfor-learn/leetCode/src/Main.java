import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    final static Object[] items=new Object[1];



    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("1", 1);
        Collection<Object> values = map.values();
        Set<String> set = new HashSet<>();

        Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

        //Integer 和 String 都重写的equals和hashCode方法
        Integer a = 1;
        Integer b = 1;
        System.out.println(a == b);//true
        System.out.println(a.equals(b));//true
        System.out.println(a instanceof Integer);
        System.out.println(a.hashCode() + ":" + b.hashCode());//1:1 true


        String s1 = "1";
        String s2 = "1";
        System.out.println(s1.hashCode() + ":" + s2.hashCode());//49:49 true

        /**
         *5.为什么重写equals()的同时还得重写hashCode()
         *
         * 这个问题之前我也很好奇，不过最后还是在书上得到了比较明朗的解释，
         * 当然这个问题主要是针对映射相关的操作（Map接口）。学过数据结构的同学都知道Map
         * 接口的类会使用到键对象的哈希码，当我们调用put方法或者get方法对Map容器进行操作时，
         * 都是根据键对象的哈希码来计算存储位置的，因此如果我们对哈希码的获取没有相关保证，
         * 就可能会得不到预期的结果。
         *
         * Exception in thread "main" java.lang.NullPointerException
         * 	at Main.main(Main.java:29)
         */
        //hashMap中key是可以为null的如果重写hashCode就会报空指针异常
        String nu = null;
        // System.out.println(nu.hashCode());

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        Iterator iterator = list.iterator();
//        while (iterator.hasNext()){
//            list.remove(1);//移除第2个元素
//            int value= (int) iterator.next();
//            System.out.println(value);
//        }

        ClassLoader classLoader;


        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        String s = null;

        //System.out.println(s.hashCode());

        List<String> listArr = new LinkedList<>();

        String ss = "http://192.168.6.82:8081/E:/app/icon//fa51a24af6ea49699f8f40890530f669.png";
        String[] split = ss.split(":");
        System.out.println(split[2]);

        File file = new File("E:\\Qiju_Li\\123");
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }

        int[] intArr = {1, 2, 3, 1, 1, 2, 4};

        Map<String, List<Integer>> mapList = new HashMap<>();
            for (int i : intArr) {
                if (mapList.get(i + "") == null) {
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(i);
                    mapList.put(i+"",list1);
                } else {
                    mapList.get(i + "").add(i);
                }
            }


        System.out.println(mapList);

        System.out.println(items.length);

        Integer d=112312331;
        Integer f=112312331;
        if(d==f){
            System.out.println("qwe");
        }


    }
}
