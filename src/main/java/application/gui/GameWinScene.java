package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.BaseScene;
import application.constants.Images;
import application.stats.Lifebar;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class GameWinScene extends BaseScene  {

    private Lifebar lifebar1  = new Lifebar(1);
    private Lifebar lifebar2  = new Lifebar(2);

    public GameWinScene(Navigator navigator) {
        super(navigator, Images.GameWin);

        String text = checkwhowins();
        drawtext(text, 420, 150);

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                navigator.goTo(SceneType.GAME_SCENE);
            }
        });
    }

    private String checkwhowins() {
        if (lifebar1.getKO()== true){
            return "Player 1 Wins";
        }
        if (lifebar2.getKO() == true){
            return "Player 2 Wins";
        }
        return "Winner can not be identified";
    }

    private void drawtext(String text, int x, int y){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillText(text, x, y);
    }
}
