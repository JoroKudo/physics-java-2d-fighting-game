package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.*;
import application.constants.Images;
import application.database.Firebase;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import javafx.scene.input.KeyCode;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class LeaderboardScene extends BaseScene  {
    public LeaderboardScene(Navigator navigator) throws IOException, ParseException {
        super(navigator, Images.leaderboard);

        Firebase firebase = new Firebase();

        firebase.getLeaderBoard();

        setOnKeyPressed(e -> {
            if ((e.getCode() == KeyCode.SPACE)) {
                navigator.goTo(SceneType.USER_SELECTION_SCENE);
            }
        });
    }

}
