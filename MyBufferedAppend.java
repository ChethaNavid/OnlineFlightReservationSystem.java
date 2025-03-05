import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyBufferedAppend {
    public static void main(String[] args) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./Database/data.txt", true));
            writer.write("Hello! This data is append using Buffered Append\n");
            writer.close();
            System.out.println("Successfully appended data to the file.");
        } catch(IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
        
    }
}
