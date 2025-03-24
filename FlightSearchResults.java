import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class FlightSearchResults extends JPanel {
    private JTable flightTable;
    private DefaultTableModel tableModel;
    private JButton bookButton, detailsButton, backButton;
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
        String[] columnNames = {"Flight ID", "Flight No", "Airline", "Source", "Destination", "Departure Time", "Arrival Time", "Date", "Class"};
        tableModel = new DefaultTableModel(columnNames, 0);
        flightTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(flightTable);
    
        // Buttons
        JPanel buttonPanel = new JPanel();
        bookButton = new JButton("Book Flight");
        detailsButton = new JButton("View Details");
        backButton = new JButton("Back");
        
        buttonPanel.add(backButton);
        buttonPanel.add(bookButton);
        buttonPanel.add(detailsButton);
    
        // Add components to Frame
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "LandingPage");
        });
    
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = flightTable.getSelectedRow();
                if (selectedRow != -1) {
                    String flightNo = tableModel.getValueAt(selectedRow, 0).toString();
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to book flight number " + flightNo + "?", "Confirm booking", JOptionPane.YES_NO_OPTION);
                    if(confirm == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "Booking confirmed for Flight " + flightNo);
                        Passenger currentPassenger = Passenger.getLoggedInPassenger();

                        if (currentPassenger == null) {
                            JOptionPane.showMessageDialog(null, "Sign Up or Sign In to book a flight", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method early
                        }

                        Schedule bookedFlight = Schedule.getBookedFlightForPassenger(currentPassenger, flightNo);
                        PassengerReceiptGUI.showReceipt(currentPassenger, bookedFlight); 
                        //cardLayout.show(parentPanel, "LandingPage");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a flight to book.");
                }
            }
        });

        detailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = flightTable.getSelectedRow();
                if (selectedRow != -1) {
                    String details = "Flight ID: " + tableModel.getValueAt(selectedRow, 0) + "\n" 
                            + "Flight No: " + tableModel.getValueAt(selectedRow, 1) + "\n"
                            + "Airline: " + tableModel.getValueAt(selectedRow, 2) + "\n"
                            + "Source: " + tableModel.getValueAt(selectedRow, 3) + "\n"
                            + "Destination: " + tableModel.getValueAt(selectedRow, 4) + "\n"
                            + "Departure Time: " + tableModel.getValueAt(selectedRow, 5) + "\n"
                            + "Arrival Time: " + tableModel.getValueAt(selectedRow, 6) + "\n"
                            + "Date: " + tableModel.getValueAt(selectedRow, 7) + "\n" 
                            + "Class: " + tableModel.getValueAt(selectedRow, 8);
                    JOptionPane.showMessageDialog(null, details, "Flight Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a flight to view details.");
                }
            }
        });
    }

    public void addFlightData(String flightNo, String airline, String departure, String arrival, String price, String date, String classType) {
        String[] flight = {flightNo, airline, departure, arrival, price, date, classType};
        allFlights.add(flight);
        tableModel.addRow(flight);  // 🔹 Ensure table updates immediately
    }

    public void updateFlightResults(String from, String to, String date, String flightClass) {
        tableModel.setRowCount(0); // Clear previous results

        ArrayList<Schedule> results = Schedule.searchFlights(from, to, LocalDate.parse(date), flightClass); // Fetch from DB

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No flights found for the given search.", "No Results", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Schedule flight : results) {
                tableModel.addRow(new Object[]{
                    flight.getFlightID(),
                    flight.getFlightNumber(),
                    flight.getAirlineName(),
                    flight.getSource(),
                    flight.getDestination(),
                    flight.getDepartureTime(),
                    flight.getArrivalTime(),
                    flight.getDate(),
                    flight.getClassType()
                });
            }
        }
    }

    
    
}

