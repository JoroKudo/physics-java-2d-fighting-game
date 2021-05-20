package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.BaseScene;
import application.constants.Images;
import application.stats.Lifebar;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class GameWinScene extends BaseScene {

    Lifebar lifebar1;
    Lifebar lifebar2;

    public GameWinScene(Navigator navigator, Lifebar lifebar1, Lifebar lifebar2) {
        super(navigator, Images.GameWin);
        this.lifebar1 = lifebar1;
        this.lifebar2 = lifebar2;
        String text = checkwhowins();
        drawtext(text, 710, 350);


        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                navigator.goTo(SceneType.GAME_SCENE);
            }
        });
    }



    private String checkwhowins() {
        if (lifebar1.getKo()){
            return "Player 2 Wins";
        }
        if (lifebar2.getKo()){
            return "Player 1 Wins";
        }
        else {
            return "Winner can't be indentified";
        }
    }

    private void drawtext(String text, int x, int y){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillText(text, x, y);

    }





}
