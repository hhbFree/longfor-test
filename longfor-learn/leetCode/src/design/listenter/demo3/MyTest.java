package design.listenter.demo3;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/5/21 18:35
 */
public class MyTest {

    public static void main(String[] args) {
        MyCallback myCallback=new MyCallback();
        myCallback.setLister(667,new MyAction() {
            @Override
            public void success(String msg) {
                System.out.println("success"+msg);
            }

            @Override
            public void fail(String msg) {
                System.out.println("fail"+msg);
            }

            @Override
            public void timeout(String msg) {
                System.out.println("timeout"+msg);
            }
        });
    }
}
