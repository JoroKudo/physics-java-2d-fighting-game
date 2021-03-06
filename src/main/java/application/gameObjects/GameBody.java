package application.gameObjects;

import application.constants.Const;
import application.constants.Images;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Polygon;



public class GameBody extends Body {

    protected Image image;

    public GameBody(Image image) {
        this.image = image;
    }

    public void draw(GraphicsContext gc) {
        if (Const.HITBOXES) {
            this.drawHitboxes(gc);
        }
        Affine originTrans = gc.getTransform();
        Affine transform = new Affine();
        transform.appendTranslation(this.transform.getTranslationX() * Const.BLOCK_SIZE, this.transform.getTranslationY() * Const.BLOCK_SIZE);
        transform.appendRotation(this.transform.getRotation().toDegrees());
        transform.appendTranslation(1, 1);
        gc.transform(transform);
        Polygon rect = (Polygon) this.getFixture(0).getShape();
        double x = rect.getVertices()[0].x;
        double y = rect.getVertices()[0].y;
        if (image != Images.hitbox_fist || image != Images.hitbox_stomp) {
            drawImage(image, x, y, gc);
        }
        gc.setTransform(originTrans);
    }

    private void drawHitboxes(GraphicsContext gc) {

        Affine originTrans = gc.getTransform();
        Affine transform = new Affine();
        transform.appendTranslation(this.transform.getTranslationX() * Const.BLOCK_SIZE, this.transform.getTranslationY() * Const.BLOCK_SIZE);
        transform.appendRotation(this.transform.getRotation().toDegrees());
        transform.appendTranslation(1, -1);
        gc.transform(transform);
        for (BodyFixture fixture : this.getFixtures()) {
            Polygon polygon = (Polygon) fixture.getShape();
            var vertices = polygon.getVertices();
            var firstPoint = vertices[0];
            var secondPoint = vertices[1];
            var thirdPoint = vertices[2];
            var fourthPoint = vertices[3];
            double[] ypoints = {firstPoint.y * Const.BLOCK_SIZE, secondPoint.y * Const.BLOCK_SIZE, thirdPoint.y * Const.BLOCK_SIZE, fourthPoint.y * Const.BLOCK_SIZE};
            double[] xpoints = {firstPoint.x * Const.BLOCK_SIZE, secondPoint.x * Const.BLOCK_SIZE, thirdPoint.x * Const.BLOCK_SIZE, fourthPoint.x * Const.BLOCK_SIZE};
            gc.strokePolygon(xpoints, ypoints, 4);
            if (image == Images.hitbox_fist || image == Images.hitbox_stomp) {
                gc.fillPolygon(xpoints, ypoints, 4);
            }
        }
        gc.setTransform(originTrans);
    }

    protected void drawImage(Image image, double x, double y, GraphicsContext gc) {
        gc.drawImage(image, x * Const.BLOCK_SIZE, y * Const.BLOCK_SIZE);
    }
}
