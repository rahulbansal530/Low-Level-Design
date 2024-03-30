import Panels.InsidePanel;
import enums.DoorState;
import enums.ElevatorState;

public class Elevator {

     int elevatorId;
     DoorState doorState;
     int currentFloor;
     ElevatorState elevatorState;
     InsidePanel insidePanel;


     public Elevator(int elevatorId, DoorState doorState, int currentFloor, ElevatorState elevatorState,
            InsidePanel insidePanel) {
        this.elevatorId = elevatorId;
        this.doorState = doorState;
        this.currentFloor = currentFloor;
        this.elevatorState = elevatorState;
        this.insidePanel = insidePanel;
    }

    public void move(int floorId) {

        // move to floor floorId
        if (floorId > currentFloor) {
            for (int i = currentFloor + 1; i <= floorId; i++) {
                System.out.println("Elevator "+ elevatorId +" moving to floor : " + i);
            }

        } else if (floorId < currentFloor){
            if (floorId > currentFloor) {
                for (int i = currentFloor + 1; i <= floorId; i++) {
                    System.out.println("Elevator "+ elevatorId +" moving to floor : " + i);
                }
            }
        } 

        System.out.println("Elevator " + elevatorId + " is not at floor : " + floorId);
        
     }

     public void openDoor(int floorId) {
        doorState = DoorState.OPEN;
     }

     public void closeDoor(int floorId) {
        doorState = DoorState.CLOSE;
     }

    public int getElevatorId() {
        return elevatorId;
    }

    public void setElevatorId(int elevatorId) {
        this.elevatorId = elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }

    public InsidePanel getInsidePanel() {
        return insidePanel;
    }

    public void setInsidePanel(InsidePanel insidePanel) {
        this.insidePanel = insidePanel;
    }

     
}