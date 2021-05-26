package application.common;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class KeyboardController implements Controller, EventHandler<KeyEvent> {

    public List<KeyCode> pressedKeys = new ArrayList<>();
    public KeyCode[] allKeys1 = new KeyCode[]{
            KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.Q, KeyCode.E, KeyCode.V};
    public KeyCode[] allKeys2 = new KeyCode[]{
            KeyCode.I, KeyCode.J, KeyCode.K, KeyCode.L, KeyCode.U, KeyCode.O, KeyCode.M};

    @Override
    public void handle(KeyEvent event) {

        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (notInList(event.getCode())) {
                pressedKeys.add(event.getCode());
            }
        }
        if (event.getEventType() == KeyEvent.KEY_RELEASED) {
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

    @Override
    public ActionType FighterXisActing(int id) {
        if (id == 1) {
            for (ActionType action : ActionType.values()) {
                if (pressedKeys.contains(allKeys1[action.ordinal()])) {
                    return action;
                }
            }
        } else if (id == 2) {
            for (ActionType action : ActionType.values()) {
                if (pressedKeys.contains(allKeys2[action.ordinal()])) {
                    return action;
                }
            }
        }
        return null;
    }
}
