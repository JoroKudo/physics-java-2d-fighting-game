package application.gui;

import application.navigation.Navigator;
import application.navigation.SceneType;
import application.common.*;
import application.constants.Images;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import javafx.scene.input.KeyCode;

public class WelcomeScene extends BaseScene {

    public WelcomeScene(Navigator<?> navigator) {
        super(navigator, Images.welcome);

        ControllerManager controllers = new ControllerManager();
        controllers.initSDLGamepad();
        ControllerState currState = controllers.getState(0);
        if ((currState.a)) {
            navigator.goTo(SceneType.USER_SELECTION_SCENE);
        }
        setOnKeyPressed(e -> {
            if ((e.getCode() == KeyCode.SPACE)) {
                navigator.goTo(SceneType.USER_SELECTION_SCENE);
            }
        });
    }

}
