import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/flight_reservation_system";
    private static final String USER = "root";
    private static final String PASSWORD = "ROTH250305#"; // ⚠️ Consider using environment variables for security

    public static void main(String[] args) {
        // Create a single connection to the database
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Fetch and display all passengers
            ArrayList<User> users = fetchPassengers(connection);
            System.out.println("\n===== Existing Passengers =====");
            for (User user : users) {
                System.out.println(user);
            }

            // Insert a new passenger
            insertPassenger(connection);

            // Fetch and display updated passengers list
            users = fetchPassengers(connection);
            System.out.println("\n===== Updated Passengers =====");
            for (User user : users) {
                System.out.println(user);
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    // Method to fetch all passengers from the database
    private static ArrayList<User> fetchPassengers(Connection connection) {
      ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM passenger";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("passenger_id");
                String name = resultSet.getString("passenger_name");
                String sex = resultSet.getString("passenger_sex");
                String phone = resultSet.getString("passenger_phone");
                String email = resultSet.getString("passenger_email");
                String pass = resultSet.getString("passenger_password");
                String passportNum = resultSet.getString("passport_num").trim();

                users.add(new Passenger(id, name, sex, phone, passportNum, email, pass));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching passengers: " + e.getMessage());
        }
        return users;
    }

    // Method to insert a new passenger
    private static void insertPassenger(Connection connection) {
        String query = "INSERT INTO passenger (passenger_name, passenger_sex, passenger_phone, passenger_email, passenger_password, passport_num) VALUES (?, ?, ?, ?, ?, ?)";

        try (Scanner scanner = new Scanner(System.in);
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Take user input
            System.out.println("\n===== Insert New Passenger =====");
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Sex (M/F): ");
            String sex = scanner.nextLine();

            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Password: ");
            String pass = scanner.nextLine();

            System.out.print("Enter Passport Number: ");
            String passportNum = scanner.nextLine();

            // Set parameters for the query
            pstmt.setString(1, name);
            pstmt.setString(2, sex);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            pstmt.setString(5, pass);
            pstmt.setString(6, passportNum);

            // Execute update
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Passenger added successfully!");
            } else {
                System.out.println("Failed to insert passenger.");
            }

        } catch (SQLException e) {
            System.err.println("Error inserting passenger: " + e.getMessage());
        }
    }
}
