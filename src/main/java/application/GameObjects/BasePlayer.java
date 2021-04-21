package application.GameObjects;


import application.constants.Const;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import org.dyn4j.collision.Fixture;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Rectangle;

import java.util.List;

public abstract class BasePlayer extends Body {

    protected Image image;




    public BasePlayer(Image image, double x, double y) {
        this.image = image;
        this.translate(x, y);

        Fixture img = addFixture(new Rectangle(72 / Const.BLOCK_SIZE, 103 / Const.BLOCK_SIZE));
        img.getShape().translate(0, 1);

        Fixture body = addFixture(new Rectangle(50 / Const.BLOCK_SIZE, 50 / Const.BLOCK_SIZE - 13 / Const.BLOCK_SIZE));
        body.getShape().translate(0, 0.5);

        Fixture head = addFixture(new Rectangle(20 / Const.BLOCK_SIZE, 23 / Const.BLOCK_SIZE));
        head.getShape().translate(.17, 0);



    }

    public void draw(GraphicsContext gc) {

        this.drawHitboxes(this.getFixtures(), gc);
        Affine originTrans = gc.getTransform();

        Affine transform = new Affine();
        transform.appendTranslation(this.transform.getTranslationX() * Const.BLOCK_SIZE, this.transform.getTranslationY() * Const.BLOCK_SIZE);
        transform.appendRotation(this.transform.getRotation().toDegrees());
        transform.appendTranslation(1, 1);
        gc.transform(transform);

        Polygon rect = (Polygon) this.getFixture(0).getShape();
        double x = rect.getVertices()[0].x;
        double y = rect.getVertices()[0].y;
        gc.drawImage(image, x * Const.BLOCK_SIZE, y * Const.BLOCK_SIZE);
        gc.setTransform(originTrans);
    }

    public void drawHitboxes(List<BodyFixture> fixtures, GraphicsContext gc) {

        Affine originTrans = gc.getTransform();

        Affine transform = new Affine();
        transform.appendTranslation(this.transform.getTranslationX() * Const.BLOCK_SIZE, this.transform.getTranslationY() * Const.BLOCK_SIZE);
        transform.appendRotation(this.transform.getRotation().toDegrees());
        transform.appendTranslation(1, -1);
        gc.transform(transform);


        for (BodyFixture fixture : fixtures) {

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

    public double currentXPosition() {
        return this.getWorldCenter().x;
    }


}



