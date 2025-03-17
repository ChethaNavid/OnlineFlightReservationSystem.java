import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Passenger extends User{

    private static int totalPassenger = 0;
    private int passengerID;
    private String passportNum;

    public Passenger(String name, String sex, String phoneNum, String passportNum, String email, String password) {
        super(name, sex, phoneNum, email, password);
        this.passengerID = ++totalPassenger;
        this.passportNum = passportNum;
    }

    public Passenger(int passengerID, String name, String sex, String phoneNum, String passportNum, String email, String password) {
        super(name, sex, phoneNum, email, password);
        this.passengerID = passengerID;
        this.passportNum = passportNum;
    }

    public Passenger(String email, String password) {
        super(email, password);
    }

    public Passenger(String passportNum) {
        this.passportNum = passportNum;
    }

    public int getId() {
        return this.passengerID;
    }

    public String getPassportNum() {
        return this.passportNum;
    }

    public static int getTotalPassenger() {
        return totalPassenger;
    }
    
    @Override
    public boolean equals(Object compared){

        Passenger passengers = (Passenger)compared; // Convert compared into Passenger's object

        return this.passportNum.equals(passengers.passportNum);
    }

    @Override
    public String toString() {
        return "ID:" + String.valueOf(this.passengerID) + "," + "Name:" + this.name + "," + "Gender:" + this.sex + "," 
        + "Phone:" + this.phone + "," + "Email:" + this.email + "," + "Password:" + this.password + "," + "Passport:" + this.passportNum;
    }

    @Override
    public boolean login() {
        String url = "jdbc:mysql://localhost:3306/Flight_Reservation_System";
        String user = "root";  // Your MySQL username
        String pass = "@Vid/1105.dev";      // Your MySQL password

        String sql = "SELECT * FROM Passenger WHERE passenger_email = ? AND passenger_password = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
            PreparedStatement pstmt = conn.prepareStatement(sql)) 
        {
            pstmt.setString(1, this.email);
            pstmt.setString(2, this.password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) { // ✅ Check if at least one record exists
                String userName = rs.getString("passenger_name");
                JOptionPane.showMessageDialog(null, "Login Successful! Welcome " + userName, "Success", JOptionPane.INFORMATION_MESSAGE);
                return true; // ✅ Login successful
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Email or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                return false; // ❌ Login failed (no matching user)
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }

    @Override
    public boolean register() {
        String url = "jdbc:mysql://localhost:3306/Flight_Reservation_System";
        String user = "root";  // Your MySQL username
        String pass = "@Vid/1105.dev";      // Your MySQL password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure driver is loaded
    
            try (Connection conn = DriverManager.getConnection(url, user, pass);
                 PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Passenger (passenger_name, passenger_gender, passenger_phone, passenger_passport, passenger_email, passenger_password) VALUES (?, ?, ?, ?, ?, ?)"
                 )) {
    
                pstmt.setString(1, this.name);
                pstmt.setString(2, this.sex);
                pstmt.setString(3, this.phone);
                pstmt.setString(4, this.passportNum);
                pstmt.setString(5, this.email);
                pstmt.setString(6, this.password);
    
                int rowsInserted = pstmt.executeUpdate();
                return rowsInserted > 0;
    
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
