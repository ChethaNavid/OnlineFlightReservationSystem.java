import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class LandingPage extends JPanel implements ActionListener {
    JButton signInButton;
    JButton signUpButton;
    JButton historyButton;
    JPanel buttonPanel;
    JPanel logoPanel;

    public LandingPage(JPanel parentPanel, CardLayout cardLayout) {
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
        
        signInButton = new JButton("Sign In");
        signUpButton = new JButton("Sign Up");
        historyButton = new JButton("History");

        buttonPanel.add(signInButton);
        buttonPanel.add(signUpButton);
        buttonPanel.add(historyButton);

        this.add(buttonPanel);
        this.add(logoPanel);

        signInButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "SignInPage");
        });
        signUpButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "SignUpPage");
        });
        historyButton.addActionListener(e -> {
            cardLayout.show(parentPanel, "HistoryPage");
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
