package wxl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class test3 {

    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        lock.lock();
        List<Map<String, String>> list = new ArrayList<>();
        lock.unlock();

    }

}
