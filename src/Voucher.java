public class Voucher {

    private String voucherId;
    private String bookingId;

    public Voucher(String voucherId, String bookingId) {
        this.voucherId = voucherId;
        this.bookingId = bookingId;
    }

    public void displayVoucher() {
        System.out.println("----- BOOKING VOUCHER -----");
        System.out.println("Voucher ID : " + voucherId);
        System.out.println("Booking ID : " + bookingId);
        System.out.println("---------------------------");
    }

    public String getVoucherId() {
        return voucherId;
    }
}