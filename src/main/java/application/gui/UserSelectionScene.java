package application.gui;


import application.common.BaseScene;
import application.constants.Images;
import application.firebase.RequestHandler;
import application.sound.MusicType;
import application.sound.Sound;
import application.navigation.Navigator;
import application.navigation.SceneType;
import javafx.collections.FXCollections;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

import static javafx.scene.paint.Color.WHITE;

public class UserSelectionScene extends BaseScene {
    private String controllPlayer1 = "key";
    private String controllPlayer2 = "key";
    private String player1;
    private String player2;
    Map<String, Integer> fighter_score = new HashMap<>();
    ArrayList<String> playersFromDatabase;
    ArrayList<String> selectedPlayers = new ArrayList<>();
    RequestHandler requestHandler;
    ArrayList<String> players = new ArrayList<>();

    public UserSelectionScene(Navigator<?> navigator) throws IOException {
        super(navigator, Images.background);
        getStylesheets().add("css/Style.css");
        Sound.play(MusicType.MENU);

        requestHandler = new RequestHandler();
        playersFromDatabase = requestHandler.getAllFighters();
        JSONObject resobj = new JSONObject(playersFromDatabase.get(0));
        Iterator<?> keys = resobj.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            if (resobj.get(key) instanceof JSONObject) {
                JSONObject xx = new JSONObject(resobj.get(key).toString());
                fighter_score.put(key, (int) xx.get("wins"));
            }
        }

        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();

        fighter_score.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        int i = 0;
        for (Map.Entry<String, Integer> entry : reverseSortedMap.entrySet()) {
            if (i < 8) {
                players.add("" + entry.getKey());
                i++;
            } else break;
        }


        GridPane gridPane = new GridPane();
        gridPane.setHgap(50);
        gridPane.setVgap(60);

        //Create first combobox
        ComboBox<?> combo_box = new ComboBox<>(FXCollections.observableArrayList(players));
        combo_box.setEditable(true);
        combo_box.setPromptText("Select User");


        //create second combobox
        ComboBox<?> combo_box_2 = new ComboBox<>(FXCollections.observableArrayList(players));
        combo_box_2.setEditable(true);
        combo_box_2.setPromptText("Select User");

        ImageView controllerIconLeft = new ImageView(Images.controller);
        ImageView keyboardIconLeft = new ImageView(Images.keyboard);
        ImageView microphoneIconLeft = new ImageView(Images.microphone);

        ImageView controllerIconRight = new ImageView(Images.controller);
        ImageView keyboardIconRight = new ImageView(Images.keyboard);
        ImageView microphoneIconRight = new ImageView(Images.microphone);


        controllerIconLeft.setFitHeight(40);
        keyboardIconLeft.setFitHeight(40);
        microphoneIconLeft.setFitHeight(40);

        controllerIconRight.setFitHeight(40);
        keyboardIconRight.setFitHeight(40);
        microphoneIconRight.setFitHeight(40);


        controllerIconLeft.setPreserveRatio(true);
        keyboardIconLeft.setPreserveRatio(true);
        microphoneIconLeft.setPreserveRatio(true);

        controllerIconRight.setPreserveRatio(true);
        keyboardIconRight.setPreserveRatio(true);
        microphoneIconRight.setPreserveRatio(true);


        RadioButton controller1 = new RadioButton();
        RadioButton keyboard1 = new RadioButton();
        RadioButton mic1 = new RadioButton();

        RadioButton controller2 = new RadioButton();
        RadioButton keyboard2 = new RadioButton();
        RadioButton mic2 = new RadioButton();


        ToggleGroup group1 = new ToggleGroup();
        keyboard1.setToggleGroup(group1);
        controller1.setToggleGroup(group1);
        mic1.setToggleGroup(group1);

        ToggleGroup group2 = new ToggleGroup();
        keyboard2.setToggleGroup(group2);
        controller2.setToggleGroup(group2);
        mic2.setToggleGroup(group2);


        keyboard2.setSelected(true);
        keyboard1.setSelected(true);

        controller1.setPrefSize(40, 40);
        keyboard1.setPrefSize(40, 40);
        mic1.setPrefSize(40, 40);


        controller2.setPrefSize(40, 40);
        keyboard2.setPrefSize(40, 40);
        mic2.setPrefSize(40, 40);

        controller1.setGraphic(controllerIconLeft);
        keyboard1.setGraphic(keyboardIconLeft);
        mic1.setGraphic(microphoneIconLeft);


        controller2.setGraphic(controllerIconRight);
        keyboard2.setGraphic(keyboardIconRight);
        mic2.setGraphic(microphoneIconRight);


        //Create Player 1 text
        Text player1 = new Text("Player 1");
        player1.setFill(WHITE);
        player1.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        //Create Player 2 text
        Text player2 = new Text("Player 2");
        player2.setFill(WHITE);
        player2.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        //Draws Imag
        drawImage(Images.fighter_look_right, 460, 350);
        drawImage(Images.fighter_Bwalk_left, 900, 350);
        //Create Submit button
        Button submit = new Button("START");


        submit.setOnAction(e -> {
            if (mic1.isSelected()) {
                controllPlayer1 = "mic";
            } else if (keyboard1.isSelected()) {
                controllPlayer1 = "key";
            } else if (controller1.isSelected()) {
                controllPlayer1 = "ctrl";
            }
            if (mic2.isSelected()) {
                controllPlayer2 = "mic";
            } else if (keyboard2.isSelected()) {
                controllPlayer2 = "key";
            } else if (controller2.isSelected()) {
                controllPlayer2 = "ctrl";
            }


            if (combo_box.getValue() != null && combo_box_2.getValue() != null) {
                this.player1 = (String) combo_box.getValue();
                this.player2 = (String) combo_box_2.getValue();
                if (this.player1.length() > 8) {
                    this.player1 = this.player1.substring(0, 8);
                }
                if (this.player2.length() > 8) {
                    this.player2 = this.player2.substring(0, 8);
                }
                this.player1 = this.player1.toLowerCase();
                this.player2 = this.player2.toLowerCase();
                selectedPlayers.add(this.player1);
                selectedPlayers.add(this.player2);
                RequestHandler requestHandler = new RequestHandler();
                try {
                    for (String player : selectedPlayers) {
                        if (!requestHandler.checkIfFighterExists(player)) {
                            requestHandler.addFighter(player);
                        }
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                navigator.goTo(SceneType.GAME_SCENE);
            }


        });
        gridPane.add(player1, 9, 4, 1, 1);
        gridPane.add(combo_box, 9, 5, 1, 1);
        gridPane.add(mic1, 9, 6, 1, 1);
        gridPane.add(keyboard1, 9, 7, 1, 1);
        gridPane.add(controller1, 9, 8, 1, 1);


        gridPane.add(player2, 13, 4, 1, 1);
        gridPane.add(combo_box_2, 13, 5, 1, 1);
        gridPane.add(mic2, 13, 6, 1, 1);
        gridPane.add(keyboard2, 13, 7, 1, 1);
        gridPane.add(controller2, 13, 8, 1, 1);

        gridPane.add(submit, 11, 5, 1, 1);




        parent.add(gridPane);

    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    private void drawImage(Image image, double x, double y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, x, y);
    }

    public String getControllPlayer1() {
        return controllPlayer1;
    }

    public String getControllPlayer2() {
        return controllPlayer2;
    }


}






