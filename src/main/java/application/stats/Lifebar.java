package application.stats;

import javafx.scene.canvas.GraphicsContext;
import org.dyn4j.geometry.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Lifebar {

    private int damage = 0;
    private boolean ko = false;

    public void update(int damage) {
        this.damage += damage;
        if (this.damage > 400) {
            ko = true;
        }
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(600, 50, 400, 50);
        gc.setFill(Color.RED);
        gc.fillRect(1000-damage, 50, damage, 50);
    }

    public boolean getKO() {
        return ko;
    }

}
