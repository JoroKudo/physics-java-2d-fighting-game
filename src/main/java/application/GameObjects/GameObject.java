
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

    @Override
    public void drawimage(Image image, double x, double y, GraphicsContext gc) {
        gc.drawImage(image, x * Const.BLOCK_SIZE, y - 0.48 * Const.BLOCK_SIZE);
    }

}