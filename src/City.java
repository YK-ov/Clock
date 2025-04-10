import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class City {
    private String capital;
    private int summerTime;
    private String latitude;

    private String longitude;

    private double latitudeValue;
    private double longtitudeValue;
    private char latitudeDirection;
    private char longtitudeDirection;

    public City(String capital, int summerTime, String latitude, String longitude) {
        this.capital = capital;
        this.summerTime = summerTime;
        this.latitude = latitude;
        this.longitude = longitude;

        String regex = "[^0-9.]";
        String replacement = "";

        this.latitudeValue = Double.parseDouble(this.latitude.replaceAll(regex, replacement));
        this.longtitudeValue = Double.parseDouble(this.longitude.replaceAll(regex, replacement));
        this.latitudeDirection = this.latitude.charAt(this.latitude.length() - 1);
        this.longtitudeDirection = this.longitude.charAt(this.longitude.length() - 1);
    }

    public City parseLine(String line) {
        String[] parts = line.split(",");
            this.capital = parts[0];
            this.summerTime = Integer.parseInt(parts[1]);
            this.latitude = parts[2];
            this.longitude = parts[3];

        return new City(capital, summerTime, latitude, longitude);
     }

    public Map<String, City> parseFile(String path) {
        Map<String, City> cities = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int lineCounter = 0;
            while ((line = reader.readLine()) != null) {
                lineCounter++;
                if (lineCounter == 1) {
                    continue;
                }
                City city = parseLine(line);
                cities.put(city.capital, city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public double getLatitudeValue() {
        return latitudeValue;
    }

    public double getLongtitudeValue() {
        return longtitudeValue;
    }

    public String getCapital() {
        return capital;
    }

    public int getSummerTime() {
        return summerTime;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public char getLatitudeDirection() {
        return latitudeDirection;
    }

    public char getLongtitudeDirection() {
        return longtitudeDirection;
    }

    public String localMeanTime(int hours, int minutes, int seconds) {
        double timeZoneLongtitude = 15 * this.summerTime;
        double difference = this.longtitudeValue - timeZoneLongtitude;
        double hoursToChange;
        double doubleHours = (double) hours;
        double doubleMinutes = (double) minutes;
        double doubleSeconds = (double) seconds;
        int updatedSeconds;
        int updatedMinutes;
        int updatedHours;

        if (this.longtitudeDirection == 'W') {
            hoursToChange = difference / 15;
            doubleHours = doubleHours - Math.floor(hoursToChange);
            hoursToChange = hoursToChange * 60; // dla minut
            doubleMinutes = doubleMinutes - Math.floor(hoursToChange);
            hoursToChange = hoursToChange - doubleMinutes;
            hoursToChange = hoursToChange * 60; // dla sekund
            doubleSeconds = doubleSeconds - Math.round(hoursToChange);
        } else if (this.longtitudeDirection == 'E') {
            hoursToChange = difference / 15;
            doubleHours = doubleHours + Math.floor(hoursToChange);
            hoursToChange = hoursToChange * 60; // dla minut
            doubleMinutes = doubleMinutes + Math.floor(hoursToChange);
            hoursToChange = hoursToChange - doubleMinutes;
            hoursToChange = hoursToChange * 60; // dla sekund
            doubleSeconds = doubleSeconds + Math.round(hoursToChange);
        } else {
            throw new IllegalArgumentException("Dlugosc moze byc tylko E wschodnia i W zachodnia");
        }

        if (doubleSeconds < 0) {
            doubleSeconds = Math.abs(doubleSeconds);
        } else if (doubleSeconds >= 60) {
            doubleMinutes = doubleMinutes + doubleSeconds / 60;
            doubleSeconds = doubleSeconds % 60;
        }

        if (doubleMinutes < 0) {
            doubleMinutes = Math.abs(doubleMinutes);
        } else if (doubleMinutes >= 60) {
            doubleHours = doubleHours + doubleMinutes / 60;
            doubleMinutes = doubleMinutes % 60;
        }

        if (doubleHours < 0) {
            doubleHours = Math.abs(doubleHours);
        } else if (doubleHours >= 24) {
            doubleHours = doubleHours % 24;
        }

        updatedSeconds = (int) doubleSeconds;
        updatedMinutes = (int) doubleMinutes;
        updatedHours = (int) doubleHours;

        return updatedHours + ":" + updatedMinutes + ":" + updatedSeconds;
    }




    @Override
    public String toString() {
        return "City{" +
                "capital='" + capital + '\'' +
                ", summerTime=" + summerTime +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
