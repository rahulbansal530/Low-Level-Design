import java.util.HashMap;
import java.util.Map;

public class ParkingLotApp {

    public static void main(String[] args) throws InterruptedException {

        ParkingLot parkingLot = ParkingLot.getInstance("Sunset Vista Parking",new Address("142038", "Moga", "Punjab", "abc"));

        Map<ParkingSlotType, Map<String,ParkingSlot>>  parkingSlots = new HashMap<>();

        Map<String,ParkingSlot> twoWheelerSlots = new HashMap<>();
        twoWheelerSlots.put("T1",new ParkingSlot("T1",ParkingSlotType.TWO_WHEELER));
        twoWheelerSlots.put("T2",new ParkingSlot("T2",ParkingSlotType.TWO_WHEELER));
        parkingSlots.put(ParkingSlotType.TWO_WHEELER, twoWheelerSlots);

        Map<String,ParkingSlot> smallCompactSlots = new HashMap<>();
        smallCompactSlots.put("S1",new ParkingSlot("S1",ParkingSlotType.SMALL_COMPACT));
        smallCompactSlots.put("S2",new ParkingSlot("S2",ParkingSlotType.SMALL_COMPACT));
        parkingSlots.put(ParkingSlotType.SMALL_COMPACT, smallCompactSlots);

        Map<String,ParkingSlot> mediumCompactSlots = new HashMap<>();
        mediumCompactSlots.put("M1",new ParkingSlot("M1",ParkingSlotType.MEDIUM_COMPACT));
        mediumCompactSlots.put("M2",new ParkingSlot("M2",ParkingSlotType.MEDIUM_COMPACT));
        parkingSlots.put(ParkingSlotType.MEDIUM_COMPACT, mediumCompactSlots);

        Map<String,ParkingSlot> largeCompactSlots = new HashMap<>();
        largeCompactSlots.put("L1",new ParkingSlot("L1",ParkingSlotType.LARGE_COMPACT));
        largeCompactSlots.put("L2",new ParkingSlot("L2",ParkingSlotType.LARGE_COMPACT));
        parkingSlots.put(ParkingSlotType.LARGE_COMPACT, largeCompactSlots);
        
        ParkingFloor parkingFloor = new ParkingFloor(1,parkingSlots);
        parkingLot.addFloor(parkingFloor);


        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleCategory(VehicleCategory.HATCHBACK);
        vehicle.setVehicleNumber("PB2901234");

        Ticket ticket = parkingLot.assignTicket(vehicle);
        // System.out.println(" ticket number : " + ticket.getTicketNumber());

        Thread.sleep(10000);

        double price = parkingLot.scanAndPay(ticket);
        System.out.println("price : " + price);
    }
}
