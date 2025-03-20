import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StuffPage {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JTextField flightNumberField, airlineNameField, sourceField, destinationField, departureField, arrivalField, dateField;

    public StuffPage() {
        frame = new JFrame("Airline Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        model = new DefaultTableModel();
        table = new JTable(model);
        model.setColumnIdentifiers(new String[]{"Flight No", "Airline", "Source", "Destination", "Departure", "Arrival", "Date"});
        loadFlights();

        JPanel panel = new JPanel(new GridLayout(7, 2));

        panel.add(new JLabel("Flight No:"));
        flightNumberField = new JTextField();
        panel.add(flightNumberField);

        panel.add(new JLabel("Airline Name:"));
        airlineNameField = new JTextField();
        panel.add(airlineNameField);

        panel.add(new JLabel("Source:"));
        sourceField = new JTextField();
        panel.add(sourceField);

        panel.add(new JLabel("Destination:"));
        destinationField = new JTextField();
        panel.add(destinationField);

        panel.add(new JLabel("Departure Time (HH:MM:SS):"));
        departureField = new JTextField();
        panel.add(departureField);

        panel.add(new JLabel("Arrival Time (HH:MM:SS):"));
        arrivalField = new JTextField();
        panel.add(arrivalField);

        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        panel.add(dateField);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton removeButton = new JButton("Remove");
        JButton viewButton = new JButton("View All");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(viewButton);

        addButton.addActionListener(e -> addFlight());
        updateButton.addActionListener(e -> updateFlight());
        removeButton.addActionListener(e -> removeFlight());
        viewButton.addActionListener(e -> loadFlights());

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/flight_reservation_system", "root", "@Vid/1105.dev");
    }

    private void loadFlights() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Schedule")) {
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("flight_number"),
                    rs.getString("airline_name"),
                    rs.getString("source"),
                    rs.getString("destination"),
                    rs.getString("departure_time"),
                    rs.getString("arrival_time"),
                    rs.getDate("date")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addFlight() {
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Schedule (flight_number, airline_name, source, destination, departure_time, arrival_time, date) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, flightNumberField.getText());
            pstmt.setString(2, airlineNameField.getText());
            pstmt.setString(3, sourceField.getText());
            pstmt.setString(4, destinationField.getText());
            pstmt.setString(5, departureField.getText());
            pstmt.setString(6, arrivalField.getText());
            pstmt.setDate(7, Date.valueOf(dateField.getText()));
            pstmt.executeUpdate();
            loadFlights();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateFlight() {
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement("UPDATE Schedule SET airline_name=?, source=?, destination=?, departure_time=?, arrival_time=?, date=? WHERE flight_number=?")) {
            pstmt.setString(1, airlineNameField.getText());
            pstmt.setString(2, sourceField.getText());
            pstmt.setString(3, destinationField.getText());
            pstmt.setString(4, departureField.getText());
            pstmt.setString(5, arrivalField.getText());
            pstmt.setDate(6, Date.valueOf(dateField.getText()));
            pstmt.setString(7, flightNumberField.getText());
            pstmt.executeUpdate();
            loadFlights();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeFlight() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a flight to remove.");
            return;
        }

        String flightNumber = (String) model.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to remove Flight No: " + flightNumber + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Schedule WHERE flight_number = ?")) {
                pstmt.setString(1, flightNumber);
                pstmt.executeUpdate();
                model.removeRow(selectedRow);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}