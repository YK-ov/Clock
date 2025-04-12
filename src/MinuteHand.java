import java.time.LocalTime;

public class MinuteHand extends ClockHand {
    int length;
    String colour;
    int width;

    public MinuteHand(double xStartPosition, double yStartPosition, int length, String colour, int width) {
        super(xStartPosition, yStartPosition);
        this.length = length;
        this.colour = colour;
        this.width = width;
    }

    @Override
    public void setTime(LocalTime time) {
        angle = time.getMinute() * 6 + time.getSecond() * 0.1;
        /* 6 jako: 360/60=6 stopni przez jedna minute
          0.1 jako: wskazowka minutowa sie przesuwa na 6 stopni kazde 60 sekund,
            dlatego 6/60 = 0.1 odnosnie wskazowki minutowej
            */
        xEndPosition = Math.round((xStartPosition + length * Math.sin(Math.toRadians(angle))) * 100) / 100.00;
        yEndPosition = Math.round((yStartPosition - length * Math.cos(Math.toRadians(angle))) * 100) / 100.00;
    }

    @Override
    public String toSvg() {
        return "<line x1=\"" + xStartPosition + "\" y1=\"" + yStartPosition + "\" x2=\"" + xEndPosition +
                "\" y2=\"" + yEndPosition + "\" stroke=\"" + colour + "\" stroke-width=\"" + width + "\" />";
    }
}
