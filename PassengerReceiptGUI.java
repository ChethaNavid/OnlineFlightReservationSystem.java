import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PassengerReceiptGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private Passenger passenger;
    private Schedule flight; // Added Flight Object

    public PassengerReceiptGUI(Passenger passenger, Schedule flight) {
        if (passenger == null || flight == null) {
            throw new IllegalArgumentException("Passenger and Flight cannot be null");
        }
        this.passenger = passenger;
        this.flight = flight;
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Passenger Receipt");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 600);
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
        detailsPanel.setLayout(new GridLayout(14, 2, 0, 0));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Passenger & Flight Details"));

        // Passenger Info
        detailsPanel.add(new JLabel("Passenger ID:"));
        detailsPanel.add(new JLabel(String.valueOf(passenger.getId())));

        detailsPanel.add(new JLabel("Name:"));
        detailsPanel.add(new JLabel(passenger.getName() != null ? passenger.getName() : "N/A"));

        detailsPanel.add(new JLabel("Gender:"));
        detailsPanel.add(new JLabel(passenger.getGender() != null ? passenger.getGender() : "N/A"));

        detailsPanel.add(new JLabel("Phone Number:"));
        detailsPanel.add(new JLabel(passenger.getPhone() != null ? passenger.getPhone() : "N/A"));

        detailsPanel.add(new JLabel("Passport Number:"));
        detailsPanel.add(new JLabel(passenger.getPassportNum() != null ? passenger.getPassportNum() : "N/A"));

        // Flight Info
        detailsPanel.add(new JLabel("Flight Number:"));
        detailsPanel.add(new JLabel(flight.getFlightNumber()));

        detailsPanel.add(new JLabel("Source:"));
        detailsPanel.add(new JLabel(flight.getSource()));

        detailsPanel.add(new JLabel("Destination:"));
        detailsPanel.add(new JLabel(flight.getDestination()));

        detailsPanel.add(new JLabel("Departure Time:"));
        detailsPanel.add(new JLabel(flight.getDepartureTime()));

        detailsPanel.add(new JLabel("Arrival Time:"));
        detailsPanel.add(new JLabel(flight.getArrivalTime()));

        detailsPanel.add(new JLabel("Class:"));
        detailsPanel.add(new JLabel(flight.getClassType()));

        return detailsPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> frame.dispose());

        footerPanel.add(closeButton);

        return footerPanel;
    }

    public static void showReceipt(Passenger passenger, Schedule flight) {
        SwingUtilities.invokeLater(() -> new PassengerReceiptGUI(passenger, flight));
    }
}

