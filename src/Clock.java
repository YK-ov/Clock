import java.time.LocalTime;

public abstract class Clock {
    protected int seconds;
    protected int hours;
    protected int minutes;
    private City city;

    public void setCurrentTime(){
        LocalTime now = LocalTime.now();
        this.hours = now.getHour();
        this.minutes = now.getMinute();
        this.seconds = now.getSecond();
    }

    public void setTime(int hours, int minutes, int seconds){
        if (hours >= 0 && hours < 24) {
            this.seconds = seconds;
            this.minutes = minutes;
            this.hours = hours;
        }
        else {
            throw new IllegalArgumentException("Godzina musi ustawiona byc wedlug 24-godzinnego formatu");
        }
    }

    @Override
    public String toString() {
        return "Clock{" +
                hours +
                ":" + minutes +
                ":" + seconds +
                '}';
    }
}