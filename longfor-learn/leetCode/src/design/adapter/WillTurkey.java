package design.adapter;

public class WillTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("火鸡叫");
    }

    @Override
    public void fly() {
        System.out.println("我只能飞一点距离");
    }
}
