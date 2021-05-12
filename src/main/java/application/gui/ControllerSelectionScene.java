package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.BaseScene;
import application.constants.Const;
import application.constants.Images;
import javafx.collections.FXCollections;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static javafx.scene.paint.Color.*;

public class ControllerSelectionScene extends BaseScene {

    String ValueComboBox1;
    String ValueComboBox2;

    public ControllerSelectionScene(Navigator navigator) {
        super(navigator, Images.background);


        //Create first combobox
        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList("Keyboard", "Gamepad"));
        combo_box.setEditable(true);
        TilePane tile_pane = new TilePane(combo_box);
        tile_pane.setLayoutX(Const.CANVAS_MIDDLE_X-Const.DISTANCE_CHOICEBOXE_TO_MIDDLE);
        tile_pane.setLayoutY(300);
        tile_pane.setHgap(0);
        parent.add(tile_pane);

        ValueComboBox1 = (String) combo_box.getValue();

        //create Title
        Text title = new Text("choose your options");
        title.setFill(WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        TilePane tilePane3 = new TilePane(title);
        tilePane3.setLayoutX(250);
        tilePane3.setLayoutY(100);
        parent.add(tilePane3);

        // create second combobox
        ComboBox combo_box_2 = new ComboBox(FXCollections.observableArrayList("Keyboard", "Gamepad"));
        combo_box_2.setEditable(true);
        TilePane tile_pane_2 = new TilePane(combo_box_2);
        tile_pane_2.setLayoutX(Const.CANVAS_MIDDLE_X+Const.DISTANCE_CHOICEBOXE_TO_MIDDLE-combo_box.getWidth());
        tile_pane_2.setLayoutY(300);
        tile_pane_2.setHgap(0);
        parent.add(tile_pane_2);

        ValueComboBox2 = (String) combo_box.getValue();


        //Create Player 1 text
        Text player1 = new Text("Player 1");
        player1.setFill(WHITE);
        player1.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        TilePane tilePane6 = new TilePane(player1);
        tilePane6.setLayoutX(500);
        tilePane6.setLayoutY(250);
        parent.add(tilePane6);

        // Create Player 2 text
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
            navigator.goTo(SceneType.GAME_SCENE);
        });
    }

    private void drawImage(Image image, double x, double y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, x, y);
    }

    public String getValueComboBox1() {
        return ValueComboBox1;
    }

    public String getValueComboBox2() {
        return ValueComboBox2;
    }



}
