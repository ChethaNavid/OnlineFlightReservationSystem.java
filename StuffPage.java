import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StuffPage {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JTextField flightNumberField, airlineNameField, sourceField, destinationField, departureField, arrivalField, dateField, priceField;

    public StuffPage() {
        frame = new JFrame("Airline Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        model = new DefaultTableModel();
        table = new JTable(model);
        model.setColumnIdentifiers(new String[]{"Flight No", "Airline", "Source", "Destination", "Departure", "Arrival", "Date", "Price"});
        loadFlights();

        JPanel panel = new JPanel(new GridLayout(9, 2));

        // Create a panel to hold the labels with padding
        JPanel labelPanel = new JPanel(new GridLayout(1, 1));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        labelPanel.setOpaque(false); // make sure the label background is transparent
        labelPanel.add(new JLabel("Flight No:"));
        panel.add(labelPanel);
        flightNumberField = new JTextField();
        panel.add(flightNumberField);

        labelPanel = new JPanel(new GridLayout(1, 1));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        labelPanel.setOpaque(false); 
        labelPanel.add(new JLabel("Airline Name:"));
        panel.add(labelPanel);
        airlineNameField = new JTextField();
        panel.add(airlineNameField);

        labelPanel = new JPanel(new GridLayout(1, 1));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        labelPanel.setOpaque(false); 
        labelPanel.add(new JLabel("Source:"));
        panel.add(labelPanel);
        sourceField = new JTextField();
        panel.add(sourceField);

        labelPanel = new JPanel(new GridLayout(1, 1));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        labelPanel.setOpaque(false); 
        labelPanel.add(new JLabel("Destination:"));
        panel.add(labelPanel);
        destinationField = new JTextField();
        panel.add(destinationField);

        labelPanel = new JPanel(new GridLayout(1, 1));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        labelPanel.setOpaque(false); 
        labelPanel.add(new JLabel("Departure Time (HH:MM:SS):"));
        panel.add(labelPanel);
        departureField = new JTextField();
        panel.add(departureField);

        labelPanel = new JPanel(new GridLayout(1, 1));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        labelPanel.setOpaque(false); 
        labelPanel.add(new JLabel("Arrival Time (HH:MM:SS):"));
        panel.add(labelPanel);
        arrivalField = new JTextField();
        panel.add(arrivalField);

        labelPanel = new JPanel(new GridLayout(1, 1));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        labelPanel.setOpaque(false); 
        labelPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        panel.add(labelPanel);
        dateField = new JTextField();
        panel.add(dateField);

        labelPanel = new JPanel(new GridLayout(1, 1));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        labelPanel.setOpaque(false); 
        labelPanel.add(new JLabel("Price:"));
        panel.add(labelPanel);
        priceField = new JTextField();
        panel.add(priceField);


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
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "1234");
    }

    private void testConnection() {
        try (Connection conn = connect()) {
            if (conn != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Failed to connect to database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    private void loadFlights() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM airline_info")) {
            model.setRowCount(0); // Clear table before adding new data
    
            System.out.println("Loading flights from database..."); // Debugging log
    
            while (rs.next()) {
                Object[] rowData = {
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getDouble(8)
                };
    
                System.out.println("Row: " + java.util.Arrays.toString(rowData)); // Print retrieved row
    
                model.addRow(rowData);
            }
    
            System.out.println("Data loading completed."); // Debugging log
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    private void addFlight() {
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement("INSERT INTO airline_info VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, airlineNameField.getText());
            pstmt.setString(2, sourceField.getText());
            pstmt.setString(3, destinationField.getText());
            pstmt.setString(4, departureField.getText());
            pstmt.setString(5, arrivalField.getText());
            pstmt.setString(6, dateField.getText());
            pstmt.setDouble(7, Double.parseDouble(priceField.getText()));
            pstmt.executeUpdate();
            loadFlights();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateFlight() {
    // Ensure all fields are filled before updating
    if (flightNumberField.getText().isEmpty() || airlineNameField.getText().isEmpty() || sourceField.getText().isEmpty() || 
        destinationField.getText().isEmpty() || departureField.getText().isEmpty() || arrivalField.getText().isEmpty() || 
        dateField.getText().isEmpty() || priceField.getText().isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Please fill all fields.");
        return;
    }

    // Validate that priceField is not empty
    String priceText = priceField.getText();
    if (priceText.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Price cannot be empty.");
        return;
    }

    try {
        // Parse the price
        double price = Double.parseDouble(priceText);

        // Update query using `id` instead of `flightNumber`
        try (Connection conn = connect(); 
             PreparedStatement pstmt = conn.prepareStatement("UPDATE airline_info SET airlineName=?, source=?, destination=?, departureTime=?, arrivalTime=?, date=?, price=? WHERE id=?")) {

            pstmt.setString(1, airlineNameField.getText());
            pstmt.setString(2, sourceField.getText());
            pstmt.setString(3, destinationField.getText());
            pstmt.setString(4, departureField.getText());
            pstmt.setString(5, arrivalField.getText());
            pstmt.setString(6, dateField.getText());
            pstmt.setDouble(7, price);
            
            // Use the 'id' to identify which flight to update
            pstmt.setInt(8, Integer.parseInt(flightNumberField.getText())); // 'id' from flightNumberField
            
            pstmt.executeUpdate();
            loadFlights();  // Reload the flights table after the update
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Invalid price value. Please enter a valid number.");
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
    
        int flightNumber = (int) model.getValueAt(selectedRow, 0); // Get flight number from table
        System.out.println("Attempting to remove Flight No: " + flightNumber); // Debugging
    
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to remove Flight No: " + flightNumber + "?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);
    
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM airline_info WHERE id = ?")) {
    
                pstmt.setInt(1, flightNumber);
                System.out.println("SQL Query: DELETE FROM airline_info WHERE flightNumber = " + flightNumber); // Debugging
    
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    model.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(frame, "Flight removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "No flight found with that number.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error removing flight.");
            }
        }
    }    
}
