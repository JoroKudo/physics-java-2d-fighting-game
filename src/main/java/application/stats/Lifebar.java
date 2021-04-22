package application.stats;

import application.constants.Const;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Lifebar {

    private Integer LifebarNumber;
    private int damage = 0;
    private boolean ko = false;

    public Lifebar(Integer lifebarNumber) {
        LifebarNumber = lifebarNumber;
    }

    public void update(int damage) {
        this.damage += damage;
        if (this.damage >= Const.LIFEBAR_LENGTH) {
            ko = true;
        }
    }

    public void draw(GraphicsContext gc) {
        switch (LifebarNumber){
            case 1:
                gc.setFill(Color.GREEN);
                gc.fillRect((Const.CANVAS_WIDTH-(2*Const.LIFEBAR_LENGTH)-Const.DISTANCE_BETWEEN_LIFEBAR)/2, 50, Const.LIFEBAR_LENGTH, 50); //Starting Point calculated dependent on canvas width
                gc.setFill(Color.RED);
                gc.fillRect((Const.CANVAS_WIDTH-(2*Const.LIFEBAR_LENGTH)-Const.DISTANCE_BETWEEN_LIFEBAR)/2, 50, damage, 50);
            case 2:
                gc.setFill(Color.GREEN);
                gc.fillRect(Const.CANVAS_WIDTH-Const.LIFEBAR_LENGTH-((Const.CANVAS_WIDTH-(2*Const.LIFEBAR_LENGTH)-Const.DISTANCE_BETWEEN_LIFEBAR)/2), 50, Const.LIFEBAR_LENGTH, 50); //starting point calculated dependet on canvas length
                gc.setFill(Color.RED);
                gc.fillRect(Const.CANVAS_WIDTH-(Const.CANVAS_WIDTH-(2*Const.LIFEBAR_LENGTH)-Const.DISTANCE_BETWEEN_LIFEBAR)/2-damage, 50, damage, 50);
        }
    }

    public boolean getKO() {
        return ko;
    }
}