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
    public void start(Stage primaryStage) throws Exception {
        Navigator navigator = new Navigator(primaryStage);
        Request request = new Request("https://ultimate-arena-2d-default-rtdb.europe-west1.firebasedatabase.app/.json", "PUT", "{\n" +
                "    \"user\": {\n" +
                "        \"fabian\": {\n" +
                "            \"password\": \"1234\",\n" +
                "            \"email\": \"fabian.surber@gmail.com\"\n" +
                "        },\n" +
                "        \"leo\": {\n" +
                "            \"password\": \"abc\",\n" +
                "            \"email\": \"leo.röttele@gmail.com\"\n" +
                "        }\n" +
                "    }\n" +
                "}");
        request.doRequest();
        Request getRequest = new Request("https://ultimate-arena-2d-default-rtdb.europe-west1.firebasedatabase.app/.json", "GET", "-");
        getRequest.doRequest();
        navigator.registerScene(SceneType.WELCOME_SCENE, new WelcomeScene(navigator));
        navigator.registerScene(SceneType.USER_SELECTION_SCENE, new UserSelectionScene(navigator));
        navigator.registerScene(SceneType.GAME_SCENE, new GameScene(navigator));
        navigator.registerScene(SceneType.GAME_WIN_SCENE, new GameWinScene(navigator));
        navigator.registerScene(SceneType.GAME_OVER_SCENE, new GameOverScene(navigator));
        navigator.goTo(SceneType.WELCOME_SCENE);

        primaryStage.show();
    }
}