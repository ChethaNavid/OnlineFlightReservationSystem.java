import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.CardLayout;

public class MyFrame extends JFrame{

    MyFrame() {
        this.setTitle("Flight Reservation System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit out of the app
        this.setSize(500, 600);
        this.setLocationRelativeTo(null); // Centers the window

        // 🔹 Create a JPanel with CardLayout
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        SignUpPage signUp = new SignUpPage(mainPanel, cardLayout);
        LoginPage signIn = new LoginPage(mainPanel, cardLayout);
        HistoryPage history = new HistoryPage(mainPanel, cardLayout);
        FlightSearchResults search = new FlightSearchResults(mainPanel, cardLayout);
        LandingPage landingPage = new LandingPage(mainPanel, cardLayout, search);
        ViewAllFlight viewAllFlight = new ViewAllFlight(mainPanel, cardLayout);

        mainPanel.add(landingPage, "LandingPage");
        mainPanel.add(signUp, "SignUpPage");
        mainPanel.add(signIn, "SignInPage");
        mainPanel.add(history, "HistoryPage");
        mainPanel.add(search, "SearchResults");
        mainPanel.add(viewAllFlight, "DisplayAllFlight");
        
        this.add(mainPanel);

        cardLayout.show(mainPanel, "LandingPage");

        ImageIcon image = new ImageIcon("./assets/image.png"); // create image icon
        this.setIconImage(image.getImage()); // change icon frame
        this.getContentPane().setBackground(Color.WHITE); //change color of background
        
    }
}