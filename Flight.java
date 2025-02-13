import java.util.ArrayList;

public class Flight implements SystemManagement{
    private int flightNum;
    private int bookCount; // track the number of booked seat
    private int totalSeats = 60;
    private ArrayList<Passenger> passengers; // Stores passengers in their assigned seats
    private Schedule schedules;
    private ArrayList<Boolean>seatIsReserved; // track the booking seat if seat != null return true
    private double amount;
    private int seatNumber;

    public Flight() {}

    public Flight(int flightNum, int seatNumber) {
        this.flightNum = flightNum;
        this.seatNumber = seatNumber;
        this.passengers = new ArrayList<Passenger>();
        this.seatIsReserved = new ArrayList<Boolean>(); 

        for(int i = 0; i < totalSeats; i++) {
            seatIsReserved.add(false);
        }
    }
    
    public int getFlightNumber() {
        return this.flightNum;
    }

    public int getSeatNumber() {
        return this.seatNumber;
    }

    public int getBookCount() {
        return this.bookCount;
    }

    public void setAmount(double amount) {
        
    }

    public double getAmount() {
        return this.amount;
    }

    @Override
    public boolean equals(Object compared){
        Flight comparedFlight = (Flight)compared;

        return this.flightNum == comparedFlight.flightNum && this.seatNumber == comparedFlight.seatNumber;

    }

    @Override
    public void bookSeat() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void cancelSeat() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String toString() {
        return "Flight number: " + this.flightNum + "\nYour seat number: " + this.seatNumber + "\nAmount: " + this.amount;
    }




}
