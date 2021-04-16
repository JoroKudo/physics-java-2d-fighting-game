package application.stats;

import application.constants.Const;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Timer {

    private double time = 100;

    public void update(Double deltaInSec) {
        time -= deltaInSec;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        gc.fillText("" + (int) time, Const.CANVAS_WIDTH/2-10, 120);
    }

}
