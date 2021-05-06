package application.gui;

import application.common.KeyboardController;
import application.Navigation.Navigator;
import application.Sound.MusicType;
import application.Sound.Sound;
import application.common.*;
import application.main.Game;

public class GameScene extends BaseScene implements Initializable {
    public GameScene(Navigator navigator) {
        super(navigator);
    }

    @Override
    public void  onInitialize() {
        KeyboardController controller = new KeyboardController();
        this.setOnKeyPressed(controller);
        this.setOnKeyReleased(controller);
        Sound.play(MusicType.FIGHT);
        Game game = new Game(controller, navigator);
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
