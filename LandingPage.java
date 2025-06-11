import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class LandingPage extends JPanel{
    JButton signInButton, signUpButton, logOutButton, historyButton, searchButton, viewButton;
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

        JLabel headerLabel = new JLabel("Search your flight");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);

        // 🔹 Form Panel
        formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setBounds(20, 180, 450, 250);
        formPanel.setLayout(new GridLayout(4, 1, 0, 10));

        from = placeholderField("Source");
        from.setPreferredSize(new Dimension(300, 30));
        from.setFont(new Font("Arial", Font.PLAIN, 14));
        from.setBackground(Color.WHITE);

        to = placeholderField("Destination");
        to.setPreferredSize(new Dimension(300, 30));
        to.setFont(new Font("Arial", Font.PLAIN, 14));
        to.setBackground(Color.WHITE);

        date = placeholderField("Date (yyyy-MM-dd)");
        date.setPreferredSize(new Dimension(300, 30));
        date.setFont(new Font("Arial", Font.PLAIN, 14));
        date.setBackground(Color.WHITE);

        classType = placeholderField("Class");
        classType.setPreferredSize(new Dimension(300, 30));
        classType.setFont(new Font("Arial", Font.PLAIN, 14));
        classType.setBackground(Color.WHITE);

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
        logOutButton = new JButton("Log out");
        logOutButton.setVisible(false);
        historyButton = new JButton("History");
        historyButton.setVisible(false);
        viewButton = new JButton("View All Flight");

        buttonPanel.add(signInButton);
        buttonPanel.add(signUpButton);
        buttonPanel.add(logOutButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(viewButton);

        this.add(buttonPanel);
        this.add(logoPanel);
        this.add(headerPanel);
        this.add(formPanel);
        this.add(searchButtonPanel);

        // signInButton.addActionListener(e -> {
        //     cardLayout.show(parentPanel, "SignInPage");
        // });

        signInButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(parentPanel, "SignInPage");
                
            }
            
        });
        signUpButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "SignUpPage");
        });
        logOutButton.addActionListener(e -> {
            User.currentUser = null;
            Component[] components = parentPanel.getComponents();
            for (Component component : components) {
                if (component instanceof LandingPage) {
                    ((LandingPage) component).userLoggedOut();
                }
            }
            JOptionPane.showMessageDialog(null, "Logging out");
            // Redirect to login screen
            cardLayout.show(parentPanel, "LandingPage");
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
    public void userLoggedIn() {
        signInButton.setVisible(false);
        signUpButton.setVisible(false);
        logOutButton.setVisible(true);
        historyButton.setVisible(true);

    }

    public void userLoggedOut() {
        signInButton.setVisible(true);
        signUpButton.setVisible(true);
        logOutButton.setVisible(false);
        historyButton.setVisible(false);
    }

    private static JTextField placeholderField(String placeholder) {
        JTextField textField = new JTextField(placeholder);
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.GRAY); // Placeholder text color

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK); // Change color when user types
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY); // Set back to placeholder color
                }
            }
        });

        return textField;
    }

}
