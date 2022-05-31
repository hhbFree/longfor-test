package wxl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class test4 {
    private static volatile  int a;
    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<>();
        System.out.println(a);
    }

}
