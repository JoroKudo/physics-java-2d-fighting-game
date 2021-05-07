package application.common;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.dyn4j.geometry.Vector2;

import java.util.ArrayList;
import java.util.List;

public class KeyboardController implements Controller, EventHandler<KeyEvent> {

    public List<KeyCode> pressedKeys = new ArrayList<>();
    public ActionType action;

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

        switch (id) {
            case 1:
                if (pressedKeys.contains(KeyCode.W)) {
                    return ActionType.JUMP;
                }

                if (pressedKeys.contains(KeyCode.A)) {
                    return ActionType.WALK_lEFT;
                }
                if (pressedKeys.contains(KeyCode.D)) {
                    return ActionType.WALK_RIGHT;
                }
                if (pressedKeys.contains(KeyCode.E)) {
                    return ActionType.PUNCH;
                }
                if (pressedKeys.contains(KeyCode.S)) {
                    return ActionType.DUCK;

                }
                if (pressedKeys.contains(KeyCode.Q)) {
                    return ActionType.BLOCK;

                }
                if (pressedKeys.contains(KeyCode.V)) {
                    return ActionType.HADOUKEN;
                }

            case 2:
                if (pressedKeys.contains(KeyCode.I)) {
                    return ActionType.JUMP;
                }
                if (pressedKeys.contains(KeyCode.J)) {
                    return ActionType.WALK_lEFT;
                }
                if (pressedKeys.contains(KeyCode.L)) {
                    return ActionType.WALK_RIGHT;
                }
                if (pressedKeys.contains(KeyCode.O)) {
                    return ActionType.PUNCH;
                }
                if (pressedKeys.contains(KeyCode.K)) {
                    return ActionType.DUCK;

                }
                if (pressedKeys.contains(KeyCode.U)) {
                    return ActionType.BLOCK;

                }
                if (pressedKeys.contains(KeyCode.M)) {
                    return ActionType.HADOUKEN;

                }


            default:
                return null;



        }

    }


}
