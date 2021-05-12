package application.main;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.database.Request;
import application.gui.*;
import javafx.application.Application;


import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public Scene welcomescene;

    @Override
    public void start(Stage primaryStage) throws Exception {


        Navigator navigator = new Navigator(primaryStage);
        welcomescene = new WelcomeScene(navigator);
        welcomescene.getStylesheets().add("button.css");
        navigator.registerScene(SceneType.WELCOME_SCENE,  welcomescene);


        navigator.registerScene(SceneType.USER_SELECTION_SCENE, new UserSelectionScene(navigator));
        navigator.registerScene(SceneType.GAME_SCENE, new GameScene(navigator));
        navigator.registerScene(SceneType.GAME_WIN_SCENE, new GameWinScene(navigator));
        navigator.registerScene(SceneType.GAME_OVER_SCENE, new GameOverScene(navigator));
        navigator.goTo(SceneType.WELCOME_SCENE);

        primaryStage.show();
    }
}