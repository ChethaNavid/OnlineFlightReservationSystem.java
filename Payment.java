public class Payment {
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

    public void processPayment() {
        try {
            if (paymentStatus.equalsIgnoreCase("Pending")) {
                paymentStatus = "Completed";
                System.out.println("Payment processed successfully.");
            } else {
                throw new IllegalStateException("Payment is already " + paymentStatus + ".");
            }
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Payment(String paymentId, String userId, String reservationId,
                   double amount, String paymentDate, String paymentStatus) {
        if (paymentId == null || userId == null || reservationId == null || paymentDate == null || paymentStatus == null) {
            throw new IllegalArgumentException("Payment details cannot be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        
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
        try {
            if (inputUserId == null) {
                throw new IllegalArgumentException("User ID cannot be null.");
            }
            if (this.userId.equals(inputUserId)) {
                System.out.println(this);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
