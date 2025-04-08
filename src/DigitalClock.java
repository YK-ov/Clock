public class DigitalClock extends Clock {
    private final int timeFormat;

    public DigitalClock(City city, int timeFormat) {
        super(city);
        if (timeFormat == 12 || timeFormat == 24) {
            this.timeFormat = timeFormat; //cases for 12,24 or not + add city from clock as constructor, clock class: setTime depending on city by the summerTime zone
        }
        else{
            throw new IllegalArgumentException("Format godzin musi byc 12 lub 24");
        }
    }


    public int changeFormat() {
        int difference = 12;  // bo zmieniamy tryb 12 gogdzinowy
        if (hours >= 0 && hours < 1) {
            return difference;
        }
        else if (hours >= 1 && hours < 12) {
            return hours;
        }
        else if (hours >= 12 && hours < 13) {
            return hours;
        }
        else if (hours >= 13 && hours < 24) {
            return hours - difference;
        }
        throw new IllegalArgumentException("Zly format");
    }

    public String isAMorPM() {
        if (hours >= 0 && hours < 12) {
            return "AM";
        }
        else if (hours >= 12 && hours < 24) {
            return "PM";
        }
        else {
            throw new IllegalArgumentException("Zly format");
        }
    }

    public String toString() {
        switch (timeFormat) {
            case 24:
                return super.toString();
            case 12:
                int formattedHour = changeFormat();
                String timePeriod = isAMorPM();
                return "DigitalClock{" +
                        formattedHour + ":" + minutes + ":" + seconds + " " + timePeriod + '}';
            default:
                return "Zły format";
        }
    }

}