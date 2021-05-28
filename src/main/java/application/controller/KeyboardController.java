package application.controller;

import application.common.Controller;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class KeyboardController implements Controller, EventHandler<KeyEvent> {

    private List<KeyCode> pressedKeys = new ArrayList<>();
    private KeyCode[] keysPlayer1 = {KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.Q, KeyCode.E, KeyCode.V};
    private KeyCode[] keysPlayer2 = {KeyCode.I, KeyCode.J, KeyCode.K, KeyCode.L, KeyCode.U, KeyCode.O, KeyCode.M};

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
                if (pressedKeys.contains(keysPlayer1[action.ordinal()])) {
                    return action;
                }
            }
        } else if (id == 2) {
            for (ActionType action : ActionType.values()) {
                if (pressedKeys.contains(keysPlayer2[action.ordinal()])) {
                    return action;
                }
            }
        }
        return null;
    }
}
