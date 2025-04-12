import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AnalogClock extends Clock {
    private final List<ClockHand> hands = new ArrayList<>();
    SecondHand secondHand;
    MinuteHand minuteHand;
    HourHand hourHand;

    public AnalogClock(City city, SecondHand secondHand, MinuteHand minuteHand, HourHand hourHand) {
        super(city);
        this.secondHand = secondHand;
        this.minuteHand = minuteHand;
        this.hourHand = hourHand;

        hands.add(secondHand);
        hands.add(minuteHand);
        hands.add(hourHand);
    }

    public boolean isTimeSet() {
        LocalTime localTime = LocalTime.now();
            if (seconds != localTime.getSecond() && minutes != localTime.getMinute() && hours != localTime.getHour()) {
                return true;
            }
            else {
                return false;
            }
        }

    public void toSvg(String filePath) throws IOException {
        LocalTime newLocalTime = LocalTime.of(hours, minutes, seconds);
        String hoursSvg = "";
        String minutesSvg = "";
        String secondsSvg = "";
        String beginning = "<svg width=\"200\" height=\"200\" viewBox=\"-100 -100 200 200\" xmlns=\"http://www.w3.org/2000/svg\">";
        String newLine = System.lineSeparator();
        String clock = "<circle cx=\"0\" cy=\"0\" r=\"90\" fill=\"none\" stroke=\"black\" stroke-width=\"2\" />\n" +
                "  <g text-anchor=\"middle\">\n" +
                "    <text x=\"0\" y=\"-80\" dy=\"6\">12</text>\n" +
                "    <text x=\"80\" y=\"0\" dy=\"4\">3</text>\n" +
                "    <text x=\"0\" y=\"80\" dy=\"6\">6</text>\n" +
                "    <text x=\"-80\" y=\"0\" dy=\"4\">9</text>\n" +
                "  </g>";
        if (isTimeSet() == true) {
            secondHand.setTime(newLocalTime);
            minuteHand.setTime(newLocalTime);
            hourHand.setTime(newLocalTime);
            for (int i = 0; i < hands.size(); i++) {
                hoursSvg = hands.get(0).toSvg();
                minutesSvg = hands.get(1).toSvg();
                secondsSvg = hands.get(2).toSvg();
            }
        }
        else {
            for (int i = 0; i < hands.size(); i++) {
                hoursSvg = hands.get(0).toSvg();
                minutesSvg = hands.get(1).toSvg();
                secondsSvg = hands.get(2).toSvg();
            }
        }
        String ending = "</svg>";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(beginning);
        writer.write(newLine);
        writer.write(clock);
        writer.write(newLine);
        writer.write(newLine);
        writer.write(newLine);
        writer.write(hoursSvg);
        writer.write(newLine);
        writer.write(newLine);
        writer.write(minutesSvg);
        writer.write(newLine);
        writer.write(newLine);
        writer.write(secondsSvg);
        writer.write(newLine);
        writer.write(ending);

        writer.close();
    }
}
