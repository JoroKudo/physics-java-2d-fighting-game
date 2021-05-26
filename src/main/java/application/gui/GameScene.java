package application.gui;

import application.controller.KeyboardController;
import application.controller.GamepadController;
import application.controller.VoiceController;
import application.navigation.Navigator;
import application.sound.MusicType;
import application.sound.Sound;
import application.common.*;
import application.game.Game;
import application.stats.Lifebar;

public class GameScene extends BaseScene implements Initializable {

    Lifebar lifebar1;
    Lifebar lifebar2;
    UserSelectionScene userSelectionScene;
    private FancyAnimationTimer gameLoop;

    public GameScene(Navigator<?> navigator, Lifebar lifebar1, Lifebar lifebar2, UserSelectionScene userSelectionScene) {
        super(navigator);
        this.lifebar1 = lifebar1;
        this.lifebar2 = lifebar2;
        this.userSelectionScene = userSelectionScene;
    }


    @Override
    public void onInitialize() {
        KeyboardController keyboardController = new KeyboardController();
        GamepadController gamepadController = new GamepadController();
        VoiceController voiceController = new VoiceController();
        this.setOnKeyPressed(keyboardController);
        this.setOnKeyReleased(keyboardController);
        Sound.play(MusicType.FIGHT);
        Game game = new Game(keyboardController, gamepadController, voiceController, navigator, lifebar1, lifebar2, this::gameLoopStopper, userSelectionScene);
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

    private void gameLoopStopper() {
        gameLoop.stop();
    }
}

