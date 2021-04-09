package application.main;

import application.common.Navigator;
import application.gui.*;
import javafx.application.Application;

import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        Navigator navigator = new Navigator(primaryStage);
        navigator.registerScene(SceneType.GAME_SCENE, new GameScene(navigator));
        navigator.goTo(SceneType.GAME_SCENE);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}