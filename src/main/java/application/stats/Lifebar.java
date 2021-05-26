package application.stats;

import application.constants.Const;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Lifebar {

    private final int lifebarNumber;
    private double damage = 0;

    public Lifebar(int lifebarNumber) {
        this.lifebarNumber = lifebarNumber;
    }

    public void increaseDamage(double damage) {
        this.damage += damage;
    }

    public void draw(GraphicsContext gc) {
        switch (lifebarNumber) {
            case 1:
                gc.setFill(Color.GREEN);
                gc.fillRect((Const.CANVAS_WIDTH - (2 * Const.LIFEBAR_LENGTH) - Const.DISTANCE_BETWEEN_LIFEBARS) >> 1, 50, Const.LIFEBAR_LENGTH, 50); //Starting Point calculated dependent on canvas width
                gc.setFill(Color.RED);
                gc.fillRect((Const.CANVAS_WIDTH - (2 * Const.LIFEBAR_LENGTH) - Const.DISTANCE_BETWEEN_LIFEBARS) >> 1, 50, damage, 50);
            case 2:
                gc.setFill(Color.GREEN);
                gc.fillRect(Const.CANVAS_WIDTH - Const.LIFEBAR_LENGTH - ((Const.CANVAS_WIDTH - (2 * Const.LIFEBAR_LENGTH) - Const.DISTANCE_BETWEEN_LIFEBARS) >> 1), 50, Const.LIFEBAR_LENGTH, 50); //starting point calculated dependet on canvas length
                gc.setFill(Color.RED);
                gc.fillRect(Const.CANVAS_WIDTH - ((Const.CANVAS_WIDTH - (2 * Const.LIFEBAR_LENGTH) - Const.DISTANCE_BETWEEN_LIFEBARS) >> 1) - damage, 50, damage, 50);
        }
    }

    public boolean isKo() {
        return this.damage >= Const.LIFEBAR_LENGTH;
    }

    public double getDamage() {
        return damage;
    }
}
