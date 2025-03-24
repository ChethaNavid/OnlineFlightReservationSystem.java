import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Schedule {
    private int scheduleID;
    private String flightNumber;
    private String airlineName;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private Date date;
    private String classType;
    private static int totalFlight = 0;
    private static ArrayList<Schedule> schedules = new ArrayList<>();

    // Constructor
    public Schedule(String flightNumber, String airlineName, String source, String destination, 
                    String departureTime, String arrivalTime, Date date, String classType) {
        this.scheduleID = ++totalFlight;
        this.flightNumber = flightNumber;
        this.airlineName = airlineName;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.date = date;
        this.classType = classType;
    }

    public Schedule(int scheduleID, String flightNumber, String airlineName, String source, String destination, 
                    String departureTime, String arrivalTime, Date date, String classType) {
        this.scheduleID = scheduleID;
        this.flightNumber = flightNumber;
        this.airlineName = airlineName;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.date = date;
        this.classType = classType;
    }

    public Schedule() {}

    public int getFlightID() {
        return this.scheduleID;
    }
    public String getSource() {
        return this.source;
    }

    public String getDestination() {
        return this.destination;
    }

    public String getAirlineName() {
        return this.airlineName;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public String getDepartureTime() {
        return this.departureTime;
    }

    public String getArrivalTime() {
        return this.arrivalTime;
    }

    public Date getDate() {
        return this.date;
    }

    public String getClassType() {
        return this.classType;
    }

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

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/flight_reservation_system", "root", "@Vid/1105.dev");
    }

    public static ArrayList<Schedule> getAllFlights() {
        ArrayList<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM Schedule";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int flightID = rs.getInt("schedule_id");
                String flightNo = rs.getString("flight_number");
                String airline = rs.getString("airline_name");
                String source = rs.getString("source");
                String destination = rs.getString("destination");
                String departureTime = rs.getString("departure_time");
                String arrivalTime = rs.getString("arrival_time");
                Date date = rs.getDate("date");
                String classType = rs.getString("class_type");

                // Create Schedule object and add to list
                schedules.add(new Schedule(flightID, flightNo, airline, source, destination, departureTime, arrivalTime, date, classType));
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Print SQL error details
        }
        return schedules;
        
    }

    public static ArrayList<Schedule> searchFlights(String source, String destination, LocalDate date, String classType) {
        ArrayList<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM Schedule WHERE source = ? AND destination = ? AND date = ? AND class_type = ?";
        
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, source);
            pstmt.setString(2, destination);
            pstmt.setDate(3, java.sql.Date.valueOf(date));
            pstmt.setString(4, classType);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int flightID = rs.getInt("schedule_id");
                    String flightNo = rs.getString("flight_number");
                    String airline = rs.getString("airline_name");
                    String depTime = rs.getString("departure_time");
                    String arrTime = rs.getString("arrival_time");
                    Date flightDate = rs.getDate("date");
                    
                    schedules.add(new Schedule(flightID, flightNo, airline, source, destination, depTime, arrTime, flightDate, classType));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    public static Schedule getBookedFlightForPassenger(Passenger passenger, String flightID) {
        String sql = "SELECT * FROM Schedule WHERE passenger_id = ? AND schedule_id = ?"; // Assuming you have a relationship between passengers and flights
        
        try (Connection connection = connect(); 
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, passenger.getId()); // Set the passenger ID as a parameter
            pstmt.setString(2, flightID);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Assuming Flight has properties like flightID, flightNumber, source, destination, etc.
                return new Schedule(
                    rs.getInt("schedule_id"),
                    rs.getString("flight_number"),
                    rs.getString("airline_name"),
                    rs.getString("source"),
                    rs.getString("destination"),
                    rs.getString("departure_time"),
                    rs.getString("arrival_time"),
                    rs.getDate("date"),
                    rs.getString("class_type")
                );
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        
        return null; // No booked flight found
    }
    

}
