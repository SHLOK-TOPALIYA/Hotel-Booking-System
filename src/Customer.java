import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String customerId;
    private String name;
    private String email;
    private List<Booking> bookings;

    public Customer(
            String customerId,
            String name,
            String email) {

        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void displayBookings() {

        System.out.println("\n===== MY BOOKINGS =====");

        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Booking booking : bookings) {
            booking.displayBooking();
        }
    }
}