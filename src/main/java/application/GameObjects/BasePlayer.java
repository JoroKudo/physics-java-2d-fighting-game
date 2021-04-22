package application.GameObjects;


import application.constants.Const;
import javafx.scene.image.Image;
import org.dyn4j.collision.Fixture;
import org.dyn4j.geometry.Rectangle;


public abstract class BasePlayer extends GameBody {

    protected Image image;




    public BasePlayer(Image image, double x, double y) {
        super(image);
        this.image = image;
        this.translate(x, y);

        Fixture img = addFixture(new Rectangle(72 / Const.BLOCK_SIZE, 103 / Const.BLOCK_SIZE));
        img.getShape().translate(0, 1);

        Fixture body = addFixture(new Rectangle(50 / Const.BLOCK_SIZE, 50 / Const.BLOCK_SIZE - 13 / Const.BLOCK_SIZE));
        body.getShape().translate(0, 0.5);

        Fixture head = addFixture(new Rectangle(20 / Const.BLOCK_SIZE, 23 / Const.BLOCK_SIZE));
        head.getShape().translate(.17, 0);

        Fixture fist = addFixture(new Rectangle(20 / Const.BLOCK_SIZE, 23 / Const.BLOCK_SIZE));
        fist.getShape().translate(.17, 0);




    }






}



