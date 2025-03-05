import java.util.ArrayList;

public abstract class ClassType {
    protected ArrayList<Passenger> passengers; // Stores passengers in their assigned seats
    protected Schedule schedules;
    protected ArrayList<Boolean>seatIsReserved; // track the booking seat if seat != null return true
    protected double amount;
    protected int seatNumber;

    protected abstract String seat(); // If we don't use abstract method, we have to implement the method here
    protected abstract String dinning();
    protected abstract String entertainment();
    protected abstract String baggage();
    protected abstract String customerSupport();
    
}
