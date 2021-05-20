package application.gui;

import application.Navigation.Navigator;
import application.Navigation.SceneType;
import application.common.BaseScene;
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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;

import static javafx.scene.paint.Color.WHITE;

public class GameOverScene extends BaseScene  {
    public GameOverScene(Navigator navigator) {
        super(navigator, Images.Game_over);

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                navigator.goTo(SceneType.WELCOME_SCENE);
            }
        });
    }
}


