import java.time.LocalDate;

public class DateException extends IllegalArgumentException{
    public DateException(String message) {
        super(message); 
    }

    public DateException(LocalDate date) {
        if (date == null) {
            throw new DateException("Date input can't be null");
        }

        LocalDate today = LocalDate.now();
        if (date.isBefore(today)) {
            throw new DateException("The date cannot be in the past");
        }

        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        if(month < 1 || month > 12) {
            throw new DateException("Invalid month! Month must be between 1 and 12.");
        }

        boolean isValidDay = false;
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                isValidDay = day >= 1 && day <= 31;
                break;
            case 4: case 6: case 9: case 11:
                isValidDay = day >= 1 && day <= 30;
                break;
            case 2:
                // Check for leap year for February
                if ((date.isLeapYear() && day >= 1 && day <= 29) || (!date.isLeapYear() && day >= 1 && day <= 28)) {
                    isValidDay = true;
                }
                break;
        }

        if (!isValidDay) {
            throw new DateException("Invalid day! The day does not exist for the given month.");
        }
    }
}
