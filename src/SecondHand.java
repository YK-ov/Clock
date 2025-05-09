import java.time.LocalTime;

public class SecondHand extends ClockHand {
    int length;
    String colour;
    int width;

    public SecondHand(double xStartPosition, double yStartPosition, int length, String colour, int width) {
        super(xStartPosition, yStartPosition);
        this.length = length;
        this.colour = colour;
        this.width = width;
    }

    @Override
    public void setTime(LocalTime time) {
        angle = time.getSecond() * 6; // 6 jako 360 stopni / 60 = 6 stopni przez jedna sekunde
        xEndPosition = Math.round((xStartPosition + length * Math.sin(Math.toRadians(angle))) * 100) / 100.00;
        yEndPosition = Math.round((yStartPosition - length * Math.cos(Math.toRadians(angle))) * 100) / 100.00;
    }

    @Override
    public String toSvg() {
        return "<line x1=\"" + xStartPosition + "\" y1=\"" + yStartPosition + "\" x2=\"" + xEndPosition +
                "\" y2=\"" + yEndPosition + "\" stroke=\"" + colour + "\" stroke-width=\"" + width + "\" />";
    }
}
