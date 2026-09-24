public enum RoomType {

    STANDARD(2, 3000.0),
    DELUXE(4, 5000.0),
    SUITE(6, 8000.0);

    private final int capacity;
    private final double nightlyPrice;

    RoomType(int capacity, double nightlyPrice) {
        this.capacity = capacity;
        this.nightlyPrice = nightlyPrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getNightlyPrice() {
        return nightlyPrice;
    }
}