
import java.util.Scanner;

public class Direction {
    private Scanner input = new Scanner(System.in);
    private String fromWhere;
    private String toWhere;
    private String durationTime;
    private String dateTime;
    private String airlineName;

    public Direction() {
        System.out.println("1. From Phnom Penh");
        System.out.println("2. From Siem Reap");
        System.out.print("Which location would you like to start?: ");
        int start = input.nextInt();
        System.out.println(" ");

        if (start == 1) {
            handlePhnomPenhRoutes();
        } else if (start == 2) {
            handleSiemReapRoutes();
        } else {
            System.out.println("Invalid option. Please restart and choose a valid location.");
        }
    }

    private void handlePhnomPenhRoutes() {
        System.out.println("1. Ho Chi Minh City (SGN)");
        System.out.println("2. Hanoi (HAN)");
        System.out.println("3. Da Nang (DAD)");
        System.out.print("Which location would you like to go?: ");
        int finish = input.nextInt();
        System.out.println(" ");

        if (finish == 1) {
            setFlightDetails("Phnom Penh", "Ho Chi Minh City (SGN)", "1h 10m", 
                              "10/01/2026 19:15", "Cambodia Angkor Airlines");
        } else if (finish == 2) {
            setFlightDetails("Phnom Penh", "Hanoi (HAN)", "2h 30m", 
                              "10/01/2026 20:00", "Vietnam Airlines");
        } else if (finish == 3) {
            setFlightDetails("Phnom Penh", "Da Nang (DAD)", "2h 0m", 
                              "10/01/2026 15:30", "Cambodia Angkor Airlines");
        } else {
            System.out.println("Invalid option. Please restart and choose a valid destination.");
        }
    }

    private void handleSiemReapRoutes() {
        System.out.println("1. Ho Chi Minh City (SGN)");
        System.out.println("2. Hanoi (HAN)");
        System.out.print("Which location would you like to go?: ");
        int finish = input.nextInt();
        System.out.println(" ");
        
        if (finish == 1) {
            setFlightDetails("Siem Reap", "Ho Chi Minh City (SGN)", "1h 20m", 
                              "10/01/2026 15:45", "Cambodia Angkor Airlines");
        } else if (finish == 2) {
            setFlightDetails("Siem Reap", "Hanoi (HAN)", "2h 40m", 
                              "10/01/2026 18:15", "Vietnam Airlines");
        } else {
            System.out.println("Invalid option. Please restart and choose a valid destination.");
        }
    }

    private void setFlightDetails(String from, String to, String duration, String dateTime, String airline) {
            this.fromWhere = from;
            this.toWhere = to;
            this.durationTime = duration;
            this.dateTime = dateTime;
            this.airlineName = airline;
    
            // Print flight details
            System.out.println("Flight Details:");
            System.out.println("From: " + fromWhere);
            System.out.println("To: " + toWhere);
            System.out.println("Duration: " + durationTime);
            System.out.println("Date & Time: " + dateTime);
        System.out.println("Airline: " + airlineName);
        System.out.println(" ");
    }

    public static void main(String[] args) {
       Direction Direction1 = new Direction();
    }
}
