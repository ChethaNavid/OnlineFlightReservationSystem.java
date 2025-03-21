import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;

public class HistoryPage extends JPanel implements ActionListener {
    private JPanel logoPanel;
    private JPanel buttonPanel;
    private JPanel backButtonPanel;
    private JButton backButton;
    private JPanel parentPanel;  // Reference to parent panel
    private CardLayout cardLayout;

    private static final String URL = "jdbc:mysql://localhost:3306/flight_reservation_system";
    private static final String USER = "root";
    private static final String PASSWORD = "ROTH250305#"; // ⚠️ Consider using environment variables for security

    // User class definition
    public static class User {
        private String name, email, flightNumber, airlineName, seat, source, destination, className;

        public User(String name, String email, String flightNumber, 
                    String airlineName, String seat, String source,
                    String destination, String className) {
            this.name = name;
            this.email = email;
            this.flightNumber = flightNumber;
            this.airlineName = airlineName;
            this.seat = seat;
            this.source = source;
            this.destination = destination;
            this.className = className;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getFlightNumber() { return flightNumber; }
        public String getAirlineName() { return airlineName; }
        public String getSeat() { return seat; }
        public String getSource() { return source; }
        public String getDestination() { return destination; }
        public String getClassName() { return className; }

        @Override
        public String toString() {
            return String.format("User{id=%d, name=%s, sex=%s, email=%s, flight=%s, airline=%s, source=%s, class=%s}",
                    name, email, flightNumber, airlineName, seat, source, destination, className);
        }
    }

    private static ArrayList<User> showHistory(Connection connection) {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM history"; // Query the view instead of the table

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("passenger_name");
                String email = resultSet.getString("passenger_email");
                String flightNumber = resultSet.getString("flight_number");
                String airlineName = resultSet.getString("airline_name");
                String seatNumber = resultSet.getString("seat_number");
                String source = resultSet.getString("source");
                String destination = resultSet.getString("destination");
                String className = resultSet.getString("class_name").trim();

                User user = new User(name, email, flightNumber, airlineName, seatNumber, source,destination, className);
                users.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Error displaying history: " + e.getMessage());
        }
        return users;
    }

    public HistoryPage(JPanel parentPanel, CardLayout cardLayout) {
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        
        this.setLayout(null);
        this.setBounds(50, 50, 500, 500);
        this.setBackground(Color.WHITE);

        backButtonPanel = new JPanel();
        backButtonPanel.setBackground(Color.WHITE);
        backButtonPanel.setBounds(20, 10, 400, 40);
        backButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        // Load and scale the image
        ImageIcon image = new ImageIcon("./assets/image.png");
        Image scaledImage = image.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        // Logo Panel
        logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        logoPanel.setBounds(40, 50, 400, 60);
        logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel logoLabel = new JLabel("FLIGHT RESERVATION SYSTEM", resizedIcon, JLabel.CENTER);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        logoLabel.setHorizontalTextPosition(JLabel.RIGHT);
        logoLabel.setVerticalTextPosition(JLabel.CENTER);
        logoLabel.setIconTextGap(15);
        logoPanel.add(logoLabel);

        JLabel historyLabel = new JLabel("History Page", JLabel.CENTER);
        historyLabel.setBounds(40, 120, 400, 60);
        historyLabel.setFont(new Font("Arial", Font.BOLD, 20));
        historyLabel.setHorizontalTextPosition(JLabel.RIGHT);
        historyLabel.setVerticalTextPosition(JLabel.CENTER);

        // Display history in a JTable
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(40, 180, 400, 300);
        buttonPanel.setLayout(new BorderLayout()); // Changed to BorderLayout for JTable

        String[] columnNames = {"Passenger Name", "passenger Email", "Flight Number", "Airline Name", "Seat Number", "Source", "Destination", "Class Name"};
        JTable historyTable;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            ArrayList<User> users = showHistory(connection);
            Object[][] data = new Object[users.size()][8];
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                data[i] = new Object[]{
                    user.getName(), user.getEmail(),
                    user.getFlightNumber(), user.getAirlineName(),
                    user.getSeat(), user.getSource(), user.getDestination(), user.getClassName()
                };
            }
            historyTable = new JTable(data, columnNames);
            historyTable.setFillsViewportHeight(true);
            historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable auto-resize to allow scrolling
            // Set preferred column widths to ensure visibility of full data
            historyTable.getColumnModel().getColumn(0).setPreferredWidth(100);  // ID
            historyTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Name
            historyTable.getColumnModel().getColumn(2).setPreferredWidth(60);  // Sex
            historyTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Email (longer)
            historyTable.getColumnModel().getColumn(4).setPreferredWidth(60);  // Flight
            historyTable.getColumnModel().getColumn(5).setPreferredWidth(100); // Airline
            historyTable.getColumnModel().getColumn(6).setPreferredWidth(100); // Source
            historyTable.getColumnModel().getColumn(7).setPreferredWidth(100);  // Class
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            historyTable = new JTable(new Object[0][0], columnNames); // Empty table on error
            historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Consistent behavior
        }

        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Enable horizontal scrolling
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);   // Enable vertical scrolling
        buttonPanel.add(scrollPane, BorderLayout.CENTER);

        backButtonPanel.add(backButton);

        // Add panels to main panel
        this.add(logoPanel);
        this.add(historyLabel);
        this.add(buttonPanel);
        this.add(backButtonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            cardLayout.show(parentPanel, "LandingPage");
        }
    }
}