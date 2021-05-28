package application.gui;

import application.common.BaseScene;
import application.common.FancyAnimationTimer;
import application.common.Initializable;
import application.controller.GamepadController;
import application.controller.KeyboardController;
import application.controller.VoiceController;
import application.game.Game;
import application.navigation.Navigator;
import application.navigation.SceneType;
import application.sound.MusicType;
import application.sound.Sound;
import application.stats.Lifebar;
import javafx.scene.input.KeyCode;

public class GameScene extends BaseScene implements Initializable {

    private final Lifebar lifebar1;
    private final Lifebar lifebar2;
    private final UserSelectionScene userSelectionScene;
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
        setOnKeyPressed(e -> {
            if ((e.getCode() == KeyCode.SPACE)) {
                gameLoop.stop();
                navigator.goTo(SceneType.GAME_WIN_SCENE);
            }
        });

    }
}

