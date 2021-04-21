package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.BaseScene;
import application.constants.Images;
import application.main.Game;
import application.stats.Lifebar_1;
import application.stats.Lifebar_2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class GameWinScene extends BaseScene  {

    Lifebar_1 lifebar1 = new Lifebar_1();
    Lifebar_2 lifebar2 = new Lifebar_2();
    static String text = "123";



    public GameWinScene(Navigator navigator) {
        super(navigator, Images.GameWin);

        checkwhowins();
        drawtext(text, 420, 150);

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                navigator.goTo(SceneType.GAME_SCENE);
            }
        });
    }

    private String checkwhowins() {
        if (lifebar1.getKO()== true){
            text = "Player 1 Wins";
            return text;
        }
        if (lifebar2.getKO() == true){
            text = "Player 2 Wins";
            return text;
        }
        text = "Winner can not be identified";

        return text;
    }

    private void drawtext(String text, int x, int y){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillText(text, x, y);
    }
}
