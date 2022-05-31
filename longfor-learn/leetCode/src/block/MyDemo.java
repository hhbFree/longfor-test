package block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<apple> apples = new ArrayBlockingQueue<>(1,true);

        new Thread(new producter(apples)).start();
        new Thread(new consumer(apples)).start();
    }

    static class producter implements Runnable {
        ArrayBlockingQueue<apple> apples;

        public producter(ArrayBlockingQueue<apple> apples) {
            this.apples = apples;
        }


        @Override
        public void run() {
            while (true){
                product();
            }
        }

        private void product() {
            apple apple = new apple();

            System.out.println("生产"+apple);

            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                apples.put(apple);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class consumer implements Runnable {
        ArrayBlockingQueue<apple> apples;

        public consumer(ArrayBlockingQueue<apple> apples) {
            this.apples = apples;
        }

        @Override
        public void run() {
            while (true){
                consume();
            }
        }

        private void consume() {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                apple take = apples.take();
                System.out.println("消费"+take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class apple {

    }

}
