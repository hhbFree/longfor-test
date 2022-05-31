package design.observer;

public interface Observer {
    //当气象观测值改变时，主题会把这些状态值当作方法参数，传给观察者
    public void update(float temp, float humidity, float pressure);
}
