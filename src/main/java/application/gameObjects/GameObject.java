
package application.gameObjects;


import application.constants.Const;
import javafx.scene.image.Image;
import org.dyn4j.geometry.Rectangle;

public abstract class GameObject extends GameBody {

    public GameObject(Image image, double x, double y) {
        super(image);
        this.translate(x, y);
        addFixture(new Rectangle(image.getWidth() / Const.BLOCK_SIZE, image.getHeight() / Const.BLOCK_SIZE));
    }


}