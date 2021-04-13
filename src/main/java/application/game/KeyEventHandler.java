package application.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler implements EventHandler<KeyEvent> {

    private boolean isAPressed = false;
    private boolean isDPressed = false;
    private boolean isSpaceKeyPressed = false;
    private boolean isAReleased = true;
    private boolean isDReleased = true;



    @Override
    public void handle(KeyEvent event) {
        boolean pressed = event.getEventType() == KeyEvent.KEY_PRESSED;
        switch (event.getCode()) {
            case A -> isAPressed = pressed;
            case D -> isDPressed = pressed;
            case SPACE -> isSpaceKeyPressed = pressed;
        }
        boolean released = event.getEventType() == KeyEvent.KEY_RELEASED;
        switch (event.getCode()) {
            case A -> isAReleased = released;
            case D -> isDReleased = released;
        }
    }

    public boolean isAPressed() {
        return isAPressed;
    }

    public boolean isDPressed() {
        return isDPressed;
    }

    public boolean isSpaceKeyPressed() {
        return isSpaceKeyPressed;
    }

    public boolean isAReleased() {
        return isAReleased;
    }

    public boolean isDReleased() {
        return isDReleased;
    }
}
