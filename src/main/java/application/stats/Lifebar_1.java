package application.stats;

import application.constants.Const;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Lifebar_1 {

    private int damage = 100;
    private boolean ko = false;

    public void update(int damage) {
        this.damage += damage;
        if (this.damage >= Const.LIFEBAR_LENGTH) {
            ko = true;
        }
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect((Const.CANVAS_WIDTH-(2*Const.LIFEBAR_LENGTH)-Const.DISTANCE_BETWEEN_LIFEBAR)/2, 50, Const.LIFEBAR_LENGTH, 50); //Starting Point calculated dependent on canvas width
        gc.setFill(Color.RED);
        gc.fillRect((Const.CANVAS_WIDTH-(2*Const.LIFEBAR_LENGTH)-Const.DISTANCE_BETWEEN_LIFEBAR)/2, 50, damage, 50);
    }

    public boolean getKO() {
        return ko;
    }

}
