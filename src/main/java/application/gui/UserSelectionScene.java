package application.gui;


import application.Navigation.Navigator;
import application.Navigation.SceneType;

import application.common.*;
import application.constants.Images;
import application.database.Request;
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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

import static javafx.scene.paint.Color.WHITE;

public class UserSelectionScene extends BaseScene {
    public static String ValueComboBox1;
    public static String ValueComboBox2;

    public UserSelectionScene(Navigator navigator) {
        super(navigator, Images.background);

        String players[] = {"Fabian", "Leo", "Jiro"}; //TODO insert values form database here
        //Create first combobox
        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(players));
        combo_box.setEditable(true);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(50);
        gridPane.setVgap(60);

        //create second combobox
        ComboBox combo_box_2 = new ComboBox(FXCollections.observableArrayList(players));
        combo_box_2.setEditable(true);

        //create second combobox
        ComboBox combo_box_input_1 = new ComboBox(FXCollections.observableArrayList("Keyboard", "Gamepad"));
        combo_box_input_1.setEditable(true);

        //create second combobox
        ComboBox combo_box_input_2 = new ComboBox(FXCollections.observableArrayList("Keyboard", "Gamepad"));
        combo_box_input_2.setEditable(true);

        ImageView ctrlview2 = new ImageView(Images.controller);
        ImageView key2img = new ImageView(Images.keyboard);
        ImageView micview2 = new ImageView(Images.microphone);

        ctrlview2.setFitHeight(40);
        key2img.setFitHeight(40);
        micview2.setFitHeight(40);

        ctrlview2.setPreserveRatio(true);
        key2img.setPreserveRatio(true);
        micview2.setPreserveRatio(true);

        final ToggleGroup group = new ToggleGroup();

        RadioButton controller2 = new RadioButton();
        RadioButton keyboard2 = new RadioButton();
        RadioButton mic2 = new RadioButton();

        keyboard2.setToggleGroup(group);
        controller2.setToggleGroup(group);
        mic2.setToggleGroup(group);

        keyboard2.setSelected(true);


        controller2.getStylesheets().add("button.css");
        keyboard2.getStylesheets().add("button.css");
        mic2.getStylesheets().add("button.css");

        controller2.setPrefSize(40, 40);
        keyboard2.setPrefSize(40, 40);
        mic2.setPrefSize(40, 40);


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
            ValueComboBox1 = (String) combo_box_input_1.getValue();
            ValueComboBox2 = (String) combo_box_input_2.getValue();
            String valueComboBox1 = (String) combo_box.getValue();
            String valueComboBox2 = (String) combo_box_2.getValue();
            System.out.println(valueComboBox1);
            System.out.println(valueComboBox2);
            System.out.println(combo_box.getValue());
            if (combo_box.getValue() != null && combo_box_2.getValue() != null) {
                Request request = new Request();
                try {
                    request.addFight((String) combo_box.getValue(), (String) combo_box_2.getValue());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                navigator.goTo(SceneType.GAME_SCENE);
            }


        });
        gridPane.add(player1, 9, 4, 1, 1);
        gridPane.add(combo_box, 9, 5, 1, 1);
        gridPane.add(combo_box_input_1, 9, 8, 1, 1);

        gridPane.add(player2, 13, 4, 1, 1);
        gridPane.add(combo_box_2, 13, 5, 1, 1);
        gridPane.add(combo_box_input_2, 13, 6, 1, 1);
        gridPane.add(keyboard2, 13, 7, 1, 1);
        gridPane.add(controller2, 13, 8, 1, 1);

        gridPane.add(submit, 11, 5, 1, 1);


        parent.add(gridPane);

    }

    private void drawImage(Image image, double x, double y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, x, y);
    }


}



