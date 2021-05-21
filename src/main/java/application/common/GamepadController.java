package application.common;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import net.java.games.input.Component;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;
import net.java.games.input.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GamepadController implements application.common.Controller {


    public int e = 0;
    public ControllerManager controllers;


    @Override
    public ActionType FighterXisActing(int id) {
        if (e == 0) {
            controllers = new ControllerManager();
            controllers.initSDLGamepad();
            e++;
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
            return ActionType.HADOUKEN;
        }
        return null;
    }
}



//GAMEPADCONTROLLER WITH JINPUT THAT WILL BE FIXED BY LEO ASAP

        /*
            List<net.java.games.input.Controller> gamepads = Arrays.stream(ControllerEnvironment.getDefaultEnvironment().getControllers()).filter(controller ->
                    controller.getType().equals(net.java.games.input.Controller.Type.GAMEPAD)).collect(Collectors.toList());
            Controller gamepad = gamepads.get(0); // only working with one gamepad

            Event event;
            Component component;
            float value;
            String tempPosition = "";




        while (true) {
            gamepad.poll();

            EventQueue eq = gamepad.getEventQueue();
            event = new Event();

            while (eq.getNextEvent(event)) {
                component = event.getComponent();
                value = event.getValue();

                // clear temporarily stored position if analog stick is in neutral position
                if ((value < 0.3) && (value > -0.3) && (tempPosition.equals(component.getIdentifier().getName()))) {
                    tempPosition = "";
                }

                if (component.isAnalog()) {
                    // input from analog-sticks and back triggers
                    if ((value > 0.8) && !(tempPosition.equals(component.getIdentifier().getName()))) {
                        // positive direction
                        switch (component.getIdentifier().getName()) {
                            case "x":
                                // left stick - RIGHT
                                tempPosition = "x";
                                System.out.println("LEFT STICK RIGHT");
                                return ActionType.WALK_RIGHT;


                            case "y":
                                // left stick - DOWN
                                tempPosition = "y";
                                System.out.println("LEFT STICK DOWN");
                                return ActionType.DUCK;


                        }
                    }

                    if (value < -0.8 && !(tempPosition.equals(component.getIdentifier().getName()))) {
                        // negative direction
                        switch (component.getIdentifier().getName()) {
                            case "x":
                                // left stick - LEFT
                                tempPosition = "x";
                                System.out.println("LEFT STICK LEFT");
                                return ActionType.WALK_lEFT;


                        }
                    }
                } else {
                    // input from buttons, dpad analog-stick-pushes
                    if (value == 1.0) {
                        switch (component.getIdentifier().getName()) {
                            case "0":
                                // A-Button
                                System.out.println("BUTTON A");
                                return ActionType.DUCK;

                            case "1":
                                // B-Button
                                System.out.println("BUTTON B");
                                return ActionType.PUNCH;

                            case "2":
                                // X-Button
                                System.out.println("BUTTON X");
                                return ActionType.HADOUKEN;

                            case "3":
                                // Y-Button
                                System.out.println("BUTTON Y");
                                return ActionType.JUMP;

                            default:
                                break;
                        }

                    }
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

         */

