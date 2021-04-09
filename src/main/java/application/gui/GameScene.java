package application.gui;

import application.common.BaseScene;
import application.common.FancyAnimationTimer;
import application.common.Initializable;
import application.common.Navigator;
import application.game.*;

public class GameScene extends BaseScene implements Initializable {
    public GameScene(Navigator navigator) {
        super(navigator);
    }

    @Override
    public void  onInitialize() {

        KeyEventHandler keyEventHandler = new KeyEventHandler();
        this.setOnKeyPressed(keyEventHandler);
        this.setOnKeyReleased(keyEventHandler);

        Game game = new Game(keyEventHandler, navigator);
        game.load();
        FancyAnimationTimer gameLoop = new FancyAnimationTimer() {
            @Override
            public void doHandle(double deltaInSec) {
                game.update(deltaInSec)
                ;
                game.draw(canvas.getGraphicsContext2D());

            }
        };
        gameLoop.start();
    }
}
