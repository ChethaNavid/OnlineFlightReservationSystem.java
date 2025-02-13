
// --- Payment Class ---
public class Payment extends Reservation implements Payable, Displayable {
    private String paymentId;
    private String userId;
    private String reservationId;
    private double amount;
    private String paymentDate;
    private String paymentStatus;

    @Override
    public boolean equals(Object compared) {
        if (this == compared) return true;
        if (compared == null || getClass() != compared.getClass()) return false;
        Payment payment = (Payment) compared;
        return paymentId.equals(payment.paymentId);
    }

    @Override
    public String toString() {
        return "Payment ID: " + paymentId + 
               "\nUser ID: " + userId + 
               "\nReservation ID: " + reservationId + 
               "\nAmount: $" + amount + 
               "\nPayment Date: " + paymentDate + 
               "\nPayment Status: " + paymentStatus;
    }

    @Override
    public void processPayment() {
        if (paymentStatus.equalsIgnoreCase("Pending")) {
            paymentStatus = "Completed";
            System.out.println("Payment processed successfully.");
        } else {
            System.out.println("Payment is already " + paymentStatus + ".");
        }
    }

    @Override
    public void displayPayment() {
        System.out.println(this.toString());
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

    public void displayIfUserIdMatches(String inputUserId) {
        if (this.userId.equals(inputUserId)) {
            display();
        }
    }
}
