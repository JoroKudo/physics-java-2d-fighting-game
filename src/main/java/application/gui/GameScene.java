package application.gui;

import application.common.KeyboardController;
import application.Navigation.Navigator;
import application.Sound.MusicType;
import application.Sound.Sound;
import application.common.*;
import application.main.Game;
import application.stats.Lifebar;

public class GameScene extends BaseScene implements Initializable {

    Lifebar lifebar1;
    Lifebar lifebar2;
    private FancyAnimationTimer gameLoop;

    public GameScene(Navigator navigator, Lifebar lifebar1, Lifebar lifebar2) {
        super(navigator);
        this.lifebar1 = lifebar1;
        this.lifebar2 = lifebar2;
    }


    @Override
    public void  onInitialize() {
        KeyboardController  keyboardController = new KeyboardController();
        GamepadController  gamepadController = new GamepadController();
        VoiceContrroll  voiceContrroll = new VoiceContrroll();
        this.setOnKeyPressed(keyboardController);
        this.setOnKeyReleased(keyboardController);
        Sound.play(MusicType.FIGHT);
        Game game = new Game(keyboardController,gamepadController,voiceContrroll, navigator, lifebar1, lifebar2, () -> gameLoopStopper());
        game.load();
        gameLoop = new FancyAnimationTimer() {
            @Override
            public void doHandle(double deltaInSec) {
                game.update(deltaInSec);
                game.draw(canvas.getGraphicsContext2D());
            }
        };
        gameLoop.start();

    }

    private void gameLoopStopper(){
        gameLoop.stop();
    }
}

