package time;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
 
public class TimeTest {
    private static long _TEN_THOUSAND=10000;
    public static void main(String[] args) {
        Timestamp timestamp= Timestamp.valueOf(LocalDateTime.now());
        System.out.println(timestamp.getTime());
    }
 
    public static void testSystem(long times){//use 188
        for(int i=0;i<times;i++){
            long currentTime=System.currentTimeMillis();
        }
    }
 
    public static void testCalander(long times){//use 6299
        for(int i=0;i<times;i++){
            long currentTime=Calendar.getInstance().getTimeInMillis();
        }
    }
 
    public static void testDate(long times){
        for(int i=0;i<times;i++){
            long currentTime=new Date().getTime();
        }
    }
 
}