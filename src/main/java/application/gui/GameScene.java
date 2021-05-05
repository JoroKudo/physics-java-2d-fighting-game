package application.gui;

import application.Navigation.Navigator;
import application.Sound.MusicType;
import application.Sound.Sound;
import application.common.KeyEventHandler;
import application.common.*;
import application.main.Game;

import java.security.PrivateKey;

public class GameScene extends BaseScene implements Initializable {
    public GameScene(Navigator navigator) {
        super(navigator);
    }

    @Override
    public void  onInitialize() {
        KeyEventHandler keyEventHandler = new KeyEventHandler();
        this.setOnKeyPressed(keyEventHandler);
        this.setOnKeyReleased(keyEventHandler);
        Sound.play(MusicType.FIGHT);
        Game game = new Game(keyEventHandler, navigator);
        game.load();
        FancyAnimationTimer gameLoop = new FancyAnimationTimer() {
            @Override
            public void doHandle(double deltaInSec) {
                game.update(deltaInSec);
                game.draw(canvas.getGraphicsContext2D());
            }
        };
        gameLoop.start();
    }
}
