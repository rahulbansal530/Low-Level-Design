public class ParkingSlot {

    String parkingSlotId;
    ParkingSlotType parkingSlotType;
    boolean isAvailable;
    Vehicle vehicle;

    public ParkingSlot(String parkingSlotId, ParkingSlotType parkingSlotType) {
        
        this.parkingSlotId = parkingSlotId;
        this.parkingSlotType = parkingSlotType;
        this.isAvailable = true;
        this.vehicle = null;
    }

    public void addVehicle(Vehicle vehicle) {
        this.isAvailable = false;
        this.vehicle = vehicle;
    }

    public void removeVehicle() {
        this.isAvailable = true;
        this.vehicle = null;
    }

    public String getParkingSlotId() {
        return parkingSlotId;
    }

    public ParkingSlotType getParkingSlotType() {
        return parkingSlotType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setParkingSlotId(String parkingSlotId) {
        this.parkingSlotId = parkingSlotId;
    }

    public void setParkingSlotType(ParkingSlotType parkingSlotType) {
        this.parkingSlotType = parkingSlotType;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}
