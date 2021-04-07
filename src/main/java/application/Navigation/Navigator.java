package application.Navigation;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Navigator {
    private final Stage stage;
    private final Map<SceneType, Scene> sceneMap = new HashMap<>();

    public Navigator(Stage stage) {
        this.stage = stage;
    }

    public void registerScene(SceneType sceneType, Scene scene) {
        sceneMap.put(sceneType, scene);
    }

    public void navigateTo(SceneType sceneType) {
        Scene scene = sceneMap.get(sceneType);
        stage.setScene(scene);
        stage.show();
    }
}
