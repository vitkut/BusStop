package models;

import java.util.Comparator;

public class Service implements Comparable<Service>{

    private String name;
    private Time departureTime;
    private Time arrivalTime;

    public Service() {
    }

    public Service(String name, Time departureTime, Time arrivalTime) {
        this.name = name;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Time getTravelTime() throws InputException{
        return Time.subtr(arrivalTime, departureTime);
    }

    @Override
    public String toString() {
        return name+" "+departureTime+" "+arrivalTime;
    }

    public int compareTo(Service o) {
        return this.departureTime.compareTo(o.departureTime);
    }
}
