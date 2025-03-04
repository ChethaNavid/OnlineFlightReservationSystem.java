
public class EmptyStringException extends IllegalArgumentException{
    public EmptyStringException(String message) {
        super(message);
    }

    public EmptyStringException(String[] inputString) {
        for(String string : inputString) {
            if(string.trim().isEmpty()) {
                throw new EmptyStringException("The input cannot be empty!");
            } 
        }
    }
}
