package application.gui;

import application.navigation.Navigator;
import application.navigation.SceneType;
import application.common.BaseScene;
import application.common.Initializable;
import application.constants.Images;
import application.firebase.RequestHandler;
import application.stats.Lifebar;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;

import java.io.IOException;

public class GameWinScene extends BaseScene implements Initializable {

    private final Lifebar lifebar1;
    private final Lifebar lifebar2;
    private final UserSelectionScene userSelectionScene;

    Scene test;

    public GameWinScene(Navigator<?> navigator, Lifebar l1, Lifebar l2, UserSelectionScene userSelectionScene) {
        super(navigator, Images.GameWin);
        this.lifebar1 = l1;
        this.lifebar2 = l2;
        this.userSelectionScene = userSelectionScene;

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                navigator.goTo(SceneType.LEADERBOARD_SCENE);
            }
        });
    }

    private void drawtext(String text) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFont(Font.font("Arial", 30));
        gc.strokeText(text, 650, 560);
        gc.setFont(Font.font("Arial", 15));
        gc.strokeText("press Space to see leaderboard", 650, 600);

    }

    @Override
    public void onInitialize() {
        String text = "";
        String winner = "";
        if (lifebar1.isKo()) {
            text = "Player " + userSelectionScene.getP2() + " Wins";
            winner = userSelectionScene.getP2();
        } else if (lifebar2.isKo()) {
            text = "Player " + userSelectionScene.getP1() + " Wins";
            winner = userSelectionScene.getP1();
        } else {
            if (lifebar1.getDamage() < lifebar2.getDamage()) {
                text = "Player " + userSelectionScene.getP1() + " Wins";
                winner = userSelectionScene.getP1();
            } else if (lifebar2.getDamage() < lifebar1.getDamage()) {
                text = "Player " + userSelectionScene.getP2() + " Wins";
                winner = userSelectionScene.getP2();
            } else if (lifebar1.getDamage() == lifebar2.getDamage()) {
                text = "It's a draw!";
            }
        }
        RequestHandler requestHandler = new RequestHandler();
        if (userSelectionScene.getP1() != null && userSelectionScene.getP2() != null && winner != null) {
            try {
                requestHandler.addFight(userSelectionScene.getP1(), userSelectionScene.getP2(), winner);
                requestHandler.addWin(winner);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            drawtext(text);
        }
    }
}
