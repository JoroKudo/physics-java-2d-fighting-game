package application.controller;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class GamepadController implements application.common.Controller {

    private boolean initialized = false;
    private ControllerManager controllers;

    @Override
    public ActionType FighterXisActing(int id) {
        if (!initialized) {
            controllers = new ControllerManager();
            controllers.initSDLGamepad();
            initialized = true;
        }
        ControllerState currState = controllers.getState(0);
        if (currState.y) {
            return ActionType.JUMP;
        }
        if (currState.leftStickX <= -0.8) {
            return ActionType.WALK_lEFT;
        }
        if (currState.leftStickX >= 0.8) {
            return ActionType.WALK_RIGHT;
        }
        if (currState.b) {
            return ActionType.PUNCH;
        }
        if (currState.a) {
            return ActionType.DUCK;
        }
        if (currState.rightTrigger >= 0.1) {
            return ActionType.BLOCK;
        }
        if (currState.x) {
            return ActionType.HADOKEN;
        }
        return null;
    }
}

