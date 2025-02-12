public class Ticket extends Reservation{
    String passengerId;
    String paymentId;
    String airplaneCode;//"AB123"
    String dateOfJourney; //YYYY-MM-DD
    int seatNum; //can justify plane Class
    String destination ;
    String source;
    String dateOfBuyingTicket;
    String airlineName;
    String ticketId;//

    static int totalTickets = 0;

    Ticket(String passId, String payment, String planeCode, String doj, int sn, String to, String from, String dobt, String airName){
        passengerId = passId;
        paymentId = payment;
        airplaneCode = planeCode;
        dateOfJourney = doj;
        seatNum = sn;
        destination = to;
        source = from;
        dateOfBuyingTicket = dobt;
        airlineName = airName;
        totalTickets++;
    }

    public void newTicket(){
        System.out.println("New ticket created successfully.");
    }

    public void deleteTicket(){
        System.out.println("Ticket deleted successfully.");
    }

    static int getTotalTickets() {
        return totalTickets;
    }
}