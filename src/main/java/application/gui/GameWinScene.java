package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.BaseScene;
import application.common.Initializable;
import application.constants.Images;
import application.database.Firebase;
import application.stats.Lifebar;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.io.IOException;

public class GameWinScene extends BaseScene implements Initializable {

    private Lifebar lifebar1;
    private Lifebar lifebar2;
    private String winner;
    private UserSelectionScene userSelectionScene;

    public GameWinScene(Navigator navigator, Lifebar l1, Lifebar l2, UserSelectionScene userSelectionScene) {
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

    private void drawtext(String text, int x, int y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillText(text, x, y);
    }

    @Override
    public void onInitialize() {
        String text;
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
        Firebase firebase = new Firebase();
        if (userSelectionScene.getP1() != null && userSelectionScene.getP2() != null && winner != null) {
            try {
                firebase.addFight(userSelectionScene.getP1(), userSelectionScene.getP2(), winner);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        drawtext(text, 710, 350);
    }
}
