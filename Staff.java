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

    public String getPosition(){
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
        // TODO Auto-generated method stub
        super.register();
    }

    @Override
    public String toString() {
        return "ID:" + String.valueOf(this.staffID) + "," + "Name:" + this.name + "," + "Gender:" + this.sex + "," 
        + "Phone:" + this.phone + "," + "Email:" + this.email + "," + "Password:" + this.password + "," + "Position:" + this.position;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public void login() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter email: ");
            String enteredEmail = scanner.nextLine();
            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            if (this.email.equals(enteredEmail) && this.password.equals(enteredPassword)) {
                System.out.println("Login successful! Welcome, " + this.name);
                
            } else {
                System.out.println("Login failed! Incorrect username or password.");
                
            }
        }
    }
}