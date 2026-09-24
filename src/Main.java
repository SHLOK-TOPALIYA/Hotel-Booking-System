import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    static HotelChain chain;
    static Customer customer;

    public static void main(String[] args) {

        setupSystem();

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n==============================");
            System.out.println("     HOTEL BOOKING SYSTEM");
            System.out.println("==============================");

            System.out.println("1. View Hotels");
            System.out.println("2. View Room Types");
            System.out.println("3. View Available Rooms");
            System.out.println("4. Book a Room");
            System.out.println("5. View My Bookings");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Run Required Test Cases");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    viewHotels();
                    break;

                case 2:
                    viewRoomTypes();
                    break;

                case 3:
                    viewAvailableRooms(scanner);
                    break;

                case 4:
                    bookRoom(scanner);
                    break;

                case 5:
                    customer.displayBookings();
                    break;

                case 6:
                    cancelBooking(scanner);
                    break;

                case 7:
                    runTestCases();
                    break;

                case 8:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ==============================
    // SYSTEM SETUP
    // ==============================

    static void setupSystem() {

        chain = new HotelChain("DreamStay Hotels");

        Hotel ahmedabad = new Hotel(
                "H001",
                "DreamStay Ahmedabad",
                "Ahmedabad");

        Hotel mumbai = new Hotel(
                "H002",
                "DreamStay Mumbai",
                "Mumbai");

        ahmedabad.addRoom(
                new Room(101, RoomType.STANDARD));

        ahmedabad.addRoom(
                new Room(102, RoomType.DELUXE));

        ahmedabad.addRoom(
                new Room(103, RoomType.SUITE));

        ahmedabad.addRoom(
                new Room(104, RoomType.STANDARD));

        mumbai.addRoom(
                new Room(201, RoomType.STANDARD));

        mumbai.addRoom(
                new Room(202, RoomType.DELUXE));

        mumbai.addRoom(
                new Room(203, RoomType.SUITE));

        chain.addHotel(ahmedabad);
        chain.addHotel(mumbai);

        customer = new Customer(
                "C001",
                "Test Customer",
                "test@example.com");
    }

    // ==============================
    // VIEW HOTELS
    // ==============================

    static void viewHotels() {

        chain.displayHotels();
    }

    // ==============================
    // VIEW ROOM TYPES
    // ==============================

    static void viewRoomTypes() {

        System.out.println("\n===== ROOM TYPES =====");

        for (RoomType type : RoomType.values()) {

            System.out.println(
                    type
                            + " | Capacity: "
                            + type.getCapacity()
                            + " | Price per night: ₹"
                            + type.getNightlyPrice());
        }
    }

    // ==============================
    // VIEW AVAILABLE ROOMS
    // ==============================

    static void viewAvailableRooms(Scanner scanner) {

        System.out.print("Enter hotel ID: ");
        String hotelId = scanner.next();

        Hotel hotel = chain.findHotel(hotelId);

        if (hotel == null) {

            System.out.println("Hotel not found.");
            return;
        }

        System.out.print(
                "Enter room type (STANDARD/DELUXE/SUITE): ");

        RoomType type;

        try {

            type = RoomType.valueOf(
                    scanner.next().toUpperCase());

        } catch (IllegalArgumentException e) {

            System.out.println("Invalid room type.");
            return;
        }

        System.out.print(
                "Enter check-in date (YYYY-MM-DD): ");

        LocalDate checkIn;

        try {

            checkIn = LocalDate.parse(scanner.next());

        } catch (Exception e) {

            System.out.println("Invalid check-in date.");
            return;
        }

        System.out.print(
                "Enter check-out date (YYYY-MM-DD): ");

        LocalDate checkOut;

        try {

            checkOut = LocalDate.parse(scanner.next());

        } catch (Exception e) {

            System.out.println("Invalid check-out date.");
            return;
        }

        if (!checkIn.isBefore(checkOut)) {

            System.out.println("Invalid date range.");
            return;
        }

        List<Room> rooms = hotel.getAvailableRooms(
                type,
                checkIn,
                checkOut);

        System.out.println("\n===== AVAILABLE ROOMS =====");

        if (rooms.isEmpty()) {

            System.out.println("No rooms available.");

        } else {

            for (Room room : rooms) {

                System.out.println(
                        "Room "
                                + room.getRoomNumber()
                                + " | "
                                + room.getRoomType()
                                + " | Capacity: "
                                + room.getRoomType().getCapacity()
                                + " | Price: ₹"
                                + room.getRoomType().getNightlyPrice());
            }
        }
    }

    // ==============================
    // BOOK ROOM
    // ==============================

    static void bookRoom(Scanner scanner) {

        System.out.print("Enter hotel ID: ");
        String hotelId = scanner.next();

        Hotel hotel = chain.findHotel(hotelId);

        if (hotel == null) {

            System.out.println("Hotel not found.");
            return;
        }

        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();

        Room room = hotel.findRoom(roomNumber);

        if (room == null) {

            System.out.println("Invalid room number.");
            return;
        }

        System.out.print(
                "Enter check-in date (YYYY-MM-DD): ");

        LocalDate checkIn;

        try {

            checkIn = LocalDate.parse(scanner.next());

        } catch (Exception e) {

            System.out.println("Invalid check-in date.");
            return;
        }

        System.out.print(
                "Enter check-out date (YYYY-MM-DD): ");

        LocalDate checkOut;

        try {

            checkOut = LocalDate.parse(scanner.next());

        } catch (Exception e) {

            System.out.println("Invalid check-out date.");
            return;
        }

        if (!checkIn.isBefore(checkOut)) {

            System.out.println("Invalid date range.");
            return;
        }

        String bookingId = "B" + System.currentTimeMillis();

        Booking booking = new Booking(
                bookingId,
                customer,
                hotel,
                room,
                checkIn,
                checkOut);

        System.out.print(
                "Should payment succeed? (true/false): ");

        boolean paymentSuccess = scanner.nextBoolean();

        boolean confirmed = booking.confirmBooking(
                paymentSuccess);

        if (confirmed) {

            customer.addBooking(booking);

            System.out.println(
                    "Booking ID: "
                            + booking.getBookingId());
        }
    }

    // ==============================
    // CANCEL BOOKING
    // ==============================

    static void cancelBooking(Scanner scanner) {

        System.out.print(
                "Enter booking ID: ");

        String id = scanner.next();

        for (Booking booking : customer.getBookings()) {

            if (booking.getBookingId()
                    .equals(id)) {

                System.out.print(
                        "Enter cancellation date/time "
                                + "(YYYY-MM-DDTHH:MM): ");

                try {

                    LocalDateTime cancellationTime = LocalDateTime.parse(
                            scanner.next());

                    booking.cancelBooking(
                            cancellationTime);

                } catch (Exception e) {

                    System.out.println(
                            "Invalid date/time format.");
                }

                return;
            }
        }

        System.out.println(
                "Booking not found.");
    }

    // ==============================
    // RUN ALL TEST CASES
    // ==============================

    static void runTestCases() {

        System.out.println(
                "\n======================================");

        System.out.println(
                "       REQUIRED TEST CASES");

        System.out.println(
                "======================================");

        testTC01();
        testTC02();
        testTC03();
        testTC04();
        testTC05();
        testTC06();
        testTC07();
    }

    // ==============================
    // TC01 - SUCCESSFUL BOOKING
    // ==============================

    static void testTC01() {

        System.out.println(
                "\nTC01 - Successful Booking");

        Hotel hotel = chain.findHotel("H001");

        Room room = hotel.findRoom(101);

        LocalDate checkIn = LocalDate.of(2030, 1, 10);

        LocalDate checkOut = LocalDate.of(2030, 1, 12);

        Booking booking = new Booking(
                "TC01-B",
                customer,
                hotel,
                room,
                checkIn,
                checkOut);

        boolean result = booking.confirmBooking(true);

        if (result
                && booking.getStatus() == Booking.Status.CONFIRMED
                && booking.getVoucher() != null) {

            System.out.println("TC01 PASS");

        } else {

            System.out.println("TC01 FAIL");
        }
    }

    // ==============================
    // TC02 - OVERLAPPING BOOKING
    // ==============================

    static void testTC02() {

        System.out.println(
                "\nTC02 - Overlapping Booking Rejected");

        Hotel hotel = chain.findHotel("H001");

        Room room = hotel.findRoom(101);

        LocalDate checkIn = LocalDate.of(2030, 1, 11);

        LocalDate checkOut = LocalDate.of(2030, 1, 13);

        Booking booking = new Booking(
                "TC02-B",
                customer,
                hotel,
                room,
                checkIn,
                checkOut);

        boolean result = booking.confirmBooking(true);

        if (!result
                && booking.getStatus() != Booking.Status.CONFIRMED) {

            System.out.println("TC02 PASS");

        } else {

            System.out.println("TC02 FAIL");
        }
    }

    // ==============================
    // TC03 - PAYMENT FAILURE
    // ==============================

    static void testTC03() {

        System.out.println(
                "\nTC03 - Payment Failure");

        Hotel hotel = chain.findHotel("H001");

        Room room = hotel.findRoom(102);

        LocalDate checkIn = LocalDate.of(2030, 2, 10);

        LocalDate checkOut = LocalDate.of(2030, 2, 12);

        Booking booking = new Booking(
                "TC03-B",
                customer,
                hotel,
                room,
                checkIn,
                checkOut);

        boolean result = booking.confirmBooking(false);

        if (!result
                && booking.getStatus() == Booking.Status.PAYMENT_FAILED
                && booking.getVoucher() == null) {

            System.out.println("TC03 PASS");

        } else {

            System.out.println("TC03 FAIL");
        }
    }

    // ==============================
    // TC04 - FULL REFUND
    // ==============================

    static void testTC04() {

        System.out.println(
                "\nTC04 - Cancellation Full Refund");

        Hotel hotel = chain.findHotel("H001");

        Room room = hotel.findRoom(103);

        LocalDate checkIn = LocalDate.of(2030, 3, 10);

        LocalDate checkOut = LocalDate.of(2030, 3, 12);

        Booking booking = new Booking(
                "TC04-B",
                customer,
                hotel,
                room,
                checkIn,
                checkOut);

        boolean result = booking.confirmBooking(true);

        if (!result) {

            System.out.println("TC04 FAIL");
            return;
        }

        LocalDateTime cancellationTime = LocalDateTime.of(
                2030,
                3,
                1,
                10,
                0);

        Refund refund = booking.cancelBooking(
                cancellationTime);

        if (booking.getStatus() == Booking.Status.CANCELLED
                && refund != null
                && refund.getAmount() == booking.calculateTotal()) {

            System.out.println("TC04 PASS");

        } else {

            System.out.println("TC04 FAIL");
        }
    }

    // ==============================
    // TC05 - PARTIAL / NO REFUND
    // ==============================

    static void testTC05() {

        System.out.println(
                "\nTC05 - Cancellation Partial/No Refund");

        Hotel hotel = chain.findHotel("H002");

        Room room = hotel.findRoom(201);

        LocalDate checkIn = LocalDate.of(2030, 4, 10);

        LocalDate checkOut = LocalDate.of(2030, 4, 12);

        Booking booking = new Booking(
                "TC05-B",
                customer,
                hotel,
                room,
                checkIn,
                checkOut);

        boolean result = booking.confirmBooking(true);

        if (!result) {

            System.out.println("TC05 FAIL");
            return;
        }

        LocalDateTime cancellationTime = LocalDateTime.of(
                2030,
                4,
                9,
                10,
                0);

        Refund refund = booking.cancelBooking(
                cancellationTime);

        if (refund != null
                && refund.getAmount() < booking.calculateTotal()) {

            System.out.println("TC05 PASS");

        } else {

            System.out.println("TC05 FAIL");
        }
    }

    // ==============================
    // TC06 - NON-OVERLAPPING DATES
    // ==============================

    static void testTC06() {

        System.out.println(
                "\nTC06 - Non-Overlapping Dates");

        Hotel hotel = chain.findHotel("H002");

        Room room = hotel.findRoom(202);

        LocalDate firstCheckIn = LocalDate.of(2030, 5, 10);

        LocalDate firstCheckOut = LocalDate.of(2030, 5, 12);

        Booking firstBooking = new Booking(
                "TC06-B1",
                customer,
                hotel,
                room,
                firstCheckIn,
                firstCheckOut);

        boolean firstResult = firstBooking.confirmBooking(true);

        LocalDate secondCheckIn = LocalDate.of(2030, 5, 12);

        LocalDate secondCheckOut = LocalDate.of(2030, 5, 14);

        Booking secondBooking = new Booking(
                "TC06-B2",
                customer,
                hotel,
                room,
                secondCheckIn,
                secondCheckOut);

        boolean secondResult = secondBooking.confirmBooking(true);

        if (firstResult && secondResult) {

            System.out.println("TC06 PASS");

        } else {

            System.out.println("TC06 FAIL");
        }
    }

    // ==============================
    // TC07 - INVALID ROOM
    // ==============================

    static void testTC07() {

        System.out.println(
                "\nTC07 - Invalid Room");

        Hotel hotel = chain.findHotel("H001");

        Room room = hotel.findRoom(999);

        if (room == null) {

            System.out.println("TC07 PASS");

        } else {

            System.out.println("TC07 FAIL");
        }
    }
}