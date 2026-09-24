import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String hotelId;
    private String name;
    private String city;
    private List<Room> rooms;

    public Hotel(
            String hotelId,
            String name,
            String city) {

        this.hotelId = hotelId;
        this.name = name;
        this.city = city;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public List<Room> getAvailableRooms(
            RoomType type,
            LocalDate checkIn,
            LocalDate checkOut) {

        List<Room> availableRooms = new ArrayList<>();

        for (Room room : rooms) {

            if (room.getRoomType() == type &&
                    room.isAvailable(checkIn, checkOut)) {

                availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    public Room findRoom(int roomNumber) {

        for (Room room : rooms) {

            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }

        return null;
    }

    public void displayRooms() {

        System.out.println("\nHotel: " + name);
        System.out.println("City : " + city);

        for (Room room : rooms) {

            System.out.println(
                    "Room " + room.getRoomNumber()
                            + " | "
                            + room.getRoomType()
                            + " | Capacity: "
                            + room.getRoomType().getCapacity()
                            + " | Price: ₹"
                            + room.getRoomType().getNightlyPrice());
        }
    }
}