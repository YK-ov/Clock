import java.time.LocalTime;

public class HourHand extends ClockHand {
    int length;
    String colour;
    int width;

    public HourHand(double xStartPosition, double yStartPosition, int length, String colour, int width) {
        super(xStartPosition, yStartPosition);
        this.length = length;
        this.colour = colour;
        this.width = width;
    }

    @Override
    public void setTime(LocalTime time) {
        angle = time.getHour() * 30 + time.getMinute() * 0.5 + time.getSecond() * 30.0/3600.0;
    /*
        30 jako: 360/12 = 30, 30 stopni co 60 min przesuniecie wskazowki godzninowej
            0.5 jako: 30 stopni kazde 60 minut, stad: 30/60 = 0.5 odnosnie wskazowki godzinowej
                30.0/3600.0 (rowna sie okolo 0.0083) jako: 30 stopni przez 3600 sekund w jednej godznie (odnosnie wskazowki godzninowej)
                 */
    }


    @Override
    public String toSvg() {
        return "<line x1=\"" + xStartPosition + "\" y1=\"" + yStartPosition +
                "\" x2=\"" + xStartPosition + "\" y2=\"" + (yStartPosition - length) +
                "\" stroke=\"" + colour + "\" stroke-width=\"" + width +
                "\" transform=\"rotate(" + angle + ")\" />";
    }
}
