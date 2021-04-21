package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.BaseScene;
import application.constants.Images;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import application.stats.Lifebar_1;
import application.stats.Lifebar_2;

public class GameWinScene extends BaseScene  {
    String Win_text;

    public GameWinScene(Navigator navigator) {
        super(navigator, Images.GameWin, "Player (Nr) Wins", 425, 150);
        //super(navigator, Images.Game_over);
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                navigator.goTo(SceneType.GAME_SCENE);
            }
        });
        };


    }
