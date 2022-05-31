package design.adapter;

public class BullDuck implements Duck {
    @Override
    public void fly() {
        System.out.println("我能飞");
    }

    @Override
    public void quack() {
        System.out.println("哇哇哇的叫");
    }
}
