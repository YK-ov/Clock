import java.time.LocalTime;

public abstract class Clock {
    protected int seconds;
    protected int hours;
    protected int minutes;
    private City city;

    public Clock(City city) {
        this.city = city;
    }

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

    public void setCity(City newCity) {
        int summerTimeDifference = newCity.getSummerTime() - city.getSummerTime();
        System.out.println(summerTimeDifference);
        System.out.println(newCity.getSummerTime());
        System.out.println(city.getSummerTime());
        this.hours = this.hours + summerTimeDifference;

        if (this.hours > 23) {
            this.hours = this.hours - 24;
        }
        else if (this.hours < 0) {
            this.hours = this.hours + 24;
        }
        this.city = newCity;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
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