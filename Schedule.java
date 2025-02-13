import java.util.Date;
import java.util.Scanner;

public class Schedule implements Schedulable{
    private int flightNumber;
    private String airlineName;
    private String source;
    private String destination;
    private String durationTime;
    private Date date;
    private String departureTime;
    

    private void checkFlightDetails( String date) {
        if (date.equals("10/01/2026")) {
            System.out.println("Flight Details");
            System.out.println("From: " + source);
            System.out.println("To: " + destination);
            System.out.println("Duration: " + durationTime);
            System.out.println("Date: " + date);
            System.out.println("Time: " + time);
            System.out.println("Airline: " + airlineName);
        } else {
            System.out.println("No flight available on this date.");
        }
        
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    @Override
    public void displaySchedule() {
        // TODO Auto-generated method stub
        
    }

    private void setFlightDetails(String from, String to, String duration, String date, String Time, String airline) {
            this.source = from;
            this.destination = to;
            this.durationTime = duration;
            this.date = date;
            this.time = Time;
            this.airlineName = airline;
    }

    public Schedule(String airlineName) {
        this.airlineName = airlineName;
    }
    public String getsource() {
        return source;
    }

    public void setsource(String source) {
        this.source = source;
    }

    public String getdestination() {
        return destination;
    }

    public void setdestination(String destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
