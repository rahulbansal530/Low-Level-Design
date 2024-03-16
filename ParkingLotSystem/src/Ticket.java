import java.util.UUID;

public class Ticket {

    private String ticketNumber;
    private long startTime;
    private long endTime;
    private Vehicle vehicle;
    private ParkingSlot parkingSlot;

    public static Ticket createTicket(Vehicle vehicle, ParkingSlot parkingSlot) {

        Ticket ticket = new Ticket();
        ticket.setStartTime(System.currentTimeMillis());
        ticket.setParkingSlot(parkingSlot);
        ticket.setVehicle(vehicle);
        ticket.setTicketNumber(UUID.randomUUID().toString());
        System.out.println("Ticket created with ticket number - " + ticket.getTicketNumber() + " and start time - " + ticket.getStartTime());
        return ticket;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    
    
}