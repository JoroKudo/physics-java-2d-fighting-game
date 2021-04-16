package application.main;

import application.GameObjects.*;
import application.common.KeyEventHandler;
import application.Navigation.Navigator;
import application.constants.Const;
import application.constants.Images;
import application.Navigation.SceneType;
import javafx.scene.canvas.GraphicsContext;

import java.util.concurrent.CopyOnWriteArrayList;

public class Welcome extends CopyOnWriteArrayList<GameObject> {

    public final KeyEventHandler keyEventHandler;
    private final Navigator navigator;

    public Welcome(KeyEventHandler keyEventHandler, Navigator navigator) {
        this.keyEventHandler = keyEventHandler;
        this.navigator = navigator;
    }

    public void update(double elapsedTime) {
        WelcomeKeyHandler();
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(Images.welcome, 0, 0);
    }

    public void WelcomeKeyHandler() {
        if (keyEventHandler.isKeyPressed(" "))
            navigator.goTo(SceneType.GAME_SCENE);
    }
}

