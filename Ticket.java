public class Ticket {
    private String passengerId;
    private String paymentId;
    private String airplaneCode;//"AB123"
    private String dateOfJourney; //YYYY-MM-DD
    private int seatNum; //can justify plane Class
    private String destination ;
    private String source;
    private String dateOfBuyingTicket;
    private String airlineName;
    private int ticketId;

    private static int totalTickets = 0;

    public Ticket(String passId, String payment, String planeCode, String doj, int sn, String to, String from, String dobt, String airName){
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

    void newTicket(){
        System.out.println("New ticket created successfully. Ticket ID: " + ticketId);
    }

    void deleteTicket(){
        System.out.println("Ticket deleted successfully.");
    }

    static int getTotalTickets() {
        return totalTickets;
    }
}