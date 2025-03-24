import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewAllFlight extends JPanel{
    private JTable flightTable;
    private DefaultTableModel tableModel;
    private JButton backButton, bookButton, detailButton;
    private JPanel parentPanel;
    private CardLayout cardLayout;
    
    public ViewAllFlight(JPanel parentPanel, CardLayout cardLayout) {
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        //allFlights = new ArrayList<>();
    
        // Create Table
        String[] columnNames = {"Flight ID", "Flight No", "Airline", "Source", "Destination", "Departure Time", "Arrival Time", "Date", "Class"};
        tableModel = new DefaultTableModel(columnNames, 0);
        flightTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(flightTable);

        viewAllFlights();
    
        // Buttons
        JPanel buttonPanel = new JPanel();
        backButton = new JButton("Back");
        bookButton = new JButton("Book Flight");
        detailButton = new JButton("View Detail");

        
        buttonPanel.add(backButton);
        buttonPanel.add(bookButton);
        buttonPanel.add(detailButton);

        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = flightTable.getSelectedRow();
                if (selectedRow != -1) {
                    String flightNo = tableModel.getValueAt(selectedRow, 0).toString();
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to book flight number " + flightNo + "?", "Confirm booking", JOptionPane.YES_NO_OPTION);
                    if(confirm == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "Booking confirmed for Flight " + flightNo);
                        //cardLayout.show(parentPanel, "LandingPage");
                        //int loggedInPassengerID = Passenger.getId();
                        Passenger currentPassenger = Passenger.getLoggedInPassenger();

                        if (currentPassenger == null) {
                            JOptionPane.showMessageDialog(null, "Sign Up or Sign In to book a flight", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method early
                        }

                        Schedule bookedFlight = Schedule.getBookedFlightForPassenger(currentPassenger, flightNo);
                        PassengerReceiptGUI.showReceipt(currentPassenger, bookedFlight); // Show receipt
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a flight to book.");
                }
            }
        });

        detailButton.addActionListener(new ActionListener() {
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
                flight.getDate(),
                flight.getClassType()
            });
        }
    }

}
