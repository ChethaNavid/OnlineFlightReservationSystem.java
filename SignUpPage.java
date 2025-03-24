import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class SignUpPage extends JPanel implements ActionListener {
    JPanel logoPanel;
    JPanel signUpPanel;
    JPanel buttonPanel;
    JPanel backButtonPanel;
    JTextField nameField;
    JTextField genderField;
    JTextField phoneNumberField;
    JTextField passportNumField;
    JTextField emailField;
    JPasswordField passField;
    JButton signUpButton;
    JButton backButton;
    JPanel parentPanel;  // Reference to parent panel
    CardLayout cardLayout;

    public SignUpPage(JPanel parentPanel, CardLayout cardLayout) {
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        
        this.setLayout(null);
        this.setBounds(50, 50, 500, 500);
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
        signUpPanel = new JPanel();
        signUpPanel.setBackground(Color.WHITE);
        signUpPanel.setBounds(90, 120, 310, 350);
        signUpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameField = new JTextField(); 
        nameField.setPreferredSize(new Dimension(300, 30));
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        genderField = new JTextField(); 
        genderField.setPreferredSize(new Dimension(300, 30));
        genderField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setFont(new Font("Arial", Font.BOLD, 14));
        phoneNumberField = new JTextField(); 
        phoneNumberField.setPreferredSize(new Dimension(300, 30));
        phoneNumberField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel passportNumLabel = new JLabel("Passport Number:");
        passportNumLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passportNumField = new JTextField(); 
        passportNumField.setPreferredSize(new Dimension(300, 30));
        passportNumField.setFont(new Font("Arial", Font.PLAIN, 14));

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
        buttonPanel.setBounds(90, 480, 310, 40);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(this);

        // Add components to signUpPanel
        signUpPanel.add(nameLabel);
        signUpPanel.add(nameField);
        signUpPanel.add(genderLabel);
        signUpPanel.add(genderField);
        signUpPanel.add(phoneNumberLabel);
        signUpPanel.add(phoneNumberField);
        signUpPanel.add(passportNumLabel);
        signUpPanel.add(passportNumField);
        signUpPanel.add(emailLabel);
        signUpPanel.add(emailField);
        signUpPanel.add(passLabel);
        signUpPanel.add(passField);

        buttonPanel.add(signUpButton);
        backButtonPanel.add(backButton);

        // Add panels to main panel
        this.add(logoPanel);
        this.add(signUpPanel);
        this.add(buttonPanel);
        this.add(backButtonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            cardLayout.show(parentPanel, "LandingPage");
        }
        
        if (e.getSource() == signUpButton) {
            String name = nameField.getText();
            String gender = genderField.getText();
            String phoneNum = phoneNumberField.getText();
            String passportNum = passportNumField.getText();
            String email = emailField.getText();
            String password = new String(passField.getPassword()); // Convert char[] to String

            if (name.isEmpty() || gender.isEmpty() || phoneNum.isEmpty() || passportNum.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
                return; // Stop execution if any field is empty
            }

            User passenger = new Passenger(name, gender, phoneNum, passportNum, email, password);
            boolean success = passenger.register();

            if(success) {
                Component[] components = parentPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof LandingPage) {
                        ((LandingPage) component).userLoggedIn();
                    }
                }
                JOptionPane.showMessageDialog(this, "Sign Up Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(parentPanel, "LandingPage");
            } else {
                JOptionPane.showMessageDialog(this, "Sign Up Failed. Try Again!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

