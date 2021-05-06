package application.GameObjects;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class KeyboardController implements Controller, EventHandler<KeyEvent> {

    public List<KeyCode> pressedKeys = new ArrayList<>();

    @Override
    public void handle(KeyEvent event) {

        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (pressedKeys.contains(event.getCode())) {
                pressedKeys.add(event.getCode());
            }
        }
        if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            pressedKeys.remove(event.getCode());
        }
    }

    @Override
    public boolean isJUMP() {
        return pressedKeys.contains(KeyCode.W);
    }

    @Override
    public boolean isDUCK() {
        return pressedKeys.contains(KeyCode.S);
    }

    @Override
    public boolean isWALK_LEFT() {
        return pressedKeys.contains(KeyCode.A);
    }

    @Override
    public boolean isWALK_RIGHT() {
        return pressedKeys.contains(KeyCode.D);
    }

    @Override
    public boolean isPUNCH() {
        return pressedKeys.contains(KeyCode.E);
    }

    @Override
    public boolean isBLOCK() {
        return pressedKeys.contains(KeyCode.Q);
    }

    @Override
    public boolean isHADOUKEN() {
        return pressedKeys.contains(KeyCode.V);
    }
}
