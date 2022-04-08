public class MDate {
    /*
     Design and implement your own Date class including the following attributes: day,
month, year, timeZone, hour, minute
o Required constructors
o Required accessor/mutators
     */
    private int day;
    private int month;
    private int year;
    private String timeZone;
    private int hour;
    private int minute;
    public MDate(int day,int month,int year,String timeZone,int hour,int minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.timeZone = timeZone;
        this.hour = hour;
        this.minute = minute;
    }
    public boolean compareTo(MDate date) {
        if(this.day == date.getDay() && this.month == date.getMonth() && this.year == date.getYear()
                && this.hour == date.getHour() && this.minute == date.getMinute() && this.timeZone.equals(date.getTimeZone())) {
            return true;
        }
        return false;
    }
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return "MDate{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", timeZone='" + timeZone + '\'' +
                ", hour=" + hour +
                ", minute=" + minute +
                '}';
    }
}
