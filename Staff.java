import java.util.Scanner;

public class Staff extends User {
    private static int totalStaff = 0;
    private int stuffId;
    private String position;

    public Staff(String name, String sex, String position, String phone, String email, String password) {
        super(name, sex, phone, email, password);
        this.stuffId = ++totalStaff;
        this.position = position;
    }

    public int getID() {
        return stuffId;
    }

    public String getPosition(){
        return position;
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
        // TODO Auto-generated method stub
        return super.toString();
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