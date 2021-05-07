package application.common;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class KeyboardController implements Controller, EventHandler<KeyEvent> {

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


    @Override
    public boolean Fighter1isJUMP() {
        return pressedKeys.contains(KeyCode.W);
    }

    @Override
    public boolean Fighter1isDUCK() {
        return pressedKeys.contains(KeyCode.S);
    }

    @Override
    public boolean Fighter1isWALK_LEFT() {
        return pressedKeys.contains(KeyCode.A);
    }

    @Override
    public boolean Fighter1isWALK_RIGHT() {
        return pressedKeys.contains(KeyCode.D);
    }

    @Override
    public boolean Fighter1isPUNCH() {
        return pressedKeys.contains(KeyCode.E);
    }

    @Override
    public boolean Fighter1isBLOCK() {
        return pressedKeys.contains(KeyCode.Q);
    }

    @Override
    public boolean Fighter1isHADOUKEN() {
        return pressedKeys.contains(KeyCode.V);
    }

    @Override
    public boolean Fighter2isJUMP() {
        return pressedKeys.contains(KeyCode.I);
    }

    @Override
    public boolean Fighter2isDUCK() {
        return pressedKeys.contains(KeyCode.K);
    }

    @Override
    public boolean Fighter2isWALK_LEFT() {
        return pressedKeys.contains(KeyCode.J);
    }

    @Override
    public boolean Fighter2isWALK_RIGHT() {
        return pressedKeys.contains(KeyCode.L);
    }

    @Override
    public boolean Fighter2isPUNCH() {
        return pressedKeys.contains(KeyCode.O);
    }

    @Override
    public boolean Fighter2isBLOCK() {
        return pressedKeys.contains(KeyCode.U);
    }

    @Override
    public boolean Fighter2isHADOUKEN() {
        return pressedKeys.contains(KeyCode.M);
    }

    @Override
    public boolean RagFighterJump() {
        return pressedKeys.contains(KeyCode.T);
    }

    @Override
    public boolean RagFighterDuck() {
        return pressedKeys.contains(KeyCode.G);
    }

    @Override
    public boolean RagFighterLeft() {
        return pressedKeys.contains(KeyCode.F);
    }

    @Override
    public boolean RagFighterRight() {
        return pressedKeys.contains(KeyCode.H);
    }
}
