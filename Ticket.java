import java.util.ArrayList;

public class Ticket {
    private String passengerId;
    private String paymentId;
    private String airplaneCode;
    private String dateOfJourney; // YYYY-MM-DD
    private int seatNum;
    private String destination;
    private String source;
    private String dateOfBuyingTicket;
    private String airlineName;
    private int ticketId;
    
    private static int totalTickets = 0;
    private static ArrayList<Ticket> ticketList = new ArrayList<>(); // Store all tickets

    public Ticket(String passId, String payment, String planeCode, String doj, int sn, String to, String from, String dobt, String airName) {
        this.passengerId = passId;
        this.paymentId = payment;
        this.airplaneCode = planeCode;
        this.dateOfJourney = doj;
        this.seatNum = sn;
        this.destination = to;
        this.source = from;
        this.dateOfBuyingTicket = dobt;
        this.airlineName = airName;

        this.ticketId = ++totalTickets;
        ticketList.add(this); // Add ticket to list
    }

    public String getPassengerId() { 
        return passengerId; 
    }

    public String getPaymentId() { 
        return paymentId; 
    }

    public String getAirplaneCode() { 
        return airplaneCode; 
    }

    public String getDateOfJourney() { 
        return dateOfJourney; 
    }

    public int getSeatNum() { 
        return seatNum; 
    }

    public String getDestination() { 
        return destination; 
    }

    public String getSource() { 
        return source; 
    }

    public String getDateOfBuyingTicket() { 
        return dateOfBuyingTicket; 
    }

    public String getAirlineName() { 
        return airlineName; 
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
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ticket other = (Ticket) obj;
        return ticketId == other.ticketId;
    }

    public void newTicket(){
        System.out.println("New ticket created successfully.");
    }

    public void deleteTicket(){
        System.out.println("Ticket deleted successfully.");
    }

    public static int getTotalTickets() {
        return totalTickets;
    }

    @Override
    public String toString() {
        return "Ticket [passengerId=" + passengerId + ", airplaneCode=" + airplaneCode + ", dateOfJourney="
                + dateOfJourney + ", seatNum=" + seatNum + ", destination=" + destination + ", source=" + source
                + ", dateOfBuyingTicket=" + dateOfBuyingTicket + ", airlineName=" + airlineName + ", ticketId="
                + ticketId + "]";
    }

}