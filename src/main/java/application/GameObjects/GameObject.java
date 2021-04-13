package application.GameObjects;

import application.game.Const;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Rectangle;

public abstract class GameObject extends Body {

    protected Image image;

    public GameObject(Image image, double x, double y) {
        this.image = image;
        this.translate(x, y);
        addFixture(new Rectangle(image.getWidth() / Const.BLOCK_SIZE, image.getHeight() / Const.BLOCK_SIZE));
    }

    public void draw(GraphicsContext gc) {
        Affine originTrans = gc.getTransform();
        Affine transform = new Affine();
        transform.appendTranslation(this.transform.getTranslationX() * Const.BLOCK_SIZE, this.transform.getTranslationY() * Const.BLOCK_SIZE);
        transform.appendRotation(this.transform.getRotation().toDegrees());
        transform.appendTranslation(1, -1);
        gc.transform(transform);

        Polygon rect = (Polygon) this.getFixture(0).getShape();
        double x = rect.getVertices()[0].x + 0.5;
        double y = rect.getVertices()[0].y + 0.5;

        gc.drawImage(image, x * Const.BLOCK_SIZE, y * Const.BLOCK_SIZE);
        gc.setTransform(originTrans);
    }
}