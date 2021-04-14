package application.common;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler implements EventHandler<KeyEvent> {

    private boolean isAPressed = false;
    private boolean isDPressed = false;
    private boolean isWPressed = false;
    private boolean isEPressed = false;
    private boolean isJPressed = false;
    private boolean isLPressed = false;
    private boolean isIPressed = false;
    private boolean isOPressed = false;
    private boolean isAReleased = true;
    private boolean isDReleased = true;
    private boolean isWReleased = true;
    private boolean isEReleased = true;
    private boolean isJReleased = true;
    private boolean isLReleased = true;
    private boolean isIReleased = true;
    private boolean isOReleased = true;

    @Override
    public void handle(KeyEvent event) {
        boolean pressed = event.getEventType() == KeyEvent.KEY_PRESSED;
        switch (event.getCode()) {
            case A -> isAPressed = pressed;
            case D -> isDPressed = pressed;
            case W -> isWPressed = pressed;
            case E -> isEPressed = pressed;
            case J -> isJPressed = pressed;
            case L -> isLPressed = pressed;
            case I -> isIPressed = pressed;
            case O -> isOPressed = pressed;
        }

        boolean released = event.getEventType() == KeyEvent.KEY_RELEASED;
        switch (event.getCode()) {
            case A -> isAReleased = released;
            case D -> isDReleased = released;
            case W -> isWReleased = released;
            case E -> isEReleased = released;
            case J -> isJReleased = released;
            case L -> isLReleased = released;
            case I -> isIReleased = released;
            case O -> isOReleased = released;
        }
    }

    public boolean isAPressed() {
        return isAPressed;
    }

    public boolean isDPressed() {
        return isDPressed;
    }

    public boolean isWPressed() {
        return isWPressed;
    }

    public boolean isEPressed() {
        return isEPressed;
    }

    public boolean isJPressed() {
        return isJPressed;
    }

    public boolean isLPressed() {
        return isLPressed;
    }

    public boolean isIPressed() {
        return isIPressed;
    }

    public boolean isOPressed() {
        return isOPressed;
    }

    public boolean isAReleased() {
        return isAReleased;
    }

    public boolean isDReleased() {
        return isDReleased;
    }
    public boolean isWReleased(){
        return isWReleased;
    }
    public boolean isEReleased(){
        return isEReleased;
    }

    public boolean isJReleased() {
        return isJReleased;
    }

    public boolean isLReleased() {
        return isLReleased;
    }
    public boolean isIReleased(){
        return isIReleased;
    }
    public boolean isOReleased(){
        return isOReleased;
    }

}
