import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewAllFlight extends JPanel{
    private JTable flightTable;
    private DefaultTableModel tableModel;
    private JButton backButton;
    private JPanel parentPanel;
    private CardLayout cardLayout;
    
    public ViewAllFlight(JPanel parentPanel, CardLayout cardLayout) {
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        //allFlights = new ArrayList<>();
    
        // Create Table
        String[] columnNames = {"Flight ID", "Flight No", "Airline", "Source", "Destination", "Departure Time", "Arrival Time", "Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        flightTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(flightTable);

        viewAllFlights();
    
        // Buttons
        JPanel buttonPanel = new JPanel();
        backButton = new JButton("Back");
        
        buttonPanel.add(backButton);

        // Add components to Frame
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> cardLayout.show(parentPanel, "LandingPage"));
    }

    private void viewAllFlights() {
        tableModel.setRowCount(0); // Clear previous data

        ArrayList<Schedule> schedules = Schedule.getAllFlights(); // Fetch all flights

        for (Schedule flight : schedules) {
            tableModel.addRow(new Object[]{
                flight.getFlightID(),
                flight.getFlightNumber(),
                flight.getAirlineName(),
                flight.getSource(),
                flight.getDestination(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getDate()
            });
        }
    }
}
