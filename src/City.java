import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.*;

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
        double timeZoneLongitude = 15 * this.summerTime;
        double difference = 0;

        if (this.longtitudeDirection == 'E') {
            difference = this.longtitudeValue - timeZoneLongitude;
        } else if (this.longtitudeDirection == 'W') {
            difference = -this.longtitudeValue - timeZoneLongitude;
        } else {
            throw new IllegalArgumentException("Długość może być tylko E (wschodnia) i W (zachodnia)");
        }

        double hoursDifferenceDouble = difference / 15.0;

        int wholeHoursDifference = (int) Math.floor(hoursDifferenceDouble);
        double fractionalHoursDifference = hoursDifferenceDouble - wholeHoursDifference;
        int minutesDifference = (int) Math.floor(fractionalHoursDifference * 60);
        double secondsDifference = (fractionalHoursDifference * 60 - minutesDifference) * 60;

        int updatedHours = hours + wholeHoursDifference;
        int updatedMinutes = minutes + minutesDifference;
        int updatedSeconds = seconds + (int) Math.round(secondsDifference);


        if (updatedSeconds >= 60) {
            int extraMinutes = updatedSeconds / 60;
            updatedMinutes = updatedMinutes + extraMinutes;
            updatedSeconds = updatedSeconds % 60;
        } else if (updatedSeconds < 0) {
            int borrowMinutes = (Math.abs(updatedSeconds) + 59) / 60;
            updatedMinutes = updatedMinutes - borrowMinutes;
            updatedSeconds = updatedSeconds + (borrowMinutes * 60);
        }

        if (updatedMinutes >= 60) {
            int extraHours = updatedMinutes / 60;
            updatedHours = updatedHours + extraHours;
            updatedMinutes = updatedMinutes % 60;
        } else if (updatedMinutes < 0) {
            int borrowHours = (Math.abs(updatedMinutes) + 59) / 60;
            updatedHours = updatedHours - borrowHours;
            updatedMinutes = updatedMinutes + (borrowHours * 60);
        }

        while (updatedHours < 0) {
            updatedHours = updatedHours + 24;
        }

        while (updatedHours >= 24) {
            updatedHours = updatedHours % 24;
        }


        return updatedHours + ":" + updatedMinutes + ":" + updatedSeconds;
    }

    public static int worstTimezoneFit(City cityOne, City cityTwo, int hours, int minutes, int seconds) {
        String localTimeOne = cityOne.localMeanTime(hours, minutes, seconds);
        String[] parts1 = localTimeOne.split(":");
        int localHours1 = Integer.parseInt(parts1[0]);
        int localMinutes1 = Integer.parseInt(parts1[1]);
        int localSeconds1 = Integer.parseInt(parts1[2]);

        cityTwo.localMeanTime(hours, minutes, seconds);
        String localTimeTwo = cityTwo.localMeanTime(hours, minutes, seconds);
        String[] parts2 = localTimeTwo.split(":");
        int localHours2 = Integer.parseInt(parts2[0]);
        int localMinutes2 = Integer.parseInt(parts2[1]);
        int localSeconds2 = Integer.parseInt(parts2[2]);

        int totalSecondsOne = localHours1 * 3600 + localMinutes1 * 60 + localSeconds1;
        int totalSecondsTwo = localHours2 * 3600 + localMinutes2 * 60 + localSeconds2;

        int totalSecondsTimeZone = hours * 3600 + minutes * 60 + seconds;

        int differenceOne = Math.abs(totalSecondsOne - totalSecondsTimeZone);
        int differenceTwo = Math.abs(totalSecondsTwo - totalSecondsTimeZone);

        return Integer.compare(differenceTwo, differenceOne);
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