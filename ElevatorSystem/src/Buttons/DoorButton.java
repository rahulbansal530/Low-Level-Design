package Buttons;
import enums.DoorState;

public class DoorButton extends Button {

    private DoorState doorState;

    public DoorButton(DoorState doorState) {
        this.doorState = doorState;
    }

    @Override
    public void press() {
        super.press();
        if (doorState == DoorState.CLOSE) {
            doorState = DoorState.OPEN;
        } else {
            doorState = DoorState.CLOSE;
        }
        super.unpress();
    }
}
