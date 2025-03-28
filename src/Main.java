import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        DigitalClock digitalClock = new DigitalClock(12);
        //digitalClock.setCurrentTime();
        //System.out.println(digitalClock);
        digitalClock.setTime(15, 59, 59);
        //System.out.println(digitalClock);
        System.out.println(digitalClock.toString());

    }



}
