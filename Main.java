import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        ArrayList<User>users = new ArrayList<>();
        Schedule schedules = new Schedule();
        Scanner scanner = new Scanner(System.in);

        User passenger1 = new Passenger("Navid", "M", "061521512", "A18b100265", "navidchetha@gmail.com", "@Vid123");
        User staff1 = new Staff("Alice", "F", "Cashier", "085647514", "alice@gmail.com", "@alice123");
        User passenger2 = new Passenger("Navin", "M", "012587865", "A18b100152", "navinchetha@gmail.com", "@Vin123");

        users.add(passenger1);
        users.add(passenger2);
        users.add(staff1);

        System.out.println("Total passenger: " + Passenger.getTotalPassenger());
        System.out.println("Total staff: " + Staff.getTotalStaff());
        System.out.println(((Passenger)passenger1).getId());
        System.out.println(((Staff)staff1).getID());
        System.out.println(((Passenger)passenger2).getId());

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while(true) {
            System.out.println("Enter a date (dd-MM-yyyy): ");
            String input = scanner.nextLine();

            try {
                LocalDate date = LocalDate.parse(input, dateFormat);
                new DateException(date);
                String formattedDate = date.format(dateFormat);
                System.out.println("Valid date: " + formattedDate);
                break;
            } catch(DateException e){
                System.out.println(e.getMessage());
                continue;
            } catch(DateTimeParseException e) {
                System.out.println("Invalid format! Please enter the date in dd-MM-yyyy format.");
                continue; 
            }
        }

        try {
            System.out.println("Enter your name");
            String name = scanner.nextLine();
            System.out.println("Enter your nickname");
            String nickname = scanner.nextLine();
            String[] inpuStrings = {name, nickname};
            new EmptyStringException(inpuStrings);
        } catch(EmptyStringException e) {
            System.out.println(e.getMessage());
        }


        // try {
        //     System.out.println("Enter a valid number");
        //     int number = scanner.nextInt();
        //     new NumberOnlyException(number);
        // } catch(NumberOnlyException e) {
        //     System.out.println(e.getMessage());
        // }
        scanner.close();
        
        // EconomyClass economyClass = new EconomyClass(500);
        // System.out.println("Flying Economy Class, provide you with: ");
        // System.out.println(economyClass);
        // System.out.println("\n");
        // BusinessClass eClass = new BusinessClass(1000);
        // System.out.println("Flying Business Class, provide you with: ");
        // System.out.println(eClass);
        // System.out.println("\n");
        // System.out.println("Flying First Class, provide you with: ");
        // FirstClass firstClass = new FirstClass(1500);
        // System.out.println(firstClass);


    }
}
