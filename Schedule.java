import java.util.Date;

public class Schedule implements Schedulable {
    private int flightNumber;
    private String airlineName;
    private String source;
    private String destination;
    private String durationTime;
    private Date date;
    private String departureTime;

    // Constructor
    public Schedule(String airlineName) {
        this.airlineName = airlineName;
    }

    // Method to check if a flight exists between given source and destination
    public void checkFlightDetails(String from, String to) {
        if (from.equalsIgnoreCase(source) && to.equalsIgnoreCase(destination)) {
            displaySchedule();
        } else {
            System.out.println("No flight available from " + from + " to " + to);
        }
    }

    // Method to set flight details
    public void setFlightDetails(String from, String to, String duration, Date date, String time, String airline) {
        this.source = from;
        this.destination = to;
        this.durationTime = duration;
        this.date = date;
        this.departureTime = time;
        this.airlineName = airline;
    }

    // Override toString() to display flight details
    @Override
    public String toString() {
        return "Flight Details:\n" +
                "Flight Number: " + flightNumber + "\n" +
                "Airline: " + airlineName + "\n" +
                "From: " + source + "\n" +
                "To: " + destination + "\n" +
                "Duration: " + durationTime + "\n" +
                "Date: " + date + "\n" +
                "Time: " + departureTime;
    }

    // Implement displaySchedule() from Schedulable
    @Override
    public void displaySchedule() {
        System.out.println(toString());
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
