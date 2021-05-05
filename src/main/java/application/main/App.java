package application.main;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.database.Request;
import application.gui.*;
import javafx.application.Application;


import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Navigator navigator = new Navigator(primaryStage);
        Request request = new Request("a", "b", "c");
        request.doRequest();
        navigator.registerScene(SceneType.WELCOME_SCENE, new WelcomeScene(navigator));
        navigator.registerScene(SceneType.GAME_SCENE, new GameScene(navigator));
        navigator.registerScene(SceneType.GAME_WIN_SCENE, new GameWinScene(navigator));
        navigator.registerScene(SceneType.GAME_OVER_SCENE, new GameOverScene(navigator));
        navigator.goTo(SceneType.WELCOME_SCENE);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}