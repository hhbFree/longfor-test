package wxl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {

        List<Map<String, String>> list = new ArrayList<>();
        get();
    }
    public static synchronized  void get(){
        System.out.println("hello");
    }
}
