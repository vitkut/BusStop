package models;

public class Service implements Comparable<Service>{

    private String name;
    private Time departureTime;
    private Time arrivalTime;

    public Service(String name, Time departureTime, Time arrivalTime) {
        this.name = name;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getName() {
        return name;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    //Method returns time in travel
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
