import java.time.LocalTime;

public abstract class ClockHand {
    protected double xStartPosition;
    protected double yStartPosition;
    protected double xEndPosition;
    protected double yEndPosition; //max length = 50
    protected double angle;
    protected int length;

    public ClockHand(double xStartPosition, double yStartPosition) {
        if (xStartPosition == 0 && yStartPosition == 0) {
            this.xStartPosition = xStartPosition;
            this.yStartPosition = yStartPosition;
        }
        else {
            throw new IllegalArgumentException("Współrzędne początkowe (współrzędne środka okręgu, czyli analogowego zegara) mogą być tylko zerami");
        }
    }

    public abstract void setTime(LocalTime time);
    public abstract String toSvg();

}
