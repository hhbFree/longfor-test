package design.duckDeom.myduck;

public abstract class MyDuck {//抽象类
    //行为接口的两个引用变量
    FlyBehavior flyBehavior;

    QuackBehavior quackBehavior;

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public MyDuck() {
    }

    public abstract void display();//抽象方法

    public void swim(){
        System.out.println("所有的鸭子都会游泳");
    }

    /**
     * 委托行为类
     */
    public void performFly(){
        flyBehavior.fly();

    }
    public void performQuack(){

        quackBehavior.quack();
    }

}
