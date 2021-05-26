package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.BaseScene;
import application.constants.Const;
import application.constants.Images;
import application.database.Firebase;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

import static javafx.scene.paint.Color.WHITE;

public class LeaderboardScene extends BaseScene {

    Map<String, Integer> fighter_score = new HashMap<>();
    GridPane gridPane = new GridPane();
    Text text;

    public LeaderboardScene(Navigator navigator) throws IOException {
        super(navigator, Images.leaderboard);

        Firebase firebase = new Firebase();

        ArrayList<String> players = firebase.getAllFighters();

        JSONObject resobj = new JSONObject(players.get(0));
        Iterator<?> keys = resobj.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            if (resobj.get(key) instanceof JSONObject) {
                JSONObject xx = new JSONObject(resobj.get(key).toString());
                fighter_score.put(key, (int) xx.get("wins"));
            }
        }

        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();

        //Use Comparator.reverseOrder() for reverse ordering
        fighter_score.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        for (Map.Entry<String, Integer> entry : reverseSortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        int x = 0;
        for (Map.Entry<String, Integer> entry : reverseSortedMap.entrySet()) {
            text = new Text(entry.getKey() + ": " + entry.getValue());
            text.setFill(WHITE);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 30));
            gridPane.add(text, 0, x, 1, 1);
            x++;
        }
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setLayoutX(Const.CANVAS_WIDTH / 2.0);
        gridPane.setLayoutY(150);
        parent.add(gridPane);
        setOnKeyPressed(e -> {
            if ((e.getCode() == KeyCode.SPACE)) {
                navigator.goTo(SceneType.USER_SELECTION_SCENE);
            }
        });
    }

}
