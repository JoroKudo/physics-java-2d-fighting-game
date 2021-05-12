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
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.event.ActionEvent;

import static javafx.scene.paint.Color.WHITE;

public class UserSelectionScene extends BaseScene {
    public UserSelectionScene(Navigator navigator) {
        super(navigator, Images.background);
        String players[] = {"Fabian", "Leo", "Jiro"}; //TODO insert values form database here
        //Create first combobox
        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(players));
        combo_box.setEditable(true);
        TilePane tile_pane = new TilePane(combo_box);
        tile_pane.setLayoutX(Const.CANVAS_MIDDLE_X-Const.DISTANCE_CHOICEBOXE_TO_MIDDLE);
        tile_pane.setLayoutY(300);
        tile_pane.setHgap(0);
        parent.add(tile_pane);
        //create second combobox
        ComboBox combo_box_2 = new ComboBox(FXCollections.observableArrayList(players));
        combo_box_2.setEditable(true);
        TilePane tile_pane_2 = new TilePane(combo_box_2);
        tile_pane_2.setLayoutX(Const.CANVAS_MIDDLE_X+Const.DISTANCE_CHOICEBOXE_TO_MIDDLE-combo_box.getWidth());
        tile_pane_2.setLayoutY(300);
        tile_pane_2.setHgap(0);
        parent.add(tile_pane_2);
        //Create Player 1 text
        Text player1 = new Text("Player 1");
        player1.setFill(WHITE);
        player1.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        TilePane tilePane3 = new TilePane(player1);
        tilePane3.setLayoutX(500);
        tilePane3.setLayoutY(250);
        parent.add(tilePane3);
        //Create Player 2 text
        Text player2 = new Text("Player 2");
        player2.setFill(WHITE);
        player2.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        TilePane tilePane4 = new TilePane(player2);
        tilePane4.setLayoutX(900);
        tilePane4.setLayoutY(250);
        parent.add(tilePane4);
        //Draws Imag
        drawImage(Images.fighter_look_right, 500, 350);
        drawImage(Images.fighter_Bwalk_left, 880, 350);
        //Create Submit button
        Button submit = new Button("Submit");
        TilePane tilePane5 = new TilePane(submit);
        tilePane5.setLayoutX(Const.CANVAS_MIDDLE_X/2-submit.getWidth()/2);
        tilePane5.setLayoutY(300);
        parent.add(tilePane5);
        submit.setOnAction(e -> {
            String valueComboBox1 = (String) combo_box.getValue();
            String valueComboBox2 = (String) combo_box_2.getValue();
            System.out.println(valueComboBox1);
            System.out.println(valueComboBox2);
            if (valueComboBox1 != null && valueComboBox2 != null) {
                Request request = new Request("https://ultimate-arena-2d-default-rtdb.europe-west1.firebasedatabase.app/.json", "PUT", "{\n" +
                        "    \"fighter_1\": {\n" +
                        "        \"name\": \"" + valueComboBox1 + "\" \n" +
                        "    },\n" +
                        "    \"fighter_2\": {\n" +
                        "        \"name\": \"" + valueComboBox2 + "\"\n" +
                        "    }\n" +
                        "}");
                try {
                    request.doRequest();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                navigator.goTo(SceneType.GAME_SCENE);
            }
        });
    }

    private void drawImage(Image image, double x, double y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, x, y);
    }


}
