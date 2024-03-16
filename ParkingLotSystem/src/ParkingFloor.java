import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingFloor {
    
    int floorNumber;
    Map<ParkingSlotType, Map<String, ParkingSlot>>  parkingslots;

    public ParkingFloor(int floorNumber, Map<ParkingSlotType, Map<String, ParkingSlot>> parkingslots) {
        this.floorNumber = floorNumber;
        this.parkingslots = parkingslots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Map<ParkingSlotType, Map<String, ParkingSlot>> getParkingslots() {
        return parkingslots;
    }

    public void setParkingslots(Map<ParkingSlotType, Map<String, ParkingSlot>> parkingslots) {
        this.parkingslots = parkingslots;
    }

    public ParkingSlot park(Vehicle vehicle) {

        // pick slot
        ParkingSlot parkingSlot = pickSlot(vehicle.getVehicleCategory());
        
        if (parkingSlot == null) {
            return null;
        }

        // assign slot
        parkingSlot.addVehicle(vehicle);
        return parkingSlot;

    }

    public ParkingSlot pickSlot(VehicleCategory vehicleCategory) {

        ParkingSlotType parkingSlotType = getParkingSlotTypeFromVehicleCategory(vehicleCategory);

        // check which slot is avlbl and assign
        List<ParkingSlot> relevantSlots = new ArrayList<>(parkingslots.get(parkingSlotType).values());

        for (ParkingSlot parkingSlot : relevantSlots) {
            if(parkingSlot.isAvailable) {
                return parkingSlot;
            }
        }
        
        return null;
    }

    public ParkingSlotType getParkingSlotTypeFromVehicleCategory(VehicleCategory vehicleCategory) {
        ParkingSlotType parkingSlotType;

        if (vehicleCategory == VehicleCategory.BIKE || vehicleCategory == VehicleCategory.SCOOTY) {
            parkingSlotType = ParkingSlotType.TWO_WHEELER;
        } else if (vehicleCategory == VehicleCategory.HATCHBACK) {
            parkingSlotType = ParkingSlotType.SMALL_COMPACT;
        } else if (vehicleCategory == VehicleCategory.SEDAN || vehicleCategory == VehicleCategory.SUV) {
            parkingSlotType = ParkingSlotType.MEDIUM_COMPACT;
        } else {
            parkingSlotType = ParkingSlotType.LARGE_COMPACT;
        }

        return parkingSlotType;
    }


}
