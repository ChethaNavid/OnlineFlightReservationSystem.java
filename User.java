import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class User implements Authentication {
    protected String name;
    protected String sex;
    protected String phone;
    protected String email;
    protected String password;

    private static Map<String, String> usersDatabase = new HashMap<>(); //because fast key-value lookups.

    public User(String name, String sex, String phone, String email, String password) {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {}

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phone;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/flight_reservation_system", "root", "@Vid/1105.dev");
    }

    public static User currentUser = null;

    @Override
    public boolean login() {
        String passengerQuery= "SELECT * FROM Passenger WHERE passenger_email = ? AND passenger_password = ?";
        String staffQuery = "SELECT * FROM Staff WHERE staff_email = ? AND staff_password = ?";
        try (Connection conn = connect();
            PreparedStatement passengerStmt = conn.prepareStatement(passengerQuery);
            PreparedStatement staffStmt = conn.prepareStatement(staffQuery))
        {
            passengerStmt.setString(1, this.email);
            passengerStmt.setString(2, this.password);

            ResultSet passengerRs = passengerStmt.executeQuery();
            if (passengerRs.next()) { // ✅ Check if at least one record exists
                int passengerID = passengerRs.getInt("passenger_id");
                String userName = passengerRs.getString("passenger_name");
                String gender = passengerRs.getString("passenger_sex");
                String phoneNum = passengerRs.getString("passenger_phone");
                String passport = passengerRs.getString("passport_num");
                User.currentUser = new Passenger(passengerID, userName, gender, phoneNum, passport);
                JOptionPane.showMessageDialog(null, "Login Successful! Welcome " + userName, "Success", JOptionPane.INFORMATION_MESSAGE);
                return true; // ✅ Login successful
            }
            staffStmt.setString(1, this.email);
            staffStmt.setString(2, this.password);
            ResultSet staffRs = staffStmt.executeQuery();

            if(staffRs.next()) {
                String staffName = staffRs.getString("staff_name");
                User.currentUser = new Staff(staffName, this.email, this.password);
                JOptionPane.showMessageDialog(null, "Staff Login Successful! Welcome " + staffName, "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }

            JOptionPane.showMessageDialog(null, "Invalid Email or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return false; // ❌ Login failed (no matching user)

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean register() {
        return true;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", sex=" + sex + ", phone=" + phone + ", email=" + email + "]";
    }
}