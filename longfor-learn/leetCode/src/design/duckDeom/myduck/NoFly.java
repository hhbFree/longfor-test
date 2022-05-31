package design.duckDeom.myduck;

public class NoFly implements FlyBehavior {
    @Override
    public void fly() {

        System.out.println("我不会飞");
    }
}
