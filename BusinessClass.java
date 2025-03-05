import java.util.ArrayList;

public class BusinessClass extends EconomyClass {
    private static int totalSeats = 20; // Total seats in Business Class
    private static int bookedCount = 0; // Number of seats booked in Business Class

    // Constructor for BusinessClass, calling superclass constructor
    public BusinessClass(double amount) {
        super(amount);
        this.passengers = new ArrayList<Passenger>();
        this.seatIsReserved = new ArrayList<Boolean>(); 

        for(int i = 0; i < totalSeats; i++) {
            seatIsReserved.add(false);
        }
    }

    @Override
    protected String dinning() {
        return "Multi-course meals with a variety of choices";
    }

    @Override
    protected String entertainment() {
        return "Large screens with a vast selection of movies, TV shows, music, and games.";
    }

    @Override
    protected String seat() {
        return "Transforms into lay flat bed with extra legroom";
    }

    @Override
    protected String baggage() {
        return "Increased baggage allowance";
    }

    @Override
    protected String customerSupport() {
        return "More attentive and personalized service";
    }

    protected String checkIn() {
        return "Priority check-in, security, and boarding";
    }

    protected String lounge() {
        return "Exclusive lounge access";
    }

    @Override
    public String toString() {
        return super.toString() +
        "Check-In: " + checkIn() + "\n" +
        "Lounge: " + lounge() + "\n";
    }   

    @Override
    public boolean equals(Object compared) {
        if(this == compared) { // Check if the compared object is the same instance
            return true;
        }

        if(!(compared instanceof BusinessClass)) {
            return false;
        }

        BusinessClass business = (BusinessClass)compared;

        return this.amount == business.amount && totalSeats == business.totalSeats;
    }   

    public static int getTotalSeats() {
        return totalSeats;
    }

    public static int getBookCount() {
        return bookedCount;
    }
}
