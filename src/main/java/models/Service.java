package models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Service implements Comparable<Service>{

    private String name;
    private LocalDateTime departure;
    private LocalDateTime arrival;

    public Service(String name, LocalDateTime departure, LocalDateTime arrival) {
        this.name = name;
        this.departure = departure;
        this.arrival = arrival;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public LocalTime getTravelTime(){
        LocalDateTime travelTime = arrival;
        travelTime = travelTime.minusDays(departure.getDayOfMonth());
        travelTime = travelTime.minusHours(departure.getHour());
        travelTime = travelTime.minusMinutes(departure.getMinute());
        LocalTime result = LocalTime.of(travelTime.getHour(), travelTime.getMinute());
        return result;
    }

    @Override
    public int compareTo(Service o) {
        return this.departure.compareTo(o.departure);
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        return name+" "+departure.format(format)+" "+arrival.format(format);
    }
}
