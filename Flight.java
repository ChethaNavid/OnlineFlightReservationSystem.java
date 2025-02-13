import java.util.ArrayList;

public class Flight implements SystemManagement{
    private int flightNum;
    private int bookCount; // track the number of booked seat
    static int totalSeats = 60;
    static ArrayList<Passenger> passengers = new ArrayList<>(); // Stores passengers in their assigned seats
    static ArrayList<Boolean>seatIsReserved = new ArrayList<>(); // track the booking seat if seat != null return true
    private int amount;
    private int seatNumber;

    public Flight() {

    }

    public Flight(int flightNum, int seatNumber) {
        this.flightNum = flightNum;
        this.bookCount = 0;
        this.amount = 0;
        this.seatNumber = seatNumber;

        for(int i = 0; i < totalSeats; i++) {
            seatIsReserved.add(false);
        }
    }

    public int getSeatNumber() {
        return this.seatNumber;
    }

    public int getBookCount() {
        return this.bookCount;
    }

    // public double price() {
    //     if(this.seatNumber < 1 || this.seatNumber > 15) {
    //         System.out.println("Invalid seat number");
    //         return -1;
    //     }

    //     if(this.seatNumber >= 1 || this.seatNumber <= 15) 

        
    // }

    @Override
    public boolean equals(Object compared){
        Flight comparedFlight = (Flight)compared;

        return this.flightNum == comparedFlight.flightNum && this.seatNumber == comparedFlight.seatNumber;

    }

    @Override
    public void bookTicket() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void cancelTicket() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String toString() {
        return "Flight number: " + this.flightNum + "\nYour seat number: " + this.seatNumber + "\nAmount: " + this.amount;
    }




}
