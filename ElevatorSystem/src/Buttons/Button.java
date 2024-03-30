package Buttons;
public abstract class Button {

    private boolean isPressed;

    public boolean isPressed() {

        return isPressed;
    }

    public void press() {

        this.isPressed = true;
    }

    public void unpress() {
        this.isPressed = false;
    }

}