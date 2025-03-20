import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class PassengerReceiptGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private Passenger passenger;

    public PassengerReceiptGUI(Passenger passenger) {
        if (passenger == null) {
            throw new IllegalArgumentException("Passenger cannot be null");
        }
        this.passenger = passenger;
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Passenger Receipt");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(createDetailsPanel(), BorderLayout.CENTER);
        mainPanel.add(createFooterPanel(), BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Flight Reservation Receipt");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        JLabel dateLabel = new JLabel("Date: " + dateFormat.format(new Date()));
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(dateLabel);

        return headerPanel;
    }

    private JPanel createDetailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(7, 2, 10, 10));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Passenger Details"));

        detailsPanel.add(new JLabel("Passenger ID:"));
        detailsPanel.add(new JLabel(String.valueOf(passenger.getId())));

        detailsPanel.add(new JLabel("Name:"));
        detailsPanel.add(new JLabel(passenger.getName() != null ? passenger.getName() : "N/A"));

        detailsPanel.add(new JLabel("Gender:"));
       

        detailsPanel.add(new JLabel("Passport Number:"));
        detailsPanel.add(new JLabel(passenger.getPassportNum() != null ? passenger.getPassportNum() : "N/A"));

        detailsPanel.add(new JLabel("Total Passengers:"));
        detailsPanel.add(new JLabel(String.valueOf(Passenger.getTotalPassenger())));

        return detailsPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> frame.dispose());

        JButton printButton = new JButton("Print");
        printButton.addActionListener(e -> {
            // Implement the actual printing logic here if needed
            JOptionPane.showMessageDialog(frame,
                "Printing functionality would be implemented here",
                "Print",
                JOptionPane.INFORMATION_MESSAGE);
        });

        footerPanel.add(printButton);
        footerPanel.add(closeButton);

        return footerPanel;
    }

    public static void showReceipt(Passenger passenger) {
        SwingUtilities.invokeLater(() -> new PassengerReceiptGUI(passenger));
    }

    // Temporary Database helper class (you should replace this with your actual implementation)
    private static class Database {
        static ArrayList<Passenger> fetchPassengers(Connection connection) throws SQLException {
            ArrayList<Passenger> passengers = new ArrayList<>();
            String sql = "SELECT * FROM Passenger LIMIT 1"; // Fetch first passenger as demo
            try (PreparedStatement pstmt = connection.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) { // Handle multiple passengers (if needed)
                    passengers.add(new Passenger(
                        rs.getInt("passenger_id"),
                        rs.getString("passenger_name"),
                        rs.getString("passenger_gender"),
                        rs.getString("passenger_phone"),
                        rs.getString("passenger_passport"),
                        rs.getString("passenger_email"),
                        rs.getString("passenger_password")
                    ));
                }
            }
            return passengers;
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/flight_reservation_system";
        String user = "root";
        String password = "Pisey@!#$%^&*1234858483"; // Make sure this is secure

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            ArrayList<Passenger> passengers = Database.fetchPassengers(connection);

            if (!passengers.isEmpty()) {
                showReceipt(passengers.get(0)); // Show the first passenger's receipt
            } else {
                System.out.println("No passengers found in the database.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                "Failed to connect to database: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
