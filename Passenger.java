
public class Passenger {
    int id;
    String name;
    String sex;
    String phoneNum;
    String passportNum;
    String email;
    int bookCount;
    int seatNumbers;

    int totalSeats = 60;
    Passenger[] seats = new Passenger[totalSeats]; 
    boolean[] seatIsReserved = new boolean[totalSeats]; // track the booking seat if seat != null return true
    int amount;

    Passenger(int id, String name, String sex, String phoneNum, String passportNum, String email) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.passportNum = passportNum;
        this.email = email;
    }
}
