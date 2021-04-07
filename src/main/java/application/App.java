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

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                JOptionPane.showMessageDialog(null, "Something goes wrong!");
            }
        });

        Navigator navigator = new Navigator(primaryStage);

        navigator.registerScene(SceneType.GAME_SCENE, new GameScene(navigator));
        navigator.navigateTo(SceneType.GAME_SCENE);
        primaryStage.show();
    }
}
