import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class City {
    private String capita;
    private int summerTime;
    private double longitude;
    private double latitude;
    private char direction;

    public City(String capita, int summerTime, double longitude, double latitude, char direction) {
        this.capita = capita;
        this.summerTime = summerTime;
        this.longitude = longitude;
        this.latitude = latitude;
        this.direction = direction;
    }

    private void parseLine(String line) {
        String[] parts = line.split(",");
    }

}
