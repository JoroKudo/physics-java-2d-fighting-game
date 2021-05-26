package application.gameObjects;

import application.sound.SoundEffectType;
import application.sound.Sound;
import application.controller.ActionType;
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
    private Fist fist;
    private Hadoken hadoken;
    private Foot foot;
    private WeldJoint<?> punchTarget;
    private WeldJoint<?> footHitbox;
    private boolean punch = true;
    private boolean duck = true;
    private boolean blocking = false;
    private double cooldown = 2;
    private double animationCooldown = 0.3;
    private double punchCooldown = 0;
    private boolean returnHadoken = false;
    private Direction currentDirection = Direction.RIGHT;
    private int id;
    private final Controller controller;
    private boolean playSound = true;

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

        punchTarget = new WeldJoint<Body>(this, fist, new Vector2(x, y));
        footHitbox = new WeldJoint<Body>(this, foot, new Vector2(x, y + 4.23));
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
    }

    public void handleNavigationEvents(double elapsedTime) {
        act(controller.FighterXisActing(id), elapsedTime);
    }

    @Override
    public void drawImage(Image image, double x, double y, GraphicsContext gc) {
        if (currentDirection == Direction.LEFT && controller.FighterXisActing(id) == ActionType.PUNCH) {
            gc.drawImage(image, x-(72 / Const.BLOCK_SIZE * 2) * Const.BLOCK_SIZE, y - 0.48 * Const.BLOCK_SIZE);
        } else {
            gc.drawImage(image, x * Const.BLOCK_SIZE, y - 0.48 * Const.BLOCK_SIZE);
        }

    }

    private void createHadoken() {
        Sound.play(SoundEffectType.HADOKEN);
        cooldown = 2;
        if (currentDirection == Direction.RIGHT) {
            hadoken = new Hadoken(this.getWorldCenter().x + (updateDirection()), this.getWorldCenter().y, this, 10 * (updateDirection()),Images.hadouken_right);
        } else {
            hadoken = new Hadoken(this.getWorldCenter().x + (updateDirection()), this.getWorldCenter().y, this, 10 * (updateDirection()),Images.hadouken_left);
        }

        image = Images.shoot_right;
        physicWorld.addBody(hadoken);
        returnHadoken = true;
        playSound = true;
    }

    private void hadokenCharge(double elapsedTime) {
        if (playSound) {
            Sound.play(SoundEffectType.CHARGE_UP);
            playSound = false;
        }
        if (currentDirection == Direction.RIGHT) {
            this.image = Images.charge_right;
        } else {
            this.image = Images.charge_left;
        }
        cooldown -= elapsedTime;
    }

    private void hadokenShoot(double elapsedTime) {

        if (currentDirection == Direction.RIGHT) {
            this.image = Images.shoot_right;
        } else {
            this.image = Images.shoot_left;
        }

        animationCooldown -= elapsedTime;
    }

    public boolean isReturnHadoken() {
        return returnHadoken;
    }

    public Hadoken getHadoken() {
        returnHadoken = false;
        return this.hadoken;
    }

    private void act(ActionType actionType, double elapsedTime) {
        if (actionType != ActionType.HADOKEN) {
            playSound = true;
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
                        hadokenShoot(elapsedTime);
                        if (animationCooldown <= 0) {
                            this.createHadoken();
                        }
                    } else {
                        this.hadokenCharge(elapsedTime);
                    }
                    break;
            }
        } else {
            if (currentDirection == Direction.RIGHT) {
                this.image = Images.fighter_look_right;
            } else {
                this.image = Images.fighter_look_left;
            }
        }
    }


    private void duck() {
        if (controller.FighterXisActing(id) == ActionType.DUCK) {
            if (currentDirection == Direction.RIGHT) {
                this.image = Images.duck_right;
            } else {
                this.image = Images.duck_left;
            }
            applyImpulse(new Vector2(0, 100));
            if (duck) {
                this.getFixture(1).getShape().translate(0, 1);
                this.getFixture(2).getShape().translate(0, 1);
                this.getFixture(3).getShape().translate(0, 1);
                duck = false;
            }


        } else if (!duck) {
            this.getFixture(1).getShape().translate(0, -1);
            this.getFixture(2).getShape().translate(0, -1);
            this.getFixture(3).getShape().translate(0, -1);
            duck = true;
        }
    }


    private void jump() {
        if (this.isOnGround()) {
            if (currentDirection == Direction.RIGHT) {
                this.image = Images.jump_right;
            } else {
                this.image = Images.jump_left;
            }
            applyImpulse(new Vector2(0, -150));

        }
    }

    private void walkLeft() {
        this.currentDirection = Direction.LEFT;
        setLinearVelocity(-7, getLinearVelocity().y);
        this.image = Images.fighter_walk_left;
    }

    private void walkRight() {
        this.currentDirection = Direction.RIGHT;

        setLinearVelocity(7, getLinearVelocity().y);
        this.image = Images.fighter_walk_right;
    }

    private void punch(double elapsedTime) {
        if (controller.FighterXisActing(id) == ActionType.PUNCH && punchCooldown > 0 && punch) {
            if (currentDirection == Direction.RIGHT) {
                this.image = Images.punch_right;
            } else {
                this.image = Images.punch_left;
            }
            this.fist.getFixture(0).getShape().translate(2 * (updateDirection()), 0);
            punch = false;

        } else {
            punchCooldown += 10 * elapsedTime;
            if (!punch && punchCooldown > 3) {
                this.fist.getFixture(0).getShape().translate(-2 * (updateDirection()), 0);
                punch = true;
                punchCooldown = -2.5;

            }
        }
    }


    private void block() {
        blocking = true;
        if (currentDirection == Direction.RIGHT) {
            this.image = Images.block_right;
        } else {
            this.image = Images.block_left;
        }


    }

    public int updateDirection() {
        if (this.currentDirection == Direction.LEFT) {
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

    public Fist getFist() {
        return fist;
    }

    public Foot getFoot() {
        return foot;
    }

    public WeldJoint<?> getPunchTarget() {
        return punchTarget;
    }

    public WeldJoint<?> getFootHitbox() {
        return footHitbox;
    }

    public boolean isBlocking() {
        return blocking;
    }

    public int getId() {
        return id;
    }
}
