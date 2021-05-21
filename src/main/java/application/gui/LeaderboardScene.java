package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.*;
import application.constants.Images;
import application.database.Firebase;
import javafx.scene.input.KeyCode;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class LeaderboardScene extends BaseScene  {
    public LeaderboardScene(Navigator navigator) throws IOException {
        super(navigator, Images.leaderboard);

        Firebase firebase = new Firebase();

        firebase.addWin("Fabian");
        System.out.println(firebase.getWins("Fabian"));

        setOnKeyPressed(e -> {
            if ((e.getCode() == KeyCode.SPACE)) {
                navigator.goTo(SceneType.USER_SELECTION_SCENE);
            }
        });
    }

}
