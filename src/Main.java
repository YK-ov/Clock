import java.time.LocalTime;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        DigitalClock digitalClock = new DigitalClock(12);
        //digitalClock.setCurrentTime();
        //System.out.println(digitalClock);
        digitalClock.setTime(15, 59, 59);
        //System.out.println(digitalClock);
        System.out.println(digitalClock.toString());

        City city = new City("Warsaw", 2, "123 e", "123 e");
        City upgradedCity = city.parseLine("Abu Dhabi,4,24.4539 N, 54.3773 E");
        System.out.println(upgradedCity);
        System.out.println("--------");
        //city.parseFile("strefy.csv");

        Map<String, City> result = city.parseFile("strefy.csv");
        System.out.println(result);


    }



}
