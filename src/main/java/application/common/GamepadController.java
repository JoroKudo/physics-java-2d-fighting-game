package application.common;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.dyn4j.geometry.Vector2;

import java.util.ArrayList;
import java.util.List;

public class GamepadController implements Controller {


    public int e = 0;
    public ControllerManager controllers;




    @Override
    public ActionType FighterXisActing(int id) {


        if (id == 1) {
            if (e == 0) {
                controllers = new ControllerManager();
                controllers.initSDLGamepad();
                e++;
            }


            //Print a message when the "A" button is pressed. Exit if the "B" button is pressed
            //or the controller disconnects.

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
                return ActionType.HADOUKEN;
            }


        } else if (id == 2) {
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
                return ActionType.HADOUKEN;
            }

        }
        return null;
    }
}




