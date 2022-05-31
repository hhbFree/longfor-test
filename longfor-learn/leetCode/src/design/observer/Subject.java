package design.observer;

/**
 * 观察者主题
 */
public interface Subject {
    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObserver();//当主题状态改变时，这个方法会被调用，通知所有观察者

}
