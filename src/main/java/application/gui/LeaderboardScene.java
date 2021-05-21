package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.*;
import application.constants.Images;
import application.database.Firebase;
import javafx.scene.input.KeyCode;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

public class LeaderboardScene extends BaseScene  {

    Map<String, Integer> fighter_score = new HashMap<>();

    public LeaderboardScene(Navigator navigator) throws IOException {
        super(navigator, Images.leaderboard);

        Firebase firebase = new Firebase();

        ArrayList<String> players = firebase.returnAllFighters();

        JSONObject resobj = new JSONObject(players.get(0));
        Iterator<?> keys = resobj.keys();
        while(keys.hasNext() ) {
            String key = (String)keys.next();
            if ( resobj.get(key) instanceof JSONObject ) {
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



        setOnKeyPressed(e -> {
            if ((e.getCode() == KeyCode.SPACE)) {
                navigator.goTo(SceneType.USER_SELECTION_SCENE);
            }
        });
    }

}
