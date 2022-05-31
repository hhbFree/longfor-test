package design.observer;

public class ObserverTest {
    public static void main(String[] args) {
        WeatherData weatherData=new WeatherData();

        CurrentConditionDisplay currentConditionDisplay=new CurrentConditionDisplay(weatherData);//注册
        StatisticsDisplay statisticsDisplay=new StatisticsDisplay(weatherData);//注册

        weatherData.setMeasurements(1,2,3);
        weatherData.setMeasurements(1,3,3);
        weatherData.setMeasurements(1,2,3);

    }
}
