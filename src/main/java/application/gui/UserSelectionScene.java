package application.gui;

import application.GameObjects.BasePlayer;
import application.GameObjects.RagFighter;
import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.Sound.MusicType;
import application.Sound.Sound;
import application.common.*;
import application.constants.Images;
import application.main.Game;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static javafx.scene.paint.Color.WHITE;

public class UserSelectionScene extends BaseScene {
    public UserSelectionScene(Navigator navigator) {
        super(navigator, Images.background);
        String players[] =
                {"Fabian", "Leo", "Jiro"};
        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(players));
        TilePane tile_pane = new TilePane(combo_box);
        tile_pane.setLayoutX(500);
        tile_pane.setLayoutY(300);
        parent.add(tile_pane);
        ComboBox combo_box_2 = new ComboBox(FXCollections.observableArrayList(players));
        TilePane tile_pane_2 = new TilePane(combo_box_2);
        tile_pane_2.setLayoutX(900);
        tile_pane_2.setLayoutY(300);
        parent.add(tile_pane_2);
        Text player1 = new Text("Player 1");
        player1.setFill(WHITE);
        player1.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        TilePane tilePane3 = new TilePane(player1);
        tilePane3.setLayoutX(500);
        tilePane3.setLayoutY(250);
        parent.add(tilePane3);
        Text player2 = new Text("Player 2");
        player2.setFill(WHITE);
        player2.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        TilePane tilePane4 = new TilePane(player2);
        tilePane4.setLayoutX(900);
        tilePane4.setLayoutY(250);
        parent.add(tilePane4);
        drawImage(Images.fighter_look_right, 460, 350);
        drawImage(Images.fighter_Bwalk_left, 840, 350);
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {
                navigator.goTo(SceneType.GAME_SCENE);
            }
        });
    }

    private void drawImage(Image image, double x, double y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, x, y);
    }


}
