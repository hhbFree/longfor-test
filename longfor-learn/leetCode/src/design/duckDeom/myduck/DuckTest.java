package design.duckDeom.myduck;


public class DuckTest {

    public static void main(String[] args) {
        MyDuck duck=new ModelDuak();//多态，模型鸭
        duck.performFly();
        duck.performQuack();

        duck.setFlyBehavior(new FlyRocketPower());
        duck.performFly();
    }
}
