package application.gui;


import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.BaseScene;
import application.constants.Images;
import application.database.Firebase;
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

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.paint.Color.WHITE;

public class UserSelectionScene extends BaseScene {
    private String controll1 = "key";
    private String controll2 = "key";
    private String p1;
    private String p2;
    ArrayList<String> selectedPlayers = new ArrayList<>();


    public UserSelectionScene(Navigator<?> navigator) {
        super(navigator, Images.background);

        String[] players = {"Fabian", "Leo", "Jiro"}; //TODO insert values form database here
        //Create first combobox
        ComboBox<?> combo_box = new ComboBox<>(FXCollections.observableArrayList(players));
        combo_box.setEditable(true);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(50);
        gridPane.setVgap(60);

        //create second combobox
        ComboBox<?> combo_box_2 = new ComboBox<>(FXCollections.observableArrayList(players));
        combo_box_2.setEditable(true);


        ImageView ctrlview1 = new ImageView(Images.controller);
        ImageView key1img = new ImageView(Images.keyboard);
        ImageView micview1 = new ImageView(Images.microphone);

        ImageView ctrlview2 = new ImageView(Images.controller);
        ImageView key2img = new ImageView(Images.keyboard);
        ImageView micview2 = new ImageView(Images.microphone);


        ctrlview1.setFitHeight(40);
        key1img.setFitHeight(40);
        micview1.setFitHeight(40);

        ctrlview2.setFitHeight(40);
        key2img.setFitHeight(40);
        micview2.setFitHeight(40);


        ctrlview1.setPreserveRatio(true);
        key1img.setPreserveRatio(true);
        micview1.setPreserveRatio(true);

        ctrlview2.setPreserveRatio(true);
        key2img.setPreserveRatio(true);
        micview2.setPreserveRatio(true);


        RadioButton controller1 = new RadioButton();
        RadioButton keyboard1 = new RadioButton();
        RadioButton mic1 = new RadioButton();

        RadioButton controller2 = new RadioButton();
        RadioButton keyboard2 = new RadioButton();
        RadioButton mic2 = new RadioButton();


        ToggleGroup group1 = new ToggleGroup();
        keyboard2.setToggleGroup(group1);
        controller2.setToggleGroup(group1);
        mic2.setToggleGroup(group1);

        ToggleGroup group2 = new ToggleGroup();
        keyboard2.setToggleGroup(group2);
        controller2.setToggleGroup(group2);
        mic2.setToggleGroup(group2);


        keyboard2.setSelected(true);
        keyboard1.setSelected(true);


        controller1.getStylesheets().add("button.css");
        keyboard1.getStylesheets().add("button.css");
        mic1.getStylesheets().add("button.css");


        controller2.getStylesheets().add("button.css");
        keyboard2.getStylesheets().add("button.css");
        mic2.getStylesheets().add("button.css");

        controller1.setPrefSize(40, 40);
        keyboard1.setPrefSize(40, 40);
        mic1.setPrefSize(40, 40);


        controller2.setPrefSize(40, 40);
        keyboard2.setPrefSize(40, 40);
        mic2.setPrefSize(40, 40);

        controller1.setGraphic(ctrlview1);
        keyboard1.setGraphic(key1img);
        mic1.setGraphic(micview1);


        controller2.setGraphic(ctrlview2);
        keyboard2.setGraphic(key2img);
        mic2.setGraphic(micview2);


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
        Button submit = new Button("Submit");
        submit.getStylesheets().add("button.css");


        submit.setOnAction(e -> {
            if (mic1.isSelected()) {
                controll1 = "mic";
            } else if (keyboard1.isSelected()) {
                controll1 = "key";
            } else if (controller1.isSelected()) {
                controll1 = "ctrl";
            }
            if (mic2.isSelected()) {
                controll2 = "mic";
            } else if (keyboard2.isSelected()) {
                controll2 = "key";
            } else if (controller2.isSelected()) {
                controll2 = "ctrl";
            }

            String valueComboBox1 = (String) combo_box.getValue();
            String valueComboBox2 = (String) combo_box_2.getValue();
            if (combo_box.getValue() != null && combo_box_2.getValue() != null) {
                p1 = (String) combo_box.getValue();
                p2 = (String) combo_box_2.getValue();
                selectedPlayers.add(p1);
                selectedPlayers.add(p2);
                Firebase firebase = new Firebase();
                try {
                    for (String player : selectedPlayers) {
                        if (!firebase.checkIfFighterExists(player)) {
                            firebase.addFighter(player);
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

    public String getP1() {
        return p1;
    }

    public String getP2() {
        return p2;
    }

    private void drawImage(Image image, double x, double y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, x, y);
    }

    public String getcontroll1() {
        return controll1;
    }

    public String getcontroll2() {
        return controll2;
    }


}






