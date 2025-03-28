import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class City {
    private String capital;
    private int summerTime;
    private String latitude;
    private String longitude;

    public City(String capital, int summerTime, String latitude, String longitude) {
        this.capital = capital;
        this.summerTime = summerTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }



        public City parseLine(int targetLine) {
            try (BufferedReader reader = new BufferedReader(new FileReader("strefy.csv"))) {
                String line;
                int startLine = 1;
                while ((line = reader.readLine()) != null) {
                    if (startLine == targetLine) {

                        System.out.println(line);
                        String[] parts = line.split(",");

                        for (int i = 0; i < parts.length; i++) {
                            this.capital = parts[0].trim();
                            this.summerTime = Integer.parseInt(parts[1].trim());
                            this.latitude = parts[2].trim();
                            this.longitude = parts[3].trim();
                        }
                       break;
                    }
                    startLine++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("------------");
             City city = new City(this.capital, this.summerTime, this.latitude, this.longitude);
            return city;
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
