package application.common;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.beans.PropertyEditorManager;
import java.util.ArrayList;
import java.util.List;

public class KeyEventHandler implements EventHandler<KeyEvent> {

    public List<KeyCode> pressedKeys = new ArrayList<>();

    @Override
    public void handle(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (notInList(event.getCode())) {
                pressedKeys.add(event.getCode());
            }
        }
        if(event.getEventType() == KeyEvent.KEY_RELEASED) {
            pressedKeys.remove(event.getCode());
        }
    }

    private boolean notInList(KeyCode code) {
        for (KeyCode key : pressedKeys) {
            if (code.equals(key)) {
                return false;
            }
        }
        return true;
    }

    public boolean isKeyPressed(String s) {
        for (KeyCode key : pressedKeys) {
            if (s.equals(key.getChar())) {
                System.out.println(key.getChar());
                return true;
            }
        }
        return false;
    }



}
