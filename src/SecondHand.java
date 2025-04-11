import java.time.LocalTime;

public class SecondHand extends ClockHand {
    int radius;
    String colour;
    int width;

    public SecondHand(double xStartPosition, double yStartPosition, int radius, String colour, int width) {
        super(xStartPosition, yStartPosition);
        this.radius = radius;
        this.colour = colour;
        this.width = width;
    }

    @Override
    public void setTime(LocalTime time) {
        angle = time.getSecond();
        if (angle >= 0 && angle <= 15) {
            //x+, y-  pierwsza cwiartka
            xEndPosition = Math.round(xStartPosition + radius * Math.cos(Math.toRadians(angle)) * 100.00) / 100.00;
            yEndPosition = -Math.round(yEndPosition + radius * Math.sin(Math.toRadians(angle)) * 100.00) / 100.00;
        }
        else if (angle > 15 && angle <= 30) {
            //x+ y+ druga cwiartka
            xEndPosition = Math.round(xStartPosition + radius * Math.cos(Math.toRadians(angle)) * 100.00) / 100.00;
            yEndPosition = Math.round(yEndPosition + radius * Math.sin(Math.toRadians(angle)) * 100.00) / 100.00;
        }
        else if (angle > 30 && angle <= 45) {
            //x- y+  trzecia cwiartka
            xEndPosition = -Math.round(xStartPosition + radius * Math.cos(Math.toRadians(angle)) * 100.00) / 100.00;
            yEndPosition = Math.round(yEndPosition + radius * Math.sin(Math.toRadians(angle)) * 100.00) / 100.00;
        }
        else if (angle > 45 && angle < 60) {
            // x- y- czwartka cwiartka
            xEndPosition = -Math.round(xStartPosition + radius * Math.cos(Math.toRadians(angle)) * 100.00) / 100.00;
            yEndPosition = -Math.round(yEndPosition + radius * Math.sin(Math.toRadians(angle)) * 100.00) / 100.00;
        }
        else if (angle == 60){
            xEndPosition = xStartPosition;
            yEndPosition = yStartPosition;
        }

        System.out.println(angle);
        System.out.println(xEndPosition + " " + yEndPosition);
    }

    @Override
    public String toSvg() {
        return "<line x1=\"" + xStartPosition + "\"" + "y1=\"" + yStartPosition + "\"" + "x2= \"" + xEndPosition +
                "\"" + "y2=\"" + yEndPosition + "\"" + "stroke=\"" + colour + "\"" + "stroke-width=\"" + width + "\"" + "/>";
    }
}
