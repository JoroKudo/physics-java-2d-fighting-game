package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.BaseScene;
import application.constants.Images;
import application.database.FirebaseRequestHandler;
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
    GridPane userTextGridPane = new GridPane();
    GridPane userScoreGridPane = new GridPane();
    Text userText;
    Text pointText;

    public LeaderboardScene(Navigator navigator) throws IOException {
        super(navigator, Images.leaderboard);

        FirebaseRequestHandler firebaseRequestHandler = new FirebaseRequestHandler();

        ArrayList<String> players = firebaseRequestHandler.getAllFighters();

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

        int x = 0;
        for (Map.Entry<String, Integer> entry : reverseSortedMap.entrySet()) {
            if (x < 10 && entry.getValue() != 0) {
                x++;
                userText = new Text(x + ".    " + entry.getKey());
                userText.setFill(WHITE);
                userText.setFont(Font.font("Copperplate Gothic Bold", FontWeight.BOLD, 30));
                userTextGridPane.add(userText, 0, x, 1, 1);
                pointText = new Text("" + entry.getValue());
                pointText.setFill(WHITE);
                pointText.setFont(Font.font("Copperplate Gothic Bold", FontWeight.BOLD, 30));
                userScoreGridPane.add(pointText, 1, x, 1, 1);
            } else break;


        }
        userTextGridPane.setAlignment(Pos.CENTER);
        userTextGridPane.setLayoutX(600);
        userTextGridPane.setLayoutY(250);
        parent.add(userTextGridPane);
        userScoreGridPane.setAlignment(Pos.CENTER);
        userScoreGridPane.setLayoutX(900);
        userScoreGridPane.setLayoutY(250);
        parent.add(userScoreGridPane);

    }

}
