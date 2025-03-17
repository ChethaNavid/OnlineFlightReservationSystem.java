import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class HistoryPage extends JPanel implements ActionListener {
    JPanel logoPanel;
    JPanel buttonPanel;
    JPanel backButtonPanel;
    JButton backButton;
    JPanel parentPanel;  // Reference to parent panel
    CardLayout cardLayout;

    public HistoryPage(JPanel parentPanel, CardLayout cardLayout) {
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

        

        JLabel historyLabel = new JLabel("History Page", JLabel.CENTER);
        historyLabel.setBounds(40, 120, 400, 60);
        historyLabel.setFont(new Font("Arial", Font.BOLD, 20));

        backButtonPanel.add(backButton);

        // Add panels to main panel
        this.add(logoPanel);
        this.add(historyLabel);
        this.add(backButtonPanel);
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(40, 120, 400, 300);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(buttonPanel);
        this.add(backButtonPanel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            cardLayout.show(parentPanel, "LandingPage");
        }
    }

}

