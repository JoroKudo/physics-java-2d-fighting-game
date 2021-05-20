package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.BaseScene;
import application.constants.Images;
import javafx.scene.input.KeyCode;


public class GameOverScene extends BaseScene  {
    public GameOverScene(Navigator navigator) {
        super(navigator, Images.Game_over);

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                navigator.goTo(SceneType.WELCOME_SCENE);
            }
        });
    }
}


