package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.BaseScene;
import application.common.Initializable;
import application.constants.Images;
import application.stats.Lifebar;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class GameWinScene extends BaseScene implements Initializable {

    Lifebar lifebar1;
    Lifebar lifebar2;

    public GameWinScene(Navigator navigator, String p1, String p2, Lifebar lifebar1, Lifebar lifebar2) {
        super(navigator, Images.GameWin);
        this.lifebar1 = lifebar1;
        this.lifebar2 = lifebar2;

       setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                navigator.goTo(SceneType.GAME_SCENE);
            }
        });
    }



    private void drawtext(String text, int x, int y){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillText(text, x, y);
    }


    @Override
    public void onInitialize() {
        String text;
        if (lifebar1.getKo()) {
            text = "Player 2 Wins";
        }
        if (lifebar2.getKo()) {
            text = "Player 1 Wins";
        } else {
            text = "Winner can't be indentified";
        }
        drawtext(text, 710, 350);
    }
}
