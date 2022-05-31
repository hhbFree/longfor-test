package design.observer;

import java.util.ArrayList;

/**
 * 对外的接口类
 */
public class WeatherData implements Subject {
    private ArrayList observers;

    private float temperature;

    private float humidity;

    private float pressure;

    public WeatherData() {//构造器中建立
        this.observers = new ArrayList();
    }

    @Override
    public void registerObserver(Observer observer) {//注册
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {//删除
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {//通知数据发生了改变
        for (int i = 0; i <observers.size() ; i++) {
            Observer observer=(Observer) observers.get(i);
            observer.update(temperature,humidity,pressure);

        }
    }

    public void measurementsChanged(){
        notifyObserver();
    }


    //对外提供数据传入的接口
    public void setMeasurements(float temperature,float humidity,float pressure){

        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;

        measurementsChanged();

    }
}
