import java.util.ArrayList;

public class EconomyClass extends ClassType {
    private static int bookCount; // track the number of booked seat
    private static int totalSeats = 100;
    private int flightNum;
    private int seatNumber;

    public EconomyClass(double amount) {
        this.amount = amount;
        this.passengers = new ArrayList<Passenger>();
        this.seatIsReserved = new ArrayList<Boolean>(); 

        for(int i = 0; i < totalSeats; i++) {
            seatIsReserved.add(false);
        }
    }

    @Override
    public String seat() {
        return "Limited recline and legroom.";
    }

    @Override
    public String dinning() {
        return "Basic meal and drink, limited selection.";
    }
    
    @Override
    public String entertainment() {
        return "Personal or shared screens with movies, music, and games.";
    }

    @Override
    public String baggage() {
        return "Limited baggage allowance, additional fees for extra bags.";
    }

    @Override
    public String customerSupport() {
        return "Standard assistance from flight attendants.";
    }

    @Override
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
