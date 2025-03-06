import java.sql.Date;
import java.util.ArrayList;

public class Ticket {
    Passenger passengers;
    Payment payments;
    Schedule schedules;
    ClassType classT;
    private Date dateOfJourney; // YYYY-MM-DD
    private Date dateOfBuyingTicket;
    private int ticketId;
    
    private static int totalTickets = 0;
    private static ArrayList<Ticket> ticketList = new ArrayList<>(); // Store all tickets

    public Ticket(Passenger passengers, Payment payments, Schedule schedules, Date doj, /* String to, String from, */ Date dobt) {
        this.passengers = passengers;
        this.payments = payments;
        this.schedules = schedules;
        this.dateOfJourney = doj;
        this.dateOfBuyingTicket = dobt;

        this.ticketId = ++totalTickets;
        ticketList.add(this); // Add ticket to list
    }

    public Ticket() {}

    public int getPassengerId(Passenger passenger) { 
        return passenger.getId(); 
    }

    public String getPaymentId(Payment payments) { 
        return payments.getPaymentId(); 
    }

    public String getAirplaneCode(Schedule schedules) { 
        return schedules.getFlightNumber();
    }

    public Date getDateOfJourney() { 
        return dateOfJourney; 
    }

    public Date getDateOfBuyingTicket() { 
        return dateOfBuyingTicket; 
    }

    public String getAirlineName(Schedule schedules) { 
        return schedules.getAirlineName(); 
    }

    public int getTicketId() { 
        return ticketId; 
    }

    public static ArrayList<Ticket> getTicketList() { 
        return ticketList; 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }

    public void newTicket(){
        System.out.println("New ticket created successfully.");
    }

    public void deleteTicket(int ticketId){
        for (Ticket ticket : ticketList) {
            if (ticket.getTicketId() == ticketId) {
                ticketList.remove(ticket);
                totalTickets--;
                System.out.println("Ticket with ID " + ticketId + " deleted successfully.");
                return;
            }
        }
        System.out.println("Ticket with ID " + ticketId + " not found.");
    }    

    public static int getTotalTickets() {
        return totalTickets;
    }

    public String destination(Schedule schedule) {
        return schedule.getDestination();
    }

    @Override
    public String toString() {
        return "Ticket [" + "Ticket ID: " + getTicketId() + ", Airline Name: " + schedules.getAirlineName() +
        ", Flight Number: " + schedules.getFlightNumber() + 
        ", Source: " + schedules.getSource() + ", Destination: " + schedules.getDestination() + 
        ", Date of Journey: " + schedules.getDate() + ", Passenger ID: " + passengers.getId() +
        "Class: " + passengers.getClass() + ", Date of buying ticket: " + getDateOfBuyingTicket() +
        ", Payment ID: " + payments.getPaymentId() +
         "]";
    }

    
}