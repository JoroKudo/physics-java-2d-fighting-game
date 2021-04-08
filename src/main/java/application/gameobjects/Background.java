package application.gameobjects;

import application.constants.Images;
import javafx.scene.canvas.GraphicsContext;

public class Background {

    public void draw(GraphicsContext gc) {
        gc.drawImage(Images.background, 0, 0);
    }

}
