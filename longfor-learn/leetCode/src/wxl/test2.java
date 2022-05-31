package wxl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class test2 {
    private static Object o=new Object();
    public static void main(String[] args) {

        synchronized (o){
            List<Map<String, String>> list = new ArrayList<>();
        }


    }

}
