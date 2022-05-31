package design.duckDeom.myduck;

import java.util.EnumSet;

public class ModelDuak extends MyDuck{

    public ModelDuak() {
        flyBehavior=new NoFly();//创建的时候已经规定了属性
        quackBehavior=new CanQuack();
    }

    @Override
    public void display() {
       // EnumSet
        System.out.println("我是模型");
    }
}
