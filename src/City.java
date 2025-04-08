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

    public City(String capital, int summerTime, String latitude, String longitude) {
        this.capital = capital;
        this.summerTime = summerTime;
        this.latitude = latitude;
        this.longitude = longitude;

        String regex = "[^0-9.]";
        String replacement = "";

        this.latitudeValue = Double.parseDouble(this.latitude.replaceAll(regex, replacement));
        this.longtitudeValue = Double.parseDouble(this.longitude.replaceAll(regex, replacement));
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
