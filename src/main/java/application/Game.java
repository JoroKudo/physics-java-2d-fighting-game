package application;

import application.Navigation.Navigator;
import application.common.KeyHandler;
import application.gameobjects.Background;
import javafx.scene.canvas.GraphicsContext;

public class Game {

    private final Background background = new Background();
    private final KeyHandler keyHandler;

    public Game(Navigator navigator, KeyHandler keyHandler, Runnable gameLoopStopper) {
        this.keyHandler = keyHandler;
    }

    public void paint(GraphicsContext gc) {
        background.draw(gc);
    }

}
