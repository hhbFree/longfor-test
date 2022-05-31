package design.run;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2021/2/3 14:38
 */
public class RunTest {
    public static void main(String[] args) {
        DealRunnable dealRunnable=new DealRunnable();

        dealRunnable.deal(()->{
            System.out.println("执行了");
        });
    }
}
