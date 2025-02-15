import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Passenger> passengers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // while(true) {
        //     System.out.println("Enter your phone number");
        //     String phoneNum = scanner.nextLine();

        //     if(phoneNum.isEmpty()) {
        //         break;
        //     }

        //     System.out.println("Enter passport number");
        //     String passNum = scanner.nextLine();

        //     Passenger passenger = new Passenger(passNum);

        //     if(!passengers.contains(passenger)) {
        //         passengers.add(passenger);
        //     } else {
        //         System.out.println("Passenger already exist in the list");
        //         break;
        //     }
            
        // }
        // scanner.close();
        EconomyClass economyClass = new EconomyClass(500);
        System.out.println("Flying Economy Class, provide you with: ");
        economyClass.display();
        System.out.println("\n");
        BusinessClass eClass = new BusinessClass(1000);
        System.out.println("Flying Business Class, provide you with: ");
        eClass.display();
        System.out.println("\n");
        System.out.println("Flying First Class, provide you with: ");
        FirstClass firstClass = new FirstClass(1500);
        firstClass.display();
        

    }
}
