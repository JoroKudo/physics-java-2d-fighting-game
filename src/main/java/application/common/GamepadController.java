package application.common;


import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GamepadController implements Controller {

    @Override
    public ActionType FighterXisActing(int id) {

        ControllerManager controllers = new ControllerManager();
        controllers.initSDLGamepad();
        ControllerState currState = controllers.getState(0);

        switch (id) {
            case 1:
                 if (currState.y) {
                     return ActionType.JUMP;
                 }

                 if (currState.leftStickX <= -0.8) {
                     return ActionType.WALK_lEFT;
                 }

                 if (currState.leftStickX >=0.8) {
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
                     return ActionType.HADOUKEN;
                 }

            case 2:/*
                if (currState.y) {
                     return ActionType.JUMP;
                 }

                 if (currState.leftStickX <= -0.8) {
                     return ActionType.WALK_lEFT;
                 }

                 if (currState.leftStickX >=0.8) {
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
                     return ActionType.HADOUKEN;
                 }
                */
            default:
                return null;
        }

        }


}

