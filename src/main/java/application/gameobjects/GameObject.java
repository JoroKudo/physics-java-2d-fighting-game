package application.gameobjects;

import application.constants.Const;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

public abstract class GameObject extends Body {

    private final Image image;

    public GameObject(Image image, double x, double y)  {
        this.image = image;

        this.translate(x, y);

        addFixture(new Rectangle(image.getWidth() / Const.SCALE, image.getHeight() / Const.SCALE));
    }

    public void draw(GraphicsContext gc) {
        Vector2 position = this.getTransform().getTranslation();
        gc.drawImage(image, position.x * Const.SCALE , (position.y * Const.SCALE) * (-1));
    }
}