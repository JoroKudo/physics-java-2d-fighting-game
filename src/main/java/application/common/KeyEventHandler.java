package application.common;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler implements EventHandler<KeyEvent> {

    private boolean isAPressed = false;
    private boolean isDPressed = false;
    private boolean isWPressed = false;
    private boolean isEPressed = false;
    private boolean isAReleased = true;
    private boolean isDReleased = true;
    private boolean isWReleased = true;
    private boolean isEReleased = true;

    @Override
    public void handle(KeyEvent event) {
        boolean pressed = event.getEventType() == KeyEvent.KEY_PRESSED;
        switch (event.getCode()) {
            case A -> isAPressed = pressed;
            case D -> isDPressed = pressed;
            case W -> isWPressed = pressed;
            case E -> isEPressed = pressed;
        }

        boolean released = event.getEventType() == KeyEvent.KEY_RELEASED;
        switch (event.getCode()) {
            case A -> isAReleased = released;
            case D -> isDReleased = released;
            case W -> isWReleased = released;
            case E -> isEReleased = released;
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

}
