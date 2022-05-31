package thread;

public class test {
    public static void main(String[] args) {
        Thread aqs = new Thread(() ->{
            System.out.println("asd");
            try {
                Thread.sleep(11);

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        });
        //开启守护进程
        aqs.setDaemon(true);
        aqs.start();
    }
}
