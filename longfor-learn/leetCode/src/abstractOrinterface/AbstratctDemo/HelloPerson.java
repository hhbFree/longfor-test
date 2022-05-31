package abstractOrinterface.AbstratctDemo;

/**
 * 抽象类指的是类:相似属性的类继承抽象类
 * 接口指的是行为：不同类别的类都可以实现相同接口的行为
 *
 */
public class HelloPerson extends HelloWorld {
//    @Override
//    public void test1() {
//
//    }

    public static void main(String[] args) {
        HelloWorld helloWorld=new HelloPerson();
        helloWorld.test2();
    }
}
