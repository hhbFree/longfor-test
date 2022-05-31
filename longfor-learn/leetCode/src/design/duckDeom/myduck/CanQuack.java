package design.duckDeom.myduck;

public class CanQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("我会叫");
    }


}
