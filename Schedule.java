import java.util.ArrayList;
import java.util.Date;

public class Schedule {
    private String scheduleID;
    private int flightNumber;
    private String airlineName;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private Date date;
    private ArrayList<Schedule> schedules = new ArrayList<>();

    // Constructor
    public Schedule(String airlineName) {
        this.airlineName = airlineName;
    }

    public Schedule(){}

    // Method to check if a flight exists between given source and destination
    public void checkFlightDetails(String from, String to) {
        if (from.equalsIgnoreCase(source) && to.equalsIgnoreCase(destination)) {
            System.out.println(" flight(s) available from " + from + " to " + to);
        } else {
            System.out.println("No flight available from " + from + " to " + to);
        }
    }

    // Method to set flight details
    public void setFlightDetails(String from, String to, String airline, String departureTime, String arrivalTime, Date date) {
        this.source = from;
        this.destination = to;
        this.airlineName = airline;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.date = date;
    }

    public String toString() {
        return "Flight Details:\n" +
                "Schedule ID: " + scheduleID +
                "Flight Number: " + flightNumber + "\n" +
                "Airline: " + airlineName + "\n" +
                "From: " + source + "\n" +
                "To: " + destination + "\n" +
                "Departure: " + departureTime + "\n" +
                "Arrival: " + arrivalTime +
                "Date: " + date + "\n";
                
    }

  

    // Getters and Setters
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
