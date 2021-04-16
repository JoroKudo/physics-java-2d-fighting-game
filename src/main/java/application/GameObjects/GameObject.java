
package application.GameObjects;


import application.constants.Const;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import java.util.List;

public abstract class GameObject extends Body {

    protected Image image;

    public GameObject(Image image, double x, double y) {
        this.image = image;
        this.translate(x, y);
        addFixture(new Rectangle(image.getWidth() / Const.BLOCK_SIZE, image.getHeight() / Const.BLOCK_SIZE));
    }

    public void draw(GraphicsContext gc) {

        Affine originTrans = gc.getTransform();
        this.drawHitboxes(this.getFixtures(),gc);
        Affine transform = new Affine();
        transform.appendTranslation(this.transform.getTranslationX() * Const.BLOCK_SIZE, this.transform.getTranslationY() * Const.BLOCK_SIZE);
        transform.appendRotation(this.transform.getRotation().toDegrees());
        transform.appendTranslation(1, -1);
        gc.transform(transform);

        Polygon rect = (Polygon) this.getFixture(0).getShape();
        double x = rect.getVertices()[0].x;
        double y = rect.getVertices()[0].y;

        gc.drawImage(image, x * Const.BLOCK_SIZE, y * Const.BLOCK_SIZE);
        gc.setTransform(originTrans);
    }

    private void drawHitboxes(List<BodyFixture> fixtures, GraphicsContext gc) {

        Affine originTrans = gc.getTransform();

        Affine transform = new Affine();
        transform.appendTranslation(this.transform.getTranslationX() * Const.BLOCK_SIZE, this.transform.getTranslationY() * Const.BLOCK_SIZE);
        transform.appendRotation(this.transform.getRotation().toDegrees());
        transform.appendTranslation(1, -1);
        gc.transform(transform);



        for (BodyFixture fixture : fixtures) {
            Rectangle rectangle = (Rectangle) fixture.getShape();
         //   rectangle.getVertices()[0];

            Polygon polygon = (Polygon) fixture.getShape();
            var vertices = polygon.getVertices();
            var firstPoint = vertices[0];
            var secondPoint = vertices[1];
            var thirdPoint = vertices[2];
            var fourthPoint = vertices[3];
            gc.strokeLine(firstPoint.x * Const.BLOCK_SIZE, firstPoint.y * Const.BLOCK_SIZE, secondPoint.x * Const.BLOCK_SIZE, secondPoint.y * Const.BLOCK_SIZE);
            gc.strokeLine(secondPoint.x * Const.BLOCK_SIZE, secondPoint.y * Const.BLOCK_SIZE, thirdPoint.x * Const.BLOCK_SIZE, thirdPoint.y * Const.BLOCK_SIZE);
            gc.strokeLine(thirdPoint.x * Const.BLOCK_SIZE, thirdPoint.y * Const.BLOCK_SIZE, fourthPoint.x * Const.BLOCK_SIZE, fourthPoint.y * Const.BLOCK_SIZE);
            gc.strokeLine(fourthPoint.x * Const.BLOCK_SIZE, fourthPoint.y * Const.BLOCK_SIZE, firstPoint.x * Const.BLOCK_SIZE, firstPoint.y * Const.BLOCK_SIZE);

        }
        gc.setTransform(originTrans);


    }
    public double currentXPosition(){
        return this.getWorldCenter().x;
    }


}