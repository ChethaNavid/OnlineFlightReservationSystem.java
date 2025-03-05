import java.io.File;

public class DeletingFile {
    public static void main(String[] args) {
        File file = new File("./Database/data.txt");

        if(file.delete()) {
            System.out.println("File deleted successfully.");
        } else {
            System.out.println("Failed to delete file.");
        }
    }
}
