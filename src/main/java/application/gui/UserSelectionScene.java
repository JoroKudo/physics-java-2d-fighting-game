package application.gui;

import application.GameObjects.BasePlayer;
import application.GameObjects.RagFighter;
import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.Sound.MusicType;
import application.Sound.Sound;
import application.common.*;
import application.constants.Const;
import application.constants.Images;
import application.database.Request;
import application.main.Game;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

import static javafx.scene.paint.Color.WHITE;

public class UserSelectionScene extends BaseScene {
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
            String valueComboBox1 = (String) combo_box.getValue();
            String valueComboBox2 = (String) combo_box_2.getValue();
            System.out.println(valueComboBox1);
            System.out.println(valueComboBox2);
            if (valueComboBox1 != null && valueComboBox2 != null) {
                Request request = new Request();
                try {
                    request.doRequest("https://ultimate-arena-2d-default-rtdb.europe-west1.firebasedatabase.app/fightlog/fight"+ request.getNumberOfFights("https://ultimate-arena-2d-default-rtdb.europe-west1.firebasedatabase.app/fightlog.json")+".json", "PUT", "{\n" +
                            "    \"fighter_1\": {\n" +
                            "        \"name\": \"" + valueComboBox1 + "\"\n" +
                            "    },\n" +
                            "    \"fighter_2\": {\n" +
                            "        \"name\": \"" + valueComboBox2 + "\"\n" +
                            "    }\n" +
                            "}");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                navigator.goTo(SceneType.GAME_SCENE);
            }
        });

        gridPane.add(combo_box, 9, 5, 1, 1);
        gridPane.add(combo_box_2, 13, 5, 1, 1);
        gridPane.add(submit, 11, 5, 1, 1);
        gridPane.add(player2, 13, 4, 1, 1);
        gridPane.add(player1, 9, 4, 1, 1);
        parent.add(gridPane);

    }

    private void drawImage(Image image, double x, double y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, x, y);
    }


}



