package application.gui;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class UserSelectionScene extends BaseScene {
    public UserSelectionScene(Navigator navigator) {
        super(navigator, Images.background);
        String players[] =
                {"Fabian", "Leo", "Jiro"};
        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(players));
        TilePane tile_pane = new TilePane(combo_box);
        tile_pane.setLayoutX(600);
        tile_pane.setLayoutY(500);
        parent.add(tile_pane);
        ComboBox combo_box_2 = new ComboBox(FXCollections.observableArrayList(players));
        TilePane tile_pane_2 = new TilePane(combo_box_2);
        tile_pane_2.setLayoutX(900);
        tile_pane_2.setLayoutY(500);
        parent.add(tile_pane_2);
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {
                navigator.goTo(SceneType.GAME_SCENE);
            }
        });
    }


}
