package application.stats;

import application.constants.Const;
import javafx.scene.canvas.GraphicsContext;
import org.dyn4j.geometry.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Lifebar_2 {

    private int damage = 0;
    private boolean ko = false;

    public void update(int damage) {
        this.damage += damage;
        if (this.damage >= Const.LIFEBAR_LENGTH) {
            ko = true;
        }
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(Const.CANVAS_WIDTH-Const.LIFEBAR_LENGTH-((Const.CANVAS_WIDTH-(2*Const.LIFEBAR_LENGTH)-Const.DISTANCE_BETWEEN_LIFEBAR)/2), 50, Const.LIFEBAR_LENGTH, 50); //starting point calculated dependet on canvas length
        gc.setFill(Color.RED);
        gc.fillRect(Const.CANVAS_WIDTH-(Const.CANVAS_WIDTH-(2*Const.LIFEBAR_LENGTH)-Const.DISTANCE_BETWEEN_LIFEBAR)/2-damage, 50, damage, 50);
    }

    public boolean getKO() {
        return ko;
    }

}
