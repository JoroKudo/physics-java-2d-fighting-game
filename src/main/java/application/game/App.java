package application.game;

import application.gui.*;
import application.navigation.Navigator;
import application.navigation.SceneType;
import application.stats.Lifebar;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Navigator<SceneType> navigator = new Navigator<>(primaryStage);
        Lifebar lifebar1 = new Lifebar(1);
        Lifebar lifebar2 = new Lifebar(2);
        WelcomeScene welcomescene = new WelcomeScene(navigator);
        welcomescene.getStylesheets().add("Button.css");
        UserSelectionScene userSelectionScene = new UserSelectionScene(navigator);
        navigator.registerScene(SceneType.WELCOME_SCENE, welcomescene);
        navigator.registerScene(SceneType.USER_SELECTION_SCENE, userSelectionScene);
        navigator.registerScene(SceneType.GAME_SCENE, new GameScene(navigator, lifebar1, lifebar2, userSelectionScene));
        navigator.registerScene(SceneType.GAME_WIN_SCENE, new GameWinScene(navigator, lifebar1, lifebar2, userSelectionScene));
        navigator.registerScene(SceneType.LEADERBOARD_SCENE, new LeaderboardScene(navigator));
        navigator.goTo(SceneType.WELCOME_SCENE);
        primaryStage.show();
    }
}