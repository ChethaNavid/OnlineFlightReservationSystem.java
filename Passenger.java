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

    public Passenger(String name, String email, String password) {
        super(name, email, password);
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

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/flight_reservation_system", "root", "@Vid/1105.dev");
    }

    @Override
    public boolean login() {
        return super.login();
    }

    @Override
    public boolean register() {
        String sql = "INSERT INTO Passenger (passenger_name, passenger_sex, passenger_phone, passenger_email, passenger_password, passport_num) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, this.name);
            pstmt.setString(2, this.sex);
            pstmt.setString(3, this.phone);
            pstmt.setString(4, this.email);
            pstmt.setString(5, this.password);
            pstmt.setString(6, this.passportNum);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
         
    }
    
}
