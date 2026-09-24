public class Payment {

    private String paymentId;
    private double amount;
    private boolean successful;

    public Payment(String paymentId, double amount) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.successful = false;
    }

    public boolean processPayment(boolean shouldSucceed) {

        if (shouldSucceed) {
            successful = true;
            System.out.println("Payment successful: ₹" + amount);
        } else {
            successful = false;
            System.out.println("Payment failed.");
        }

        return successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentId() {
        return paymentId;
    }
}