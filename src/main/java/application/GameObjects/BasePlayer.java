package application.GameObjects;


import application.common.KeyEventHandler;
import application.constants.Const;
import application.constants.Images;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.dyn4j.collision.Fixture;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;


public class BasePlayer extends GameBody {

    public Fist fist;
    public Hadouken hadouken;
    public Foot foot;
    public WeldJoint punchshould;
    public WeldJoint punchfoot;
    protected boolean p = true;
    protected final KeyEventHandler keyEventHandler;
    protected boolean d = true;
    public boolean isblocking = false;
    protected double cooldown = 5;
    protected double animcooldown = 0.3;
    protected double punchcooldown = 0;
    protected boolean doesFighterNeedsToReturnHadouken = false;

    public BasePlayer(Image image, double x, double y, KeyEventHandler keyEventHandler) {
        super(image);


        this.translate(x, y);
        this.keyEventHandler = keyEventHandler;


        Fixture legs = addFixture(new Rectangle(72 / Const.BLOCK_SIZE * 2, 30 / Const.BLOCK_SIZE * 2));
        legs.getShape().translate(0, 3.6);

        Fixture head = addFixture(new Rectangle(20 / Const.BLOCK_SIZE * 2, 23 / Const.BLOCK_SIZE * 2));
        head.getShape().translate(.34, 0.2);

        Fixture torso = addFixture(new Rectangle(45 / Const.BLOCK_SIZE * 2, 25 / Const.BLOCK_SIZE * 2));
        torso.getShape().translate(0, 1.4);

        Fixture body = addFixture(new Rectangle(50 / Const.BLOCK_SIZE * 2, 30 / Const.BLOCK_SIZE * 2 - 13 / Const.BLOCK_SIZE * 2));
        body.getShape().translate(0, 0.5);

        Fixture hips = addFixture(new Rectangle(40 / Const.BLOCK_SIZE * 2, 21 / Const.BLOCK_SIZE * 2));
        hips.getShape().translate(0, 2.4);

        fist = new Fist(x, y+2, keyEventHandler);
        foot = new Foot(x, y + 4.23, keyEventHandler);

        punchshould = new WeldJoint<Body>(this, fist, new Vector2(x, y));
        punchfoot = new WeldJoint<Body>(this, foot, new Vector2(x, y + 4.23));
    }

    @Override
    public void drawimage(Image image, double x, double y, GraphicsContext gc) {
        gc.drawImage(image, x * Const.BLOCK_SIZE, y - 0.48 * Const.BLOCK_SIZE);
    }

    protected void createHadouken(double elapsedTime, World physicWorld) {


        cooldown = 5;
        hadouken = new Hadouken(this.getWorldCenter().x, this.getWorldCenter().y, physicWorld);
        image = Images.shootright;
        physicWorld.addBody(hadouken);
        doesFighterNeedsToReturnHadouken = true;
    }

    protected void hadoukenCharge(double elapsedTime) {
        this.image = Images.chargeright;
        cooldown -= elapsedTime;

    }

    protected void hadoukenShoot(double elapsedTime) {
        this.image = Images.shootright;
        animcooldown -= elapsedTime;


    }

    public boolean isDoesFighterNeedsToReturnHadouken() {
        return doesFighterNeedsToReturnHadouken;
    }

    public Hadouken getHadouken() {
        doesFighterNeedsToReturnHadouken = false;
        return this.hadouken;
    }

    protected void duck(String key) {
        if (keyEventHandler.isKeyPressed(key)) {
            applyImpulse(new Vector2(0, 100));
            if (d) {
                this.image = Images.duck_right;
                this.getFixture(1).getShape().translate(0, 1);
                this.getFixture(2).getShape().translate(0, 1);
                this.getFixture(3).getShape().translate(0, 1);
                d = false;
            }


        } else if (!d) {
            this.getFixture(1).getShape().translate(0, -1);
            this.getFixture(2).getShape().translate(0, -1);
            this.getFixture(3).getShape().translate(0, -1);
            d = true;
        }


    }

    public void jump(boolean isOnGround) {
        if (isOnGround) {
            this.image = Images.jump_right;
            applyImpulse(new Vector2(0, -150));

        }
    }


    public void walkLeft() {
        setLinearVelocity(-7, getLinearVelocity().y);
        this.image = Images.fighter_Bwalk_right;
    }

    public void walkRight() {
        setLinearVelocity(7, getLinearVelocity().y);
        this.image = Images.fighter_walk_right;
    }

    public void punch(String key, double elapsedTime) {

        if (keyEventHandler.isKeyPressed(key) && punchcooldown > 0 && p) {
            this.image = Images.punch_right;
            this.fist.getFixture(0).getShape().translate(2, 0);
            p = false;

        } else {
            punchcooldown += 10 * elapsedTime;

            if (!p && punchcooldown > 3) {
                this.fist.getFixture(0).getShape().translate(-2, 0);
                p = true;
                punchcooldown = -2.5;

            }
        }
    }

    public void block(String key) {
        if (keyEventHandler.isKeyPressed(key)) {
            isblocking = true;
            this.image = Images.block;
        } else {
            isblocking = false;
        }


    }
}



