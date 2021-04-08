package application.Navigation;

import javafx.scene.Scene;
import javafx.stage.Stage;
import application.common.Initializable;
import java.util.HashMap;
import java.util.Map;

public class Navigator {

    private final Stage stage;
    private final Map<SceneType, Scene> viewMap = new HashMap<>();

    public Navigator(Stage stage) {
        this.stage = stage;
    }

    public void registerScene(SceneType enumScene, Scene scene) {
        viewMap.put(enumScene, scene);
    }

    public void goTo(SceneType scene) {
        Scene activeScene = viewMap.get(scene);
        if (activeScene instanceof Initializable) {
            ((Initializable) activeScene).onInitialize();
        }
        stage.setScene(activeScene);
    }
}