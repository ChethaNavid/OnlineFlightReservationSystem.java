
public class Passenger extends User{
    static int totalPassenger = 0;
    private int id;
    private String name;
    private String sex;
    private String phoneNum;
    private String passportNum;
    private String email;

    public Passenger(String name, String sex, String phoneNum, String passportNum, String email) {
        this.id = ++totalPassenger;
        this.name = name;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.passportNum = passportNum;
        this.email = email;
    }

    public Passenger(String passportNum) {
        this.passportNum = passportNum;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNum;
    }

    public String getPassportNum() {
        return this.passportNum;
    }

    @Override
    public boolean equals(Object compared){

        Passenger passengers = (Passenger)compared; // Convert compared into Passenger's object

        return this.passportNum.equals(passengers.passportNum);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id) + ". " + this.name + " " + this.sex + " " + this.phoneNum + " " + this.passportNum + this.email;
    }
}
