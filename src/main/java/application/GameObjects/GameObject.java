
package application.GameObjects;


import application.constants.Const;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.dyn4j.geometry.Rectangle;

public abstract class GameObject extends GameBody {

    protected Image image;

    public GameObject(Image image, double x, double y) {
        super(image);
        this.image = image;
        this.translate(x, y);
        addFixture(new Rectangle(image.getWidth() / Const.BLOCK_SIZE, image.getHeight() / Const.BLOCK_SIZE));
    }



}