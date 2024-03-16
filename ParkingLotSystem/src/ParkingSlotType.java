public enum ParkingSlotType {
    
    TWO_WHEELER(10), 
    SMALL_COMPACT(20), 
    MEDIUM_COMPACT(30), 
    LARGE_COMPACT(40);

    private final int pricePerHour;

    private ParkingSlotType(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public int getPricePerHour() {
        return this.pricePerHour;
    }
}
