import java.util.ArrayList;

public class EconomyClass {
    protected int flightNum;
    private static int bookCount; // track the number of booked seat
    private static int totalSeats = 100;
    protected ArrayList<Passenger> passengers; // Stores passengers in their assigned seats
    protected Schedule schedules;
    protected ArrayList<Boolean>seatIsReserved; // track the booking seat if seat != null return true
    protected double amount;
    protected int seatNumber;

    public EconomyClass(double amount) {
        this.flightNum = flightNum;
        this.seatNumber = seatNumber;
        this.passengers = new ArrayList<Passenger>();
        this.seatIsReserved = new ArrayList<Boolean>(); 

        for(int i = 0; i < totalSeats; i++) {
            seatIsReserved.add(false);
        }
    }

    public String seat() {
        return "Limited recline and legroom.";
    }

    public String dinning() {
        return "Basic meal and drink, limited selection.";
    }
    
    public String entertainment() {
        return "Personal or shared screens with movies, music, and games.";
    }

    public String baggage() {
        return "Limited baggage allowance, additional fees for extra bags.";
    }

    public String customerSupport() {
        return "Standard assistance from flight attendants.";
    }

    public void display() {
        System.out.println("Seat: " + seat()); 
        System.out.println("Dinning: " + dinning());
        System.out.println("Entertianment: " + entertainment());
        System.out.println("Customer support: " + customerSupport());
        System.out.println("Baggage: " + baggage());
    }

    @Override
    public boolean equals(Object compared){
        EconomyClass comparedFlight = (EconomyClass)compared;

        return this.flightNum == comparedFlight.flightNum && this.seatNumber == comparedFlight.seatNumber;

    }

    @Override
    public String toString() {
        return "Flight number: " + this.flightNum + "\nYour seat number: " + this.seatNumber + "\nAmount: " + this.amount;
    }




}
