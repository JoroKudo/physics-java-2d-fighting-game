package application.gui;

import application.navigation.Navigator;
import application.navigation.SceneType;
import application.common.BaseScene;
import application.common.Initializable;
import application.constants.Images;
import application.firebase.RequestHandler;
import application.stats.Lifebar;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;

import java.io.IOException;

public class GameWinScene extends BaseScene implements Initializable {

    private final Lifebar lifebar1;
    private final Lifebar lifebar2;
    private final UserSelectionScene userSelectionScene;

    public GameWinScene(Navigator<?> navigator, Lifebar lifebar1, Lifebar lifebar2, UserSelectionScene userSelectionScene) {
        super(navigator, Images.gameWin);
        this.lifebar1 = lifebar1;
        this.lifebar2 = lifebar2;
        this.userSelectionScene = userSelectionScene;

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                navigator.goTo(SceneType.LEADERBOARD_SCENE);
            }
        });
    }

    private void drawText(String text) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFont(Font.font("Arial", 40));
        gc.strokeText(text, 630, 560);
        gc.setFont(Font.font("Arial", 15));
        gc.strokeText("Press Space to see the leaderboard", 650, 620);
    }

    @Override
    public void onInitialize() {
        String text = "";
        String winner = "";
        if (lifebar1.isKo()) {
            text = "Player " + userSelectionScene.getPlayer2() + " Wins";
            winner = userSelectionScene.getPlayer2();
        } else if (lifebar2.isKo()) {
            text = "Player " + userSelectionScene.getPlayer1() + " Wins";
            winner = userSelectionScene.getPlayer1();
        } else {
            if (lifebar1.getDamage() < lifebar2.getDamage()) {
                text = "Player " + userSelectionScene.getPlayer1() + " Wins";
                winner = userSelectionScene.getPlayer1();
            } else if (lifebar2.getDamage() < lifebar1.getDamage()) {
                text = "Player " + userSelectionScene.getPlayer2() + " Wins";
                winner = userSelectionScene.getPlayer2();
            } else if (lifebar1.getDamage() == lifebar2.getDamage()) {
                text = "It's a draw!";
            }
        }
        RequestHandler requestHandler = new RequestHandler();
        if (userSelectionScene.getPlayer1() != null && userSelectionScene.getPlayer2() != null && winner != null) {
            try {
                requestHandler.addFight(userSelectionScene.getPlayer1(), userSelectionScene.getPlayer2(), winner);
                requestHandler.addWin(winner);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            drawText(text);
        }
    }
}
