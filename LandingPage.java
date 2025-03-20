import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class LandingPage extends JPanel implements ActionListener {
    JButton signInButton, signUpButton, historyButton, searchButton, viewButton;
    JPanel buttonPanel, logoPanel, headerPanel, formPanel, searchButtonPanel;
    JTextField from, to, date, classType;
    private FlightSearchResults searchResultsPanel; 

    public LandingPage(JPanel parentPanel, CardLayout cardLayout, FlightSearchResults searchResults) {
        this.searchResultsPanel = searchResults; 

        this.setLayout(null);
        this.setBounds(50, 50, 600, 500);
        this.setBackground(Color.WHITE);

        buttonPanel = new JPanel();
        buttonPanel.setBounds(20, 10, 450, 40);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);

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

        // 🔹 Header Panel
        headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBounds(40, 120, 400, 60);
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel headerLabel = new JLabel("Book your flight");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(headerLabel);

        // 🔹 Form Panel
        formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setBounds(20, 180, 450, 250);
        formPanel.setLayout(new GridLayout(4, 1, 0, 10));

        from = new JTextField();
        from.setPreferredSize(new Dimension(300, 30));
        from.setFont(new Font("Arial", Font.PLAIN, 14));
        from.setBackground(Color.LIGHT_GRAY);
        from.setForeground(Color.GRAY);
        from.setText("From");

        to = new JTextField();
        to.setPreferredSize(new Dimension(300, 30));
        to.setFont(new Font("Arial", Font.PLAIN, 14));
        to.setBackground(Color.LIGHT_GRAY);
        to.setForeground(Color.GRAY);
        to.setText("To");

        date = new JTextField();
        date.setPreferredSize(new Dimension(300, 30));
        date.setFont(new Font("Arial", Font.PLAIN, 14));
        date.setBackground(Color.LIGHT_GRAY);
        date.setForeground(Color.GRAY);
        date.setText("Date (dd/MM/yyyy)");

        classType = new JTextField();
        classType.setPreferredSize(new Dimension(300, 30));
        classType.setFont(new Font("Arial", Font.PLAIN, 14));
        classType.setBackground(Color.LIGHT_GRAY);
        classType.setForeground(Color.GRAY);
        classType.setText("Class");

        formPanel.add(from);
        formPanel.add(to);
        formPanel.add(date);
        formPanel.add(classType);

        searchButtonPanel = new JPanel();
        searchButtonPanel.setBounds(20, 440, 450, 40);
        searchButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchButtonPanel.setBackground(Color.WHITE);
        searchButton = new JButton("Search");
        searchButtonPanel.add(searchButton);
        
        signInButton = new JButton("Sign In");
        signUpButton = new JButton("Sign Up");
        historyButton = new JButton("History");
        viewButton = new JButton("View All Flight");

        buttonPanel.add(signInButton);
        buttonPanel.add(signUpButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(viewButton);

        this.add(buttonPanel);
        this.add(logoPanel);
        this.add(headerPanel);
        this.add(formPanel);
        this.add(searchButtonPanel);

        signInButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "SignInPage");
        });
        signUpButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "SignUpPage");
        });
        historyButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "HistoryPage");
        });
        viewButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "DisplayAllFlight");
        });
        searchButton.addActionListener(e -> {
            String fromLocation = from.getText();
            String toLocation = to.getText();
            String travelDate = date.getText();
            String flightClass = classType.getText();
        
            System.out.println("🔎 Button clicked! Searching flights...");
            System.out.println("From: " + fromLocation + ", To: " + toLocation + ", Date: " + travelDate + ", Class: " + flightClass);
        
            // Call updateFlightResults
            searchResultsPanel.updateFlightResults(fromLocation, toLocation, travelDate, flightClass);
        
            // Switch to SearchResults page
            cardLayout.show(parentPanel, "SearchResults");
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
