public class Passenger extends User{

    static int totalPassenger = 0;
    private int passengerID;
    private String passportNum;

    public Passenger(String name, String sex, String phoneNum, String passportNum, String email, String password) {
        super(name, sex, phoneNum, email, password);
        this.passengerID = ++totalPassenger;
        this.passportNum = passportNum;
    }

    public Passenger(String passportNum) {
        this.passportNum = passportNum;
    }

    public int getId() {
        return this.passengerID;
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
        return String.valueOf(this.passengerID) + ". " + this.name + " " + this.sex + " " + this.phone + " " + this.passportNum + this.email;
    }

    @Override
    public void login() {
        // TODO Auto-generated method stub
        super.login();
    }

    @Override
    public void register() {
        // TODO Auto-generated method stub
        super.register();
    }
}
