import java.util.Date;
import java.util.Scanner;

public class Schedule {
    private String fromWhere;
    private String toWhere;
    private String durationTime;
    private Date date;
    private String time;
    private String airlineName;

    private void checkFlightDetails( String date) {
        if (date.equals("10/01/2026")) {
            System.out.println("Flight Details");
            System.out.println("From: " + fromWhere);
            System.out.println("To: " + toWhere);
            System.out.println("Duration: " + durationTime);
            System.out.println("Date: " + date);
            System.out.println("Time: " + time);
            System.out.println("Airline: " + airlineName);
        } else {
            System.out.println("No flight available on this date.");
        }
        
    }

    private void setFlightDetails(String from, String to, String duration, String date, String Time, String airline) {
            this.fromWhere = from;
            this.toWhere = to;
            this.durationTime = duration;
            this.date = date;
            this.time = Time;
            this.airlineName = airline;
    }

    public Schedule(String airlineName) {
        this.airlineName = airlineName;
    }
    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public String getToWhere() {
        return toWhere;
    }

    public void setToWhere(String toWhere) {
        this.toWhere = toWhere;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
