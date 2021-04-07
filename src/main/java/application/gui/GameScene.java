package application.gui;

import application.Navigation.Navigator;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import application.common.KeyHandler;

public class GameScene extends Scene {

    private static final StackPane root = new StackPane();
    private final Navigator navigator;
    private final GraphicsContext gc;
    private AnimationTimer animationTimer;

    public GameScene(Navigator navigator) {
        super(root);
        this.navigator = navigator;

        Canvas canvas = new Canvas(800, 600);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
    }

    @Override
    public void initialise() {

        KeyHandler keyHandler = new KeyHandler();
        setOnKeyPressed(keyHandler);
        setOnKeyReleased(keyHandler);
        keyHandler.initialise();

        game = new Game(navigator, score, keyHandler, () -> animationTimer.stop());
        Sound.play(MusicType.GAME_BACKGROUND);

        game.initialise();
        animationTimer = new FancyAnimationTimer() {
            @Override
            protected void doHandle(double deltaInSec) {

                game.update(deltaInSec);
                game.paint(gc);

            }
        };

        animationTimer.start();
    }
}
