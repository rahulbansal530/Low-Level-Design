package Panels;

import java.util.ArrayList;
import java.util.List;

import Buttons.DoorButton;
import Buttons.FloorButton;
import enums.DoorState;

public class InsidePanel {

    DoorButton openDoor = new DoorButton(DoorState.OPEN);
    DoorButton closeDoor = new DoorButton(DoorState.CLOSE);
    List<FloorButton> floorNumberButtons;

    public InsidePanel(int numFloors) {
        List<FloorButton> floorButtons = new ArrayList<>();
        for (int i = 1; i <= numFloors; i++) {
            FloorButton floorButton = new FloorButton();
            floorButton.setFloorNumber(i);
            floorButtons.add(floorButton);
        }
    }
     
}