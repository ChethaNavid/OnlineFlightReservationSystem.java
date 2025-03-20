import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class LoginPage extends JPanel implements ActionListener {
    JPanel logoPanel;
    JPanel loginPanel;
    JPanel buttonPanel;
    JPanel backButtonPanel;
    JTextField emailField;
    JPasswordField passField;
    JButton loginButton;
    JButton backButton;
    JPanel parentPanel;
    CardLayout cardLayout;

    public LoginPage(JPanel parentPanel, CardLayout cardLayout) {
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        
        this.setLayout(null);
        this.setBounds(50, 50, 600, 400);
        this.setBackground(Color.WHITE);

        backButtonPanel = new JPanel();
        backButtonPanel.setBackground(Color.WHITE);
        backButtonPanel.setBounds(20, 10, 400, 40);
        backButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Back");
        backButton.addActionListener(this);

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

        // 🔹 Login Panel (Grid Layout for Proper Alignment)
        loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBounds(90, 140, 310, 120);
        loginPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); 

        // 🔹 Email Label and Field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, 30));
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));

        // 🔹 Password Label and Field
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passField = new JPasswordField(); 
        passField.setPreferredSize(new Dimension(300, 30));
        passField.setFont(new Font("Arial", Font.PLAIN, 14));

        // 🔹 Login Button
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(90, 270, 310, 40);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Sign In");
        loginButton.addActionListener(this);

        // Add components to loginPanel
        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);

        buttonPanel.add(loginButton);
        backButtonPanel.add(backButton);

        // Add panels to main panel
        this.add(backButtonPanel);
        this.add(logoPanel);
        this.add(loginPanel);
        this.add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {   
        if(e.getSource() == backButton) {
            cardLayout.show(parentPanel, "LandingPage");
        }

        if (e.getSource() == loginButton) {
            String email = emailField.getText();
            String password = new String(passField.getPassword()); // Convert char[] to String

            User users = new User(email, password);
            boolean success = users.login();

            if(success) {
                if(User.currentUser instanceof Passenger) {
                    cardLayout.show(parentPanel, "LandingPage");
                } else if(User.currentUser instanceof Staff) {
                    new StuffPage();
                }
                
            }
        }
    }
}
