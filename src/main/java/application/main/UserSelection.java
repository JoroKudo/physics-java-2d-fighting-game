package application.main;

import javafx.collections.FXCollections;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class UserSelection {
    ChoiceBox cb;

    public UserSelection() {
        cb = new ChoiceBox(FXCollections.observableArrayList(
                "First", "Second", "Third")
        );
    }

    public void draw(GraphicsContext gc) {

    }
}
