// --- Payment Class ---
public class Payment extends Reservation implements Payable{
    private String paymentId;
    private String userId;
    private String reservationId;
    private double amount;
    private String paymentDate;
    private String paymentStatus;

    @Override
    public boolean equals(Object compared) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    public void processPayment() {
        // TODO Auto-generated method stub
        
    }

    public Payment(String paymentId, String userId, String reservationId,
                   double amount, String paymentDate, String paymentStatus) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.reservationId = reservationId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getUserId() {
        return userId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}
