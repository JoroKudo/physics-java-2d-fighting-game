package application.common;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InitializingGamepad {

    List<net.java.games.input.Controller> gamepads = Arrays.stream(ControllerEnvironment.getDefaultEnvironment().getControllers()).filter(controller ->
            controller.getType().equals(net.java.games.input.Controller.Type.GAMEPAD)).collect(Collectors.toList());
    Controller gamepad = gamepads.get(0); // only working with one gamepad

    Event event;
    Component component;
    float value;
    String tempPosition = "";

}
