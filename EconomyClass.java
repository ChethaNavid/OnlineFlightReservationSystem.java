import java.util.ArrayList;

public class EconomyClass extends ClassType {
    private static int bookCount; // track the number of booked seat
    private static int totalSeats = 100;

    public EconomyClass(double amount) {
        this.amount = amount;
        this.passengers = new ArrayList<Passenger>();
        this.seatIsReserved = new ArrayList<Boolean>(); 

        for(int i = 0; i < totalSeats; i++) {
            seatIsReserved.add(false);
        }
    }

    @Override
    protected String seat() {
        return "Limited recline and legroom.";
    }

    @Override
    protected String dinning() {
        return "Basic meal and drink, limited selection.";
    }
    
    @Override
    protected String entertainment() {
        return "Personal or shared screens with movies, music, and games.";
    }

    @Override
    protected String baggage() {
        return "Limited baggage allowance, additional fees for extra bags.";
    }

    @Override
    protected String customerSupport() {
        return "Standard assistance from flight attendants.";
    }

    @Override
    public String toString() {
        return "Seat: " + seat() + "\n" +
        "Dinning: " + dinning() + "\n" +
        "Entertianment: " + entertainment() + "\n" +
        "Customer support: " + customerSupport() + "\n" +
        "Baggage: " + baggage() + "\n";
    }

    @Override
    public boolean equals(Object compared){
        if(this == compared) {
            return true;
        }

        if(!(compared instanceof EconomyClass)) {
            return false;
        }
        EconomyClass economy = (EconomyClass)compared;

        return this.amount == economy.amount && totalSeats == economy.totalSeats;
    }

}
