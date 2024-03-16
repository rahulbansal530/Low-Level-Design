import java.util.List;
import java.util.ArrayList;

public class ParkingLot {

    String name;
    Address address;
    List<ParkingFloor> parkingFloors;
    private static ParkingLot parkingLot;

    private ParkingLot(String name, Address address) {

        this.name = name;
        this.address = address;
        this.parkingFloors = new ArrayList<>();
    }
    
    public static synchronized ParkingLot getInstance(String name, Address address) {
        
        if (parkingLot == null) {
            parkingLot = new ParkingLot(name, address);
        }

        return parkingLot;
    }

    public void addFloor(ParkingFloor parkingFloor) {
        
        parkingFloors.add(parkingFloor);
    }

    public void removeFloor(ParkingFloor parkingFloor) {

        parkingFloors.add(parkingFloor);
    }

    public ParkingSlot getRelevantSlotForVehicleAndPark(Vehicle vehicle) {

        ParkingSlot parkingSlot = null;

        for (ParkingFloor parkingFloor : parkingFloors) {
            parkingSlot = parkingFloor.park(vehicle);

            if (parkingSlot != null) {
                return parkingSlot;
            }
        }

        return null;
    }

    public Ticket assignTicket(Vehicle vehicle) {
        
        ParkingSlot parkingSlot = getRelevantSlotForVehicleAndPark(vehicle);
        if (parkingSlot == null) {
            System.out.println("No Slot currently available");
        }

        System.out.println("Parking slot assigned with id - " + parkingSlot.getParkingSlotId() + " and type - " + parkingSlot.getParkingSlotType());

        return createTicketForSlot(vehicle, parkingSlot);
    }

    public Ticket createTicketForSlot(Vehicle vehicle, ParkingSlot parkingSlot) {
        
        return Ticket.createTicket(vehicle, parkingSlot);
    }

    public void payAndExit(Ticket ticket) {
        // unassign vehicle
        ticket.getParkingSlot().removeVehicle();
        // payment
        scanAndPay(ticket);
    }

    public double scanAndPay(Ticket ticket) {  
        long endTime = System.currentTimeMillis();
        System.out.println("end time - " + endTime);

        long totalTime = endTime - ticket.getStartTime();
        System.out.println("total time - " + totalTime + " milli seconds");

        double totalTimeInHours = Math.ceil((double) totalTime / (1000 * 60 * 60));
        
        return ticket.getParkingSlot().getParkingSlotType().getPricePerHour() * totalTimeInHours;
    }
}