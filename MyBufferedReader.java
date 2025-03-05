import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class MyBufferedReader {
    public static void main(String[] args) {
        ArrayList<User>users = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./Database/data.txt"));
            String line;
            while((line = reader.readLine()) != null) { // read till the end of the line (line = null : loop break)
                String[] parts = line.split(",");
                int id = (Integer.parseInt(parts[0].split(":")[1]));
                String name = parts[1].split(":")[1];
                String gender = parts[2].split(":")[1];
                String phone = parts[3].split(":")[1];
                String email = parts[4].split(":")[1];
                String password = parts[5].split(":")[1];

                if(line.contains("Passport")) {
                    String passportNum = parts[6].split(":")[1];
                    users.add(new Passenger(id, name, gender, phone, email, password, passportNum));
                } else if(line.contains("Position")) {
                    String position = parts[6].split(":")[1];
                    users.add(new Staff(id, name, gender, phone, email, password, position));
                }
            }
            reader.close();
        } catch(IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
        
        for(User user : users) {
            System.out.println(user);
            System.out.println("Name: " + user.getName());
        }

    }
}
