import java.util.Scanner;

public class Staff extends User {
    private static int totalStaff = 0;
    private int staffID;
    private String position;

    public Staff(String name, String sex, String position, String phone, String email, String password) {
        super(name, sex, phone, email, password);
        this.staffID = ++totalStaff;
        this.position = position;
    }

    public Staff(int staffID, String name, String sex, String position, String phone, String email, String password) {
        super(name, sex, phone, email, password);
        this.staffID = staffID;
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public int getID() {
        return staffID;
    }

    public static int getTotalStaff() {
        return totalStaff;
    }
    
    @Override
    public void register() {
        super.register();
    }

    @Override
    public void login() {
        super.login();
    }

    @Override
    public String toString() {
        return "ID:" + String.valueOf(this.staffID) + "," + "Name:" + this.name + "," + "Gender:" + this.sex + "," 
        + "Phone:" + this.phone + "," + "Email:" + this.email + "," + "Password:" + this.password + "," + "Position:" + this.position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Staff other = (Staff) obj;
        return staffId == other.staffId;
    }
}