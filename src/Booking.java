import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Booking {

    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELLED,
        PAYMENT_FAILED
    }

    private String bookingId;
    private Customer customer;
    private Hotel hotel;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Status status;
    private Payment payment;
    private Voucher voucher;

    public Booking(
            String bookingId,
            Customer customer,
            Hotel hotel,
            Room room,
            LocalDate checkIn,
            LocalDate checkOut) {

        this.bookingId = bookingId;
        this.customer = customer;
        this.hotel = hotel;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = Status.PENDING;
    }

    public double calculateTotal() {

        long nights = ChronoUnit.DAYS.between(
                checkIn,
                checkOut);

        return nights *
                room.getRoomType().getNightlyPrice();
    }

    public boolean confirmBooking(boolean paymentSuccess) {

        if (!room.isAvailable(checkIn, checkOut)) {

            System.out.println(
                    "Booking rejected: Room is not available.");

            return false;
        }

        double total = calculateTotal();

        payment = new Payment(
                "PAY-" + bookingId,
                total);

        boolean paid = payment.processPayment(paymentSuccess);

        if (!paid) {

            status = Status.PAYMENT_FAILED;

            System.out.println(
                    "Booking NOT confirmed because payment failed.");

            return false;
        }

        status = Status.CONFIRMED;

        room.addBooking(this);

        voucher = new Voucher(
                "V-" + bookingId,
                bookingId);

        System.out.println(
                "Booking confirmed successfully!");

        voucher.displayVoucher();

        return true;
    }

    public Refund cancelBooking(
            LocalDateTime cancellationTime) {

        if (status != Status.CONFIRMED) {

            System.out.println(
                    "Booking cannot be cancelled.");

            return null;
        }

        LocalDateTime checkInTime = checkIn.atStartOfDay();

        CancellationPolicy policy = new CancellationPolicy();

        double percentage = policy.getRefundPercentage(
                cancellationTime,
                checkInTime);

        double refundAmount = calculateTotal() * percentage;

        status = Status.CANCELLED;

        System.out.println(
                "Booking cancelled.");

        System.out.println(
                "Refund percentage: "
                        + (percentage * 100)
                        + "%");

        Refund refund = new Refund(
                "REF-" + bookingId,
                refundAmount);

        refund.processRefund();

        return refund;
    }

    public void displayBooking() {

        System.out.println("---------------------------");
        System.out.println("Booking ID : " + bookingId);
        System.out.println("Hotel      : " + hotel.getName());
        System.out.println("Room       : " + room.getRoomNumber());
        System.out.println("Room Type  : " + room.getRoomType());
        System.out.println("Check-in   : " + checkIn);
        System.out.println("Check-out  : " + checkOut);
        System.out.println("Amount     : ₹" + calculateTotal());
        System.out.println("Status     : " + status);
        System.out.println("---------------------------");
    }

    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public Status getStatus() {
        return status;
    }

    public Payment getPayment() {
        return payment;
    }

    public Voucher getVoucher() {
        return voucher;
    }
}