import java.util.ArrayList;
import java.util.List;

public class HotelChain {

    private String name;
    private List<Hotel> hotels;

    public HotelChain(String name) {
        this.name = name;
        this.hotels = new ArrayList<>();
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public Hotel findHotel(String hotelId) {

        for (Hotel hotel : hotels) {

            if (hotel.getHotelId().equalsIgnoreCase(hotelId)) {
                return hotel;
            }
        }

        return null;
    }

    public void displayHotels() {

        System.out.println("\n===== HOTELS =====");

        for (Hotel hotel : hotels) {

            System.out.println(
                    hotel.getHotelId()
                            + " | "
                            + hotel.getName()
                            + " | "
                            + hotel.getCity());
        }
    }
}