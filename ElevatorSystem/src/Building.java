import java.util.ArrayList;
import java.util.List;

public class Building {

    private String name;
    private List<Floor> floors;
    private List<Elevator> elevators;
    private static Building buildingInstance;

    public Building(String name) {

        this.name = name;
        this.floors = new ArrayList<>();
        this.elevators = new ArrayList<>();
    }

    public static synchronized Building getInstance(String name) {

        if (buildingInstance == null) {
            return new Building(name);
        }

        return buildingInstance;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }

    public void setElevators(List<Elevator> elevators) {
        this.elevators = elevators;
    }
}