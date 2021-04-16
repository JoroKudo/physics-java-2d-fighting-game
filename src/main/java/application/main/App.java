package application.main;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.gui.*;
import javafx.application.Application;

import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Navigator navigator = new Navigator(primaryStage);
        navigator.registerScene(SceneType.WELCOME_SCENE, new WelcomeScene(navigator));
        navigator.registerScene(SceneType.GAME_SCENE, new GameScene(navigator));
        navigator.goTo(SceneType.GAME_SCENE);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}