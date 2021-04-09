package application.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler implements EventHandler<KeyEvent> {

    private boolean isLeftKeyPressed = false;

    private boolean isRightKeyPressed = false;
    private boolean isSpaceKeyPressed = false;
    private boolean isLeftKeyReleased = true;
    private boolean isRightKeyReleased = true;



    @Override
    public void handle(KeyEvent event) {
        boolean pressed = event.getEventType() == KeyEvent.KEY_PRESSED;
        switch (event.getCode()) {
            case A -> isLeftKeyPressed = pressed;
            case D -> isRightKeyPressed = pressed;
            case SPACE -> isSpaceKeyPressed = pressed;




        }
        boolean released = event.getEventType() == KeyEvent.KEY_RELEASED;
        switch (event.getCode()) {
            case A -> isLeftKeyReleased = released;
            case D -> isRightKeyReleased = released;
        }
    }

    public boolean isLeftKeyPressed() {
        return isLeftKeyPressed;
    }

    public boolean isRightKeyPressed() {
        return isRightKeyPressed;
    }

    public boolean isSpaceKeyPressed() {
        return isSpaceKeyPressed;
    }

    public boolean isLeftKeyReleased() {
        return isLeftKeyReleased;
    }

    public boolean isRightKeyReleased() {
        return isRightKeyReleased;
    }



}
