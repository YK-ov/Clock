import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        City city = new City("Warsaw", 2, "123 e", "123 e");
        City upgradedCity = city.parseLine("Abu Dhabi,4,24.4539 N, 54.3773 E");
        System.out.println(upgradedCity);
        System.out.println("--------");

        Map<String, City> cities = city.parseFile("strefy.csv");
        System.out.println(cities);

        City Warsaw = new City("Warszawa",2,"52.2297 N", "21.0122 E");

        DigitalClock digitalClock = new DigitalClock(Warsaw, 24);

        City Kyiv = new City("Kijów",3,"50.4501 N", "30.5234 E");
        digitalClock.setTime(0 , 0, 0);
        System.out.println(digitalClock);
        City Lublin = new City("Lublin",2,"51.2465 N", "22.5684 E");
        System.out.println(Lublin.localMeanTime(12, 0, 0));

        City Tokyo = new City("Tokyo", 9, "35.6895 N", "139.6917 E");
        System.out.println(Tokyo.localMeanTime(12, 0, 0));

        System.out.println("----");

        List<City> cityList = new ArrayList<>(cities.values()); // lista obiektow City
        cityList.sort((city1, city2) -> City.worstTimezoneFit(city1, city2, 12, 0, 0));
        System.out.println("Posortowana lista miast:");
        for (City currentCity : cityList) {
            System.out.println(currentCity.getCapital());

        }

        AnalogClock analogClock = new AnalogClock(Kyiv);
        analogClock.toSvg("clock.svg");


        LocalTime localTime = LocalTime.now();
        SecondHand seconds = new SecondHand(0,0, 70, "red", 1);
        seconds.setTime(localTime);


    }

}