import java.util.ArrayList;

public class FirstClass extends BusinessClass {
    private static int totalSeats = 5; // Total seats in Business Class
    private static int bookedCount = 0; // Number of seats booked in Business Class

    // Constructor for BusinessClass, calling superclass constructor
    public FirstClass(double amount) {
        super(amount);
        this.passengers = new ArrayList<Passenger>();
        this.seatIsReserved = new ArrayList<Boolean>(); 

        for(int i = 0; i < totalSeats; i++) {
            seatIsReserved.add(false);
        }
    }


    @Override
    public String baggage() {
        return super.baggage();
    }

    @Override
    public String lounge() {
        return "Ultra-exclusive First Class lounges with à la carte dining, private suites, showers, spa services, and business facilities.";
    }

    @Override
    public String customerSupport() {
        return "Highly trained staff offering personalized attention throughout the flight.";
    }

    @Override
    public String checkIn() {
        return "Private check-in counters and expedited security screening.";
    }

    @Override
    public String dinning() {
        return "Gourmet multi-course meal craft by top chef, top-shelf wines, premium champagne, signature cocktails,";
    }

    @Override
    public String entertainment() {
        return "Extra-large, high-definition screens with a vast entertainment library";
    }

    @Override
    public String seat() {
       return "Fully enclosed suites with sliding doors for maximum privacy.";
    }

    public String specialRequest() {
        return "Personalized menus, celebration cakes, or other special arrangements";
    }

    @Override
    public String toString() {
        return super.toString() +
        "Special Request: " + specialRequest();
    }

    @Override
    public boolean equals(Object compared) {
        if(this == compared) { // Check if the compared object is the same instance
            return true;
        }

        if(!(compared instanceof FirstClass)) {
            return false;
        }

        FirstClass first = (FirstClass)compared;

        return this.amount == first.amount && totalSeats == first.totalSeats;
    }

    public static int getTotalSeats() {
        return totalSeats;
    }


    public static int getBookCount() {
        return bookedCount;
    }
}
