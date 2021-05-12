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
        KeyboardController  keyboardController = new KeyboardController();
        GamepadController  gamepadController = new GamepadController();
        this.setOnKeyPressed(keyboardController);
        this.setOnKeyReleased(keyboardController);
        Sound.play(MusicType.FIGHT);
        Game game = new Game(keyboardController,gamepadController, navigator);
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
