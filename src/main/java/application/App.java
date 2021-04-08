package application;

import application.gui.GameScene;
import application.Navigation.Navigator;
import application.Navigation.SceneType;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {



        Navigator navigator = new Navigator(primaryStage);

        navigator.registerScene(SceneType.GAME_SCENE, new GameScene(navigator));
        navigator.goTo(SceneType.GAME_SCENE);
        primaryStage.show();
    }
}
