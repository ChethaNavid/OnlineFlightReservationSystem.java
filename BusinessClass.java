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
    public String dinning() {
        return "Multi-course meals with a variety of choices";
    }

    @Override
    public String entertainment() {
        return "Large screens with a vast selection of movies, TV shows, music, and games.";
    }

    @Override
    public String seat() {
        return "Transforms into lay flat bed with extra legroom";
    }

    @Override
    public String baggage() {
        return "Increased baggage allowance";
    }

    @Override
    public String customerSupport() {
        return "More attentive and personalized service";
    }

    public String checkIn() {
        return "Priority check-in, security, and boarding";
    }

    public String lounge() {
        return "Exclusive lounge access";
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Check-In: " + checkIn());
        System.out.println("Lounge: " + lounge());
    }    

    public static int getTotalSeats() {
        return totalSeats;
    }

    public static int getBookCount() {
        return bookedCount;
    }
}
