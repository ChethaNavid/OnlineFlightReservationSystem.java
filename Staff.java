import java.util.Scanner;

public class Staff implements IStaff {
    private String stuffId;
    private String stuffName;
    private String passWord;
    private String position;

    public Staff(String id, String name, String password) {
        this.stuffId = id;
        this.stuffName = name;
        this.passWord = password;
    }

    public String getId() {
        return stuffId;
    }

    public String getName() {
        return stuffName;
    }

    public String getPosition(){
        return position;
    }

    @Override
    public boolean login() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter username: ");
            String enteredUsername = scanner.nextLine();
            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            if (this.stuffName.equals(enteredUsername) && this.passWord.equals(enteredPassword)) {
                System.out.println("Login successful! Welcome, " + stuffName);
                return true;
            } else {
                System.out.println("Login failed! Incorrect username or password.");
                return false;
            }
        }
    }
}