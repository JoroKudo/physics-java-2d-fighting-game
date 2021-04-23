package application.GameObjects;


import application.constants.Const;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.dyn4j.collision.Fixture;
import org.dyn4j.geometry.Rectangle;


public  class BasePlayer extends GameBody  {

    public BasePlayer(Image image, double x, double y) {
        super(image);


        this.translate(x, y);






        Fixture legs = addFixture(new Rectangle(72 / Const.BLOCK_SIZE, 30 / Const.BLOCK_SIZE));
        legs.getShape().translate(0, 1.98);

        Fixture head = addFixture(new Rectangle(20 / Const.BLOCK_SIZE, 23 / Const.BLOCK_SIZE));
        head.getShape().translate(.17, 0);

        Fixture torso = addFixture(new Rectangle(45 / Const.BLOCK_SIZE, 25 / Const.BLOCK_SIZE));
        torso.getShape().translate(0, 0.7);

        Fixture body = addFixture(new Rectangle(50 / Const.BLOCK_SIZE, 30 / Const.BLOCK_SIZE - 13 / Const.BLOCK_SIZE));
        body.getShape().translate(0, 0.2);

        Fixture hips = addFixture(new Rectangle(40 / Const.BLOCK_SIZE, 20 / Const.BLOCK_SIZE));
        hips.getShape().translate(0, 1.3);














    }
    @Override
    public void drawimage(Image image, double x, double y, GraphicsContext gc){
        gc.drawImage(image, x  * Const.BLOCK_SIZE, y-0.48 * Const.BLOCK_SIZE);
    }






}



