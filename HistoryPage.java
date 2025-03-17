import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class HistoryPage extends JPanel implements ActionListener {
    JPanel logoPanel;
    JPanel buttonPanel;
    JPanel backButtonPanel;
    JButton backButton;
    JPanel parentPanel;  // Reference to parent panel
    CardLayout cardLayout;

    private static final String URL = "jdbc:mysql://localhost:3306/flight_reservation_system";
    private static final String USER = "root";
    private static final String PASSWORD = "ROTH250305#"; // ⚠️ Consider using environment variables for security

    // User class definition (assumed, since it wasn’t provided)
    public static class User {
        private int id;
        private String name, sex, email, flightNumber, airlineName, source, className;

        public User(int id, String name, String sex, String email, String flightNumber, 
                    String airlineName, String source, String className) {
            this.id = id;
            this.name = name;
            this.sex = sex;
            this.email = email;
            this.flightNumber = flightNumber;
            this.airlineName = airlineName;
            this.source = source;
            this.className = className;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getSex() { return sex; }
        public String getEmail() { return email; }
        public String getFlightNumber() { return flightNumber; }
        public String getAirlineName() { return airlineName; }
        public String getSource() { return source; }
        public String getClassName() { return className; }

        @Override
        public String toString() {
            return "User{id=" + id + ", name=" + name + ", sex=" + sex + ", email=" + email + 
                   ", flight=" + flightNumber + ", airline=" + airlineName + ", source=" + source + 
                   ", class=" + className + "}";
        }
    }

    private static ArrayList<User> showHistory(Connection connection) {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM passenger";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("passenger_id");
                String name = resultSet.getString("passenger_name");
                String sex = resultSet.getString("passenger_sex");
                String email = resultSet.getString("passenger_email");
                String flightNumber = resultSet.getString("flight_number");
                String airlineName = resultSet.getString("airline_name");
                String source = resultSet.getString("source");
                String className = resultSet.getString("class_name").trim();

                User user = new User(id, name, sex, email, flightNumber, airlineName, source, className);
                users.add(user); // Add user to the list
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

        // 🔹 Logo Panel
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

        // Display history in a JTable
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(40, 180, 400, 300);
        buttonPanel.setLayout(new BorderLayout()); // Changed to BorderLayout for JTable

        String[] columnNames = {"ID", "Name", "Sex", "Email", "Flight", "Airline", "Source", "Class"};
        JTable historyTable;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            ArrayList<User> users = showHistory(connection);
            Object[][] data = new Object[users.size()][8];
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                data[i] = new Object[]{
                    user.getId(), user.getName(), user.getSex(), user.getEmail(),
                    user.getFlightNumber(), user.getAirlineName(), user.getSource(), user.getClassName()
                };
            }
            historyTable = new JTable(data, columnNames);
            historyTable.setFillsViewportHeight(true);
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            historyTable = new JTable(new Object[0][0], columnNames); // Empty table on error
        }

        JScrollPane scrollPane = new JScrollPane(historyTable);
        buttonPanel.add(scrollPane, BorderLayout.CENTER);

        backButtonPanel.add(backButton);

        // Add panels to main panel
        this.add(logoPanel);
        this.add(historyLabel);
        this.add(buttonPanel);
        this.add(backButtonPanel); // Only added once
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            cardLayout.show(parentPanel, "LandingPage");
        }
    }
}