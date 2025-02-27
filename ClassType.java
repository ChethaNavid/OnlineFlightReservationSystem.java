import java.util.ArrayList;

public abstract class ClassType {
    protected ArrayList<Passenger> passengers; // Stores passengers in their assigned seats
    protected Schedule schedules;
    protected ArrayList<Boolean>seatIsReserved; // track the booking seat if seat != null return true
    protected double amount;

    public abstract String seat();
    public abstract String dinning();
    public abstract String entertainment();
    public abstract String baggage();
    public abstract String customerSupport();
    public abstract void display();
    
}
