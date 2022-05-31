package design.adapter;

/**
 * 适配器模式：将一个类的接口，转换为客户期望的另一个接口。适配器让原本不兼容的类可以合作无间
 */
public class Test {

    public static void main(String[] args) {
        //先创建bull鸭
        BullDuck bullDuck=new BullDuck();

//        bullDuck.fly();
//        bullDuck.quack();
        
        //创建一个火鸡
        WillTurkey turkey=new WillTurkey();

        //创建一个适配器
        Duck turkeyAdapter = new TurkeyAdapter(turkey);

        turkeyAdapter.fly();
        turkeyAdapter.quack();

        System.out.println("绿头鸭----------------------------------------");
        testDuck(bullDuck);

        System.out.println("火鸡---------------------------------------");
        testDuck(turkeyAdapter);

    }

    static  void testDuck(Duck duck){
        duck.quack();
        duck.fly();

    }

}
