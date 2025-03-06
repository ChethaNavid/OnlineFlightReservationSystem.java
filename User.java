import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

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

    public User() {}

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phone;
    }

    @Override
    public void login() {
        if (usersDatabase.containsKey(email) && usersDatabase.get(email).equals(password)) {
            System.out.println("Login successful for: " + name);
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    @Override
    public void register() {
        if (isValidEmail(email) && isValidPassword(password)) {
            usersDatabase.put(email, password);
            System.out.println("User registered successfully: " + name);
        } else {
            System.out.println("Invalid email or password format.");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean isValidPassword(String password) {
        // Password must be at least 8 characters long
        return password.length() >= 8;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", sex=" + sex + ", phone=" + phone + ", email=" + email + "]";
    }
}