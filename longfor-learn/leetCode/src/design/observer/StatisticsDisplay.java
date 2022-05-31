package design.observer;

/**
 * 当前的天气情况
 */
public class StatisticsDisplay implements Observer, DisplayElement {
    private float temp;
    private float humidity;
    private float pressure;
    private Subject weatherData;
    //构造器传入主题对象进行注册
    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {//公告布展示
        System.out.println(temp+"F degrees and "+humidity);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {//当发生改变就去动态的展示
        this.temp=temp+1;
        this.humidity=humidity+2;
        display();
    }
}
