package application.common;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.beans.PropertyEditorManager;
import java.util.ArrayList;
import java.util.List;

public class KeyEventHandler implements EventHandler<KeyEvent> {

    private List<KeyCode> pressedKeys = new ArrayList<>();

    @Override
    public void handle(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED) {
            pressedKeys.add(event.getCode());
        }
        if(event.getEventType() == KeyEvent.KEY_RELEASED) {
            for (KeyCode key : pressedKeys) {
                if (key.equals(event.getCode())) {
                    pressedKeys.remove(event.getCode());
                }
            }
        }
    }

    public boolean isKeyPressed(String s) {
        for (KeyCode key : pressedKeys) {
            if (s.equals(key.getChar())) {
                return true;
            }
        }
        return false;
    }

}
