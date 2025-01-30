import java.util.Scanner;

public class Schedule {
    private Scanner input = new Scanner(System.in);
    private String fromWhere;
    private String toWhere;
    private String durationTime;
    private String date;
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

    public static void main(String[] args) {
       Direction Direction1 = new Direction();
    }

    public Schedule(String airlineName) {
        this.airlineName = airlineName;
    }
}
