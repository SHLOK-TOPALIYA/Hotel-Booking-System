public class Refund {

    private String refundId;
    private double amount;
    private boolean successful;

    public Refund(String refundId, double amount) {
        this.refundId = refundId;
        this.amount = amount;
        this.successful = false;
    }

    public boolean processRefund() {
        successful = true;
        System.out.println("Refund successful: ₹" + amount);
        return successful;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getRefundId() {
        return refundId;
    }
}