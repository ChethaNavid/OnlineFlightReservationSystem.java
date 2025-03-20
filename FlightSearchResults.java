import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FlightSearchResults extends JPanel {
    private JTable flightTable;
    private DefaultTableModel tableModel;
    private JButton bookButton, detailsButton;
    private JPanel parentPanel;
    private CardLayout cardLayout;

    private ArrayList<String[]> allFlights;

    public FlightSearchResults(JPanel parentPanel, CardLayout cardLayout) {
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        allFlights = new ArrayList<>();
    
        // Create Table
        String[] columnNames = {"Flight No", "Airline", "Departure", "Arrival", "Price", "Date", "Class"};
        tableModel = new DefaultTableModel(columnNames, 0);
        flightTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(flightTable);
    
        // Buttons
        JPanel buttonPanel = new JPanel();
        bookButton = new JButton("Book Flight");
        detailsButton = new JButton("View Details");
        
        buttonPanel.add(bookButton);
        buttonPanel.add(detailsButton);
    
        // Add components to Frame
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    
        // 🔹 Sample Data (Ensure it's added correctly)
        addFlightData("FL123", "Airways A", "New York", "London", "$500", "18/03/2025", "Economy");
        addFlightData("FL456", "Airways B", "Paris", "Tokyo", "$750", "19/03/2025", "Business");
        addFlightData("FL789", "Airways C", "Berlin", "Dubai", "$600", "18/03/2025", "First Class");
    
        // 🔹 Debugging
        System.out.println("Flights added:");
        for (String[] flight : allFlights) {
            System.out.println(String.join(", ", flight));
        }
    

        // Button Actions
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = flightTable.getSelectedRow();
                if (selectedRow != -1) {
                    String flightNo = tableModel.getValueAt(selectedRow, 0).toString();
                    JOptionPane.showMessageDialog(null, "Booking confirmed for Flight " + flightNo);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a flight to book.");
                }
            }
        });

        detailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = flightTable.getSelectedRow();
                if (selectedRow != -1) {
                    String details = "Flight No: " + tableModel.getValueAt(selectedRow, 0) + "\n"
                            + "Airline: " + tableModel.getValueAt(selectedRow, 1) + "\n"
                            + "Departure: " + tableModel.getValueAt(selectedRow, 2) + "\n"
                            + "Arrival: " + tableModel.getValueAt(selectedRow, 3) + "\n"
                            + "Price: " + tableModel.getValueAt(selectedRow, 4);
                    JOptionPane.showMessageDialog(null, details, "Flight Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a flight to view details.");
                }
            }
        });
    }

    // 🔹 Method to add flight data (stores in list)
    // Updated method to include date and classType
    public void addFlightData(String flightNo, String airline, String departure, String arrival, String price, String date, String classType) {
        allFlights.add(new String[]{flightNo, airline, departure, arrival, price, date, classType});
    }


    public void updateFlightResults(String from, String to, String date, String flightClass) {
        tableModel.setRowCount(0); // Clear previous results
        boolean foundMatch = false;
    
        System.out.println("🔍 Searching for flights...");
        System.out.println("From: " + from + ", To: " + to + ", Date: " + date + ", Class: " + flightClass);
    
        for (String[] flight : allFlights) {
            boolean matchesFromTo = flight[2].equalsIgnoreCase(from) && flight[3].equalsIgnoreCase(to);
            boolean matchesDate = date.isEmpty() || flight[5].equals(date);
            boolean matchesClass = flightClass.isEmpty() || flight[6].equalsIgnoreCase(flightClass);
    
            if (matchesFromTo && matchesDate && matchesClass) {
                tableModel.addRow(flight);
                foundMatch = true;
                System.out.println("✅ Found matching flight: " + String.join(", ", flight));
            }
        }
    
        if (!foundMatch) {
            JOptionPane.showMessageDialog(null, "No flights found for the given search.", "No Results", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("❌ No matching flights found.");
        }
    }
    
    
}

