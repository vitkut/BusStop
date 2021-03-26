package models;

public class Time implements Comparable<Time>{

    private byte hours;
    private byte minutes;

    public Time(byte hours, byte minutes) throws InputException{
        validation(hours, minutes);
        this.hours = hours;
        this.minutes = minutes;
    }

    public byte getHours() {
        return hours;
    }

    public void setHours(byte hours) {
        this.hours = hours;
    }

    public byte getMinutes() {
        return minutes;
    }

    public void setMinutes(byte minutes) {
        this.minutes = minutes;
    }

    public void validation(byte hours, byte minutes) throws InputException{
        if(hours >= 24 || minutes >= 60){
            throw new InputException("Incorrect time format");
        }
    }

    @Override
    public String toString() {
        String zeroHourChar = "";
        String zeroMinuteChar = "";
        if(hours < 10){
            zeroHourChar = "0";
        }
        if(minutes < 10){
            zeroMinuteChar = "0";
        }
        return zeroHourChar+hours+":"+zeroMinuteChar+minutes;
    }

    public int compareTo(Time o) {
        if(this.hours < o.getHours()){
            return -1;
        }
        if(this.hours > o.getHours()){
            return 1;
        }
        if(this.hours == o.getHours()){
            if(this.minutes < o.getMinutes()){
                return -1;
            }
            if(this.minutes > o.getMinutes()){
                return 1;
            }
            if(this.minutes == o.getMinutes()){
                return 0;
            }
        }
        return 0;
    }

    public static Time parseTime(String str) throws InputException{
        if(str.length() != 5){
            throw new InputException("Incorrect time format");
        }
        String[] tempStrings = str.split(":");
        byte hours = Byte.parseByte(tempStrings[0]);
        byte minutes = Byte.parseByte(tempStrings[1]);
        Time time = new Time(hours, minutes);
        return time;
    }

    public static Time sum(Time t1, Time t2) throws InputException{
        byte hours = (byte) (t1.getHours()+t2.getHours());
        byte minutes = (byte) (t1.getMinutes()+t2.getMinutes());
        if(minutes >= 60){
            hours++;
            minutes -= 60;
        }
        if(hours >= 24){
            hours -= 24;
        }
        return new Time(hours, minutes);
    }

    public static Time subtr(Time t1, Time t2) throws InputException{
        byte hours = (byte) (t1.getHours()-t2.getHours());
        byte minutes = (byte) (t1.getMinutes()-t2.getMinutes());
        if(minutes < 0){
            hours--;
            minutes += 60;
        }
        if(hours < 0){
            hours += 24;
        }
        return new Time(hours, minutes);
    }
}
