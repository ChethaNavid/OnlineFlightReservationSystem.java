import java.util.Scanner;

public class Stuff implements IStuff {
    private String id;
    private String name;
    private String username;
    private String password;

    public Stuff(String id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean login() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter username: ");
            String enteredUsername = scanner.nextLine();
            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            if (this.username.equals(enteredUsername) && this.password.equals(enteredPassword)) {
                System.out.println("Login successful! Welcome, " + name);
                return true;
            } else {
                System.out.println("Login failed! Incorrect username or password.");
                return false;
            }
        }
    }
}