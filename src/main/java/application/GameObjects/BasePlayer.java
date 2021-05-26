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
    public Hadoken hadoken;
    public Foot foot;
    public WeldJoint<?> punchshould;
    public WeldJoint<?> punchfoot;
    protected boolean p = true;
    protected boolean d = true;
    public boolean isblocking = false;
    protected double cooldown = 5;
    protected double animcooldown = 0.3;
    protected double punchcooldown = 0;
    protected boolean doesFighterNeedsToReturnHadouken = false;
    protected Direction currentDirect = Direction.RIGHT;

    public int id;
    private final Controller controller;
    private boolean soundcanplay = true;

    public BasePlayer(int id, double x, double y, Controller controller, World<Body> physicWorld) {
        super(Images.fighter_look_right);
        this.controller = controller;
        this.translate(x, y);
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

        fist = new Fist(x, y+1);
        foot = new Foot(x, y + 4.23);

        punchshould = new WeldJoint<Body>(this, fist, new Vector2(x, y));
        punchfoot = new WeldJoint<Body>(this, foot, new Vector2(x, y + 4.23));
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
    }

    public void handleNavigationEvents(double elapsedTime) {
        act(controller.FighterXisActing(id), elapsedTime);

    }

    @Override
    public void drawimage(Image image, double x, double y, GraphicsContext gc) {
        if (currentDirect == Direction.LEFT && controller.FighterXisActing(id) == ActionType.PUNCH) {
            gc.drawImage(image, x-(72 / Const.BLOCK_SIZE * 2) * Const.BLOCK_SIZE, y - 0.48 * Const.BLOCK_SIZE);

        } else {
            gc.drawImage(image, x * Const.BLOCK_SIZE, y - 0.48 * Const.BLOCK_SIZE);

        }



    }

    private void createHadouken() {

        Sound.play(SoundEffectType.HADOUKEN);
        cooldown = 5;
        if (currentDirect == Direction.RIGHT) {
            hadoken = new Hadoken(this.getWorldCenter().x + (dirupdate()), this.getWorldCenter().y, this, 10 * (dirupdate()),Images.hadouken_right);
        } else {
            hadoken = new Hadoken(this.getWorldCenter().x + (dirupdate()), this.getWorldCenter().y, this, 10 * (dirupdate()),Images.hadouken_left);
        }

        image = Images.shoot_right;
        physicWorld.addBody(hadoken);
        doesFighterNeedsToReturnHadouken = true;
        soundcanplay = true;
    }

    private void hadoukenCharge(double elapsedTime) {
        if (soundcanplay) {
            Sound.play(SoundEffectType.CHARGEUP);
            soundcanplay = false;
        }


        if (currentDirect == Direction.RIGHT) {
            this.image = Images.charge_right;
        } else {
            this.image = Images.charge_left;
        }
        cooldown -= elapsedTime;
    }

    private void hadoukenShoot(double elapsedTime) {

        if (currentDirect == Direction.RIGHT) {
            this.image = Images.shoot_right;
        } else {
            this.image = Images.shoot_left;
        }

        animcooldown -= elapsedTime;
    }

    public boolean isDoesFighterNeedsToReturnHadouken() {
        return doesFighterNeedsToReturnHadouken;
    }

    public Hadoken getHadouken() {
        doesFighterNeedsToReturnHadouken = false;
        return this.hadoken;
    }

    private void act(ActionType actionType, double elapsedTime) {
        if (actionType != ActionType.HADOKEN) {
            soundcanplay = true;
        }
        punch(elapsedTime);
        duck();
        if ((actionType != ActionType.WALK_lEFT && actionType != ActionType.WALK_RIGHT) && isOnGround()) {
            applyImpulse(new Vector2(-2 * getLinearVelocity().x, 0));
        }
        if (actionType != null) {


            switch (actionType) {
                case JUMP:
                    this.jump();
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

                case HADOKEN:
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
        } else {
            if (currentDirect == Direction.RIGHT) {
                this.image = Images.fighter_look_right;
            } else {
                this.image = Images.fighter_look_left;
            }
        }


    }


    private void duck() {

        if (controller.FighterXisActing(id) == ActionType.DUCK) {
            if (currentDirect == Direction.RIGHT) {
                this.image = Images.duck_right;
            } else {
                this.image = Images.duck_left;
            }
            applyImpulse(new Vector2(0, 100));

            if (d) {

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


    private void jump() {
        if (this.isOnGround()) {
            if (currentDirect == Direction.RIGHT) {
                this.image = Images.jump_right;
            } else {
                this.image = Images.jump_left;
            }
            applyImpulse(new Vector2(0, -150));

        }
    }

    private void walkLeft() {
        this.currentDirect = Direction.LEFT;
        setLinearVelocity(-7, getLinearVelocity().y);
        this.image = Images.fighter_walk_left;
    }

    private void walkRight() {
        this.currentDirect = Direction.RIGHT;

        setLinearVelocity(7, getLinearVelocity().y);
        this.image = Images.fighter_walk_right;
    }

    private void punch(double elapsedTime) {

        if (controller.FighterXisActing(id) == ActionType.PUNCH && punchcooldown > 0 && p) {

            if (currentDirect == Direction.RIGHT) {
                this.image = Images.punch_right;
            } else {
                this.image = Images.punch_left;
            }
            this.fist.getFixture(0).getShape().translate(2 * (dirupdate()), 0);
            p = false;

        } else {
            punchcooldown += 10 * elapsedTime;

            if (!p && punchcooldown > 3) {
                this.fist.getFixture(0).getShape().translate(-2 * (dirupdate()), 0);
                p = true;
                punchcooldown = -2.5;

            }
        }
    }


    private void block() {
        isblocking = true;
        if (currentDirect == Direction.RIGHT) {
            this.image = Images.block_right;
        } else {
            this.image = Images.block_left;
        }


    }

    public int dirupdate() {
        if (this.currentDirect == Direction.LEFT) {
            return -1;

        } else {
            return 1;
        }
    }

    private boolean isOnGround() {
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
