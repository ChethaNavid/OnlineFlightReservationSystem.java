import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyBufferWriter {
    public static void main(String[] args) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./Database/data.txt"));

            User passenger1 = new Passenger("Navid", "M", "061521512", "A18b100265", "navidchetha@gmail.com", "@Vid123");
            User staff1 = new Staff("Alice", "F", "Cashier", "085647514", "alice@gmail.com", "@alice123");
            User passenger2 = new Passenger("Navin", "M", "012587865", "A18b100152", "navinchetha@gmail.com", "@Vin123");

            User[] users = {passenger1, staff1, passenger2};

            for(User user : users) {
                writer.write(user + "\n");
            }
            System.out.println("Data wrote to file successfully.");
            writer.close();
        } catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }
}
