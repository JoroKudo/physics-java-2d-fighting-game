package application.GameObjects;

import application.Sound.SoundEffectType;
import application.Sound.Sound;
import application.common.ActionType;
import application.common.Controller;
import application.constants.Const;
import application.constants.Images;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.dyn4j.collision.Fixture;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;

public class BasePlayer extends GameBody {

    private final World<Body> physicWorld;
    public Fist fist;
    public Hadouken hadouken;
    public Foot foot;
    public WeldJoint punchshould;
    public WeldJoint punchfoot;
    protected boolean p = true;
    protected boolean d = true;
    public boolean isblocking = false;
    protected double cooldown = 5;
    protected double animcooldown = 0.3;
    protected double punchcooldown = 0;
    protected boolean doesFighterNeedsToReturnHadouken = false;
    protected Direction currentDirect = Direction.RIGHT;
    protected int dirdecider = 1;
    private String[] keys;
    public int id;
    private int i = 0;
    private Controller controller;

    public BasePlayer(int id, double x, double y, Controller controller, World<Body> physicWorld) {
        super(Images.fighter_look_right);
        this.controller = controller;
        this.translate(x, y);
        this.keys = keys;

        this.physicWorld = physicWorld;
        this.id = id;


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

        fist = new Fist(x, y + 2, controller);
        foot = new Foot(x, y + 4.23, controller);

        punchshould = new WeldJoint<Body>(this, fist, new Vector2(x, y));
        punchfoot = new WeldJoint<Body>(this, foot, new Vector2(x, y + 4.23));
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
    }

    public void handleNavigationEvents(double elapsedTime) {
        act(controller.FighterXisActing(id), elapsedTime);

/*
        if (keyEventHandler.pressedKeys.size() > i) {
            if (!Arrays.asList(keys).contains(keyEventHandler.pressedKeys.get(i).getChar())) {
                i++;
            } else {
                i = 0;
            }

        }
        if (i >= keyEventHandler.pressedKeys.size()) {
            i = 0;
            if (currentDirect == Direction.RIGHT) {
                this.image = Images.fighter_look_right;
            } else {
                this.image = Images.fighter_look_left;
            }
        }
*/
    }

    @Override
    public void drawimage(Image image, double x, double y, GraphicsContext gc) {
        gc.drawImage(image, x * Const.BLOCK_SIZE, y - 0.48 * Const.BLOCK_SIZE);
    }

    protected void createHadouken() {

        Sound.play(SoundEffectType.HADOUKEN);
        cooldown = 5;
        hadouken = new Hadouken(this.getWorldCenter().x + (this.dirdecider), this.getWorldCenter().y, this, 10 * (this.dirdecider));
        image = Images.shootright;
        physicWorld.addBody(hadouken);
        doesFighterNeedsToReturnHadouken = true;
    }

    protected void hadoukenCharge(double elapsedTime) {
        Sound.play(SoundEffectType.CHARGEUP);


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

    public void act(ActionType actionType, double elapsedTime) {
        punch(elapsedTime);
        if ((actionType != ActionType.WALK_lEFT && actionType != ActionType.WALK_RIGHT) && isOnGround()) {
            applyImpulse(new Vector2(-2 * getLinearVelocity().x, 0));
        }
        if (actionType != null) {


            switch (actionType) {
                case JUMP:
                    this.jump();
                    break;
                case DUCK:
                    this.duck();
                    break;
                case WALK_lEFT:
                    this.walkLeft();
                    break;
                case WALK_RIGHT:
                    this.walkRight();
                    break;
                case BLOCK:
                    this.block();
                    break;
                case PUNCH:
                    break;
                case HADOUKEN:
                    if (cooldown <= 0) {
                        hadoukenShoot(elapsedTime);
                        if (animcooldown <= 0) {
                            this.createHadouken();
                        }
                    } else {
                        this.hadoukenCharge(elapsedTime);
                    }
                    break;

            }
        }else{
            if (currentDirect == Direction.RIGHT) {
                this.image = Images.fighter_look_right;
            } else {
                this.image = Images.fighter_look_left;
            }
        }


    }


    protected void duck() {
        applyImpulse(new Vector2(0, 100));
        if (d) {
            this.image = Images.duck_right;
            this.getFixture(1).getShape().translate(0, 1);
            this.getFixture(2).getShape().translate(0, 1);
            this.getFixture(3).getShape().translate(0, 1);
            d = false;
        }


        if (!d) {
            this.getFixture(1).getShape().translate(0, -1);
            this.getFixture(2).getShape().translate(0, -1);
            this.getFixture(3).getShape().translate(0, -1);
            d = true;
        }
    }

    public void jump() {
        if (this.isOnGround()) {
            this.image = Images.jump_right;
            applyImpulse(new Vector2(0, -150));

        }
    }

    public void walkLeft() {
        this.currentDirect = Direction.LEFT;
        setLinearVelocity(-7, getLinearVelocity().y);
        this.image = Images.fighter_walk_left;
    }

    public void walkRight() {
        this.currentDirect = Direction.RIGHT;

        setLinearVelocity(7, getLinearVelocity().y);
        this.image = Images.fighter_walk_right;
    }

    public void punch(double elapsedTime) {

        if (controller.FighterXisActing(id) == ActionType.PUNCH && punchcooldown > 0 && p) {

            this.image = Images.punch_right;
            this.fist.getFixture(0).getShape().translate(2 * (this.dirdecider), 0);
            p = false;

        } else {
            punchcooldown += 10 * elapsedTime;

            if (!p && punchcooldown > 3) {
                this.fist.getFixture(0).getShape().translate(-2 * (this.dirdecider), 0);
                p = true;
                punchcooldown = -2.5;

            }
        }
    }


    public void block() {
        isblocking = true;
        this.image = Images.block;

    }

    public void dirupdate() {
        if (this.currentDirect == Direction.LEFT) {
            this.dirdecider = -1;

        } else {
            this.dirdecider = 1;
        }
    }

    public boolean isOnGround() {
        for (Body body : physicWorld.getBodies()) {
            if (physicWorld.isInContact(this.foot, body)) {
                if (!(body instanceof Fist)) {
                    return true;
                }
            }
        }
        return false;
    }
}
