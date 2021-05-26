package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.BaseScene;
import application.common.Initializable;
import application.constants.Images;
import application.database.FirebaseRequestHandler;
import application.stats.Lifebar;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.io.IOException;

public class GameWinScene extends BaseScene implements Initializable {

    private final Lifebar lifebar1;
    private final Lifebar lifebar2;
    private final UserSelectionScene userSelectionScene;

    public GameWinScene(Navigator<?> navigator, Lifebar l1, Lifebar l2, UserSelectionScene userSelectionScene) {
        super(navigator, Images.GameWin);
        this.lifebar1 = l1;
        this.lifebar2 = l2;
        this.userSelectionScene = userSelectionScene;

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                navigator.goTo(SceneType.GAME_SCENE);
            }
        });
    }

    private void drawtext(String text) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillText(text, 710, 350);
    }

    @Override
    public void onInitialize() {
        String text;
        String winner;
        if (lifebar1.isKo()) {
            text = "Player " + userSelectionScene.getP2() + " Wins";
            winner = userSelectionScene.getP2();
        } else if (lifebar2.isKo()) {
            text = "Player " + userSelectionScene.getP1() + " Wins";
            winner = userSelectionScene.getP1();
        } else {
            text = "Winner can't be indentified";
            winner = "no winner";
        }
        FirebaseRequestHandler firebaseRequestHandler = new FirebaseRequestHandler();
        if (userSelectionScene.getP1() != null && userSelectionScene.getP2() != null && winner != null) {
            try {
                firebaseRequestHandler.addFight(userSelectionScene.getP1(), userSelectionScene.getP2(), winner);
                firebaseRequestHandler.addWin(winner);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            drawtext(text);
        }
    }
}
