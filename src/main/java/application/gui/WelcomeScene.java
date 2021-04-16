package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.*;
import application.constants.Images;
import javafx.scene.input.KeyCode;

public class WelcomeScene extends BaseScene  {
    public WelcomeScene(Navigator navigator) {
        super(navigator, Images.welcome);
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                navigator.goTo(SceneType.GAME_SCENE);
            }
        });
    }

}
