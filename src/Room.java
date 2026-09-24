import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room {

    private int roomNumber;
    private RoomType roomType;
    private List<Booking> bookings;

    public Room(int roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.bookings = new ArrayList<>();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public boolean isAvailable(
            LocalDate checkIn,
            LocalDate checkOut) {

        for (Booking booking : bookings) {

            if (booking.getStatus() == Booking.Status.CANCELLED) {
                continue;
            }

            LocalDate existingCheckIn = booking.getCheckIn();

            LocalDate existingCheckOut = booking.getCheckOut();

            boolean overlaps = checkIn.isBefore(existingCheckOut)
                    && checkOut.isAfter(existingCheckIn);

            if (overlaps) {
                return false;
            }
        }

        return true;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
}