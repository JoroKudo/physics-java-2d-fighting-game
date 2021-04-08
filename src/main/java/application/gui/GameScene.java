package application.gui;

import application.Game;
import application.Navigation.Navigator;
import application.common.FancyAnimationTimer;
import application.common.Initializable;
import application.constants.Const;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import application.common.KeyHandler;

public class GameScene extends Scene implements Initializable {

    private static final StackPane root = new StackPane();
    private final Navigator navigator;
    private final GraphicsContext gc;
    private Game game;
    private AnimationTimer animationTimer;

    public GameScene(Navigator navigator) {
        super(root);
        this.navigator = navigator;

        Canvas canvas = new Canvas(Const.CANVAS_WIDTH, Const.CANVAS_HEIGHT);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
    }

    @Override
    public void initialise() {

        KeyHandler keyHandler = new KeyHandler();

        game = new Game(navigator, keyHandler, () -> animationTimer.stop());

//        game.initialise();
        animationTimer = new FancyAnimationTimer() {
            @Override
            protected void doHandle(double deltaInSec) {

//                game.update(deltaInSec);
                game.paint(gc);

            }
        };

        animationTimer.start();
    }
}
