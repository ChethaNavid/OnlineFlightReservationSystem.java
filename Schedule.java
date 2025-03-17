import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Schedule {
    private String scheduleID;
    private String flightNumber;
    private String airlineName;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private Date date;
    private static ArrayList<Schedule> schedules = new ArrayList<>();

    // Constructor
    public Schedule(String scheduleID, String flightNumber, String airlineName, String source, String destination, 
                    String departureTime, String arrivalTime, Date date) {
        this.scheduleID = scheduleID;
        this.flightNumber = flightNumber;
        this.airlineName = airlineName;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.date = date;
    }

    public Schedule() {}

    // ✅ Method to Read from File and Store Schedule
    public static void readSchedulesFromFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try (Scanner scan = new Scanner(Paths.get("AirlineSchedule1.csv"))) {
            // Skip header row if present
            if (scan.hasNextLine()) {
                scan.nextLine(); // Skips the header row
            }

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split(",");
                if (parts.length < 8) continue; // Skip invalid lines
                
                String scheduleID = parts[0];
                String flightNumber = parts[1];
                String airlineName = parts[2];
                String source = parts[3];
                String destination = parts[4];
                String departureTime = parts[5];
                String arrivalTime = parts[6];
                Date date = null;
                
                // Try to parse the date and handle any errors
                try {
                    date = dateFormat.parse(parts[7]); // Convert date string to Date object
                } catch (Exception e) {
                    System.out.println(line);
                    continue; // Skip this entry if the date is invalid
                }

                Schedule schedule = new Schedule(scheduleID, flightNumber, airlineName, source, destination, 
                                                 departureTime, arrivalTime, date);
                schedules.add(schedule);
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // ✅ Display all schedules after reading the file
        for (Schedule s : schedules) {
            System.out.println(s);
        }
    }

    // ✅ Check Flights on a Given Date
    public static void checkFlightsByDate(String dateInput) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date inputDate = dateFormat.parse(dateInput);
            for (Schedule schedule : schedules) {
                if (schedule.getDate().equals(inputDate)) {
                    System.out.println(schedule);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Use dd-MM-yyyy");
        }
    }

    // ✅ Check if Flight Exists Between Two Cities
    public static void checkFlightDetails(String from, String to) {
        boolean found = false;
        for (Schedule schedule : schedules) {
            if (schedule.getSource().equalsIgnoreCase(from) && schedule.getDestination().equalsIgnoreCase(to)) {
                System.out.println(schedule);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No flight available from " + from + " to " + to);
        }
    }

    // ✅ Override toString() for Display
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "Flight Details:\n" +
                "Schedule ID: " + scheduleID + "\n" +
                "Flight Number: " + flightNumber + "\n" +
                "Airline: " + airlineName + "\n" +
                "From: " + source + "\n" +
                "To: " + destination + "\n" +
                "Departure: " + departureTime + "\n" +
                "Arrival: " + arrivalTime + "\n" +
                "Date: " + dateFormat.format(date) + "\n";
    }

    // ✅ Getters
    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getAirlineName() {
        return this.airlineName;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public Date getDate() {
        return date;
    }

    public static void main(String[] args) {
        readSchedulesFromFile();
        checkFlightsByDate("12-12-2021"); // Example usage of checkFlightsByDate
        checkFlightDetails("Phnom Penh", "Seoul"); // Example usage of checkFlightDetails
    }
}
