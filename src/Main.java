import java.time.LocalTime;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        /* OLD CODE
        DigitalClock digitalClock = new DigitalClock(12);
        digitalClock.setCurrentTime();
        System.out.println(digitalClock);
        digitalClock.setTime(15, 59, 59);
        System.out.println(digitalClock);
        System.out.println(digitalClock.toString());
         */

        City city = new City("Warsaw", 2, "123 e", "123 e");
        City upgradedCity = city.parseLine("Abu Dhabi,4,24.4539 N, 54.3773 E");
        System.out.println(upgradedCity);
        System.out.println("--------");
        //city.parseFile("strefy.csv");

        Map<String, City> result = city.parseFile("strefy.csv");
        System.out.println(result);

        System.out.println(upgradedCity.getLatitudeValue());    // dla znaczen szerokosci i dlugosci (bo sa orgyginalnie jako String)
        System.out.println(upgradedCity.getLongtitudeValue());

        City Warsaw = new City("Warszawa",2,"52.2297 N", "21.0122 E");

        DigitalClock digitalClock = new DigitalClock(Warsaw, 24);

        City Kyiv = new City("Kijów",3,"50.4501 N", "30.5234 E");
        digitalClock.setTime(0 , 0, 0);
        System.out.println(digitalClock);
        City Lublin = new City("Lublin",2,"51.2465 N", "22.5684 E");
        //digitalClock.setCity(Kyiv);
        //System.out.println(digitalClock);
        System.out.println("method below:");
        System.out.println(Lublin.localMeanTime(12, 0, 0));

        City Tokyo = new City("Tokyo", 9, "35.6895 N", "139.6917 E");
        System.out.println(Tokyo.localMeanTime(12, 0, 0));

    }

}
