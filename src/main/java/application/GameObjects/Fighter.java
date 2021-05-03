package application.GameObjects;


import application.constants.Images;

import application.common.KeyEventHandler;
import application.main.Game;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.MassType;

import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;

public class Fighter extends BasePlayer {
    private final World<Body> physicWorld;
    private final KeyEventHandler keyEventHandler;
    public Fist fist;
    private Hadouken hadouken;
    public Foot foot;
    public WeldJoint punchshould;
    public WeldJoint punchfoot;
    private boolean p = true;
    private boolean d = true;
    private double cooldown = 1;
    private double punchcooldown = 0;
    private boolean doesFighterNeedsToReturnHadouken = false;

    public Fighter(double x, double y, World<Body> physicWorld, KeyEventHandler keyEventHandler) {
        super(Images.fighter_look_right, x, y);
        this.physicWorld = physicWorld;
        this.keyEventHandler = keyEventHandler;
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
        fist = new Fist(x, y, keyEventHandler);
        foot = new Foot(x, y + 2.23, keyEventHandler);

        punchshould = new WeldJoint<Body>(this, fist, new Vector2(x, y));
        punchfoot = new WeldJoint<Body>(this, foot, new Vector2(x, y + 2.23));


    }


    public void handleNavigationEvents(double elapsedTime) {
        punch(elapsedTime);
        duck();
        cooldown -= elapsedTime;
        if (keyEventHandler.isKeyPressed("D"))
            walkRight();
        if (keyEventHandler.isKeyPressed("A"))
            walkLeft();
        if (keyEventHandler.isKeyPressed("W"))
            jump();
        if (keyEventHandler.isKeyPressed("Q"))
            block();
        if (keyEventHandler.isKeyPressed("D"))
            walkRight();
        if (keyEventHandler.isKeyPressed("V")) {
            if (cooldown <= 0)
                createHadouken();
        }
        if (!keyEventHandler.isKeyPressed("A") && !keyEventHandler.isKeyPressed("D") && !keyEventHandler.isKeyPressed("W") && !keyEventHandler.isKeyPressed("E") && !keyEventHandler.isKeyPressed("S") && !keyEventHandler.isKeyPressed("Q"))
            this.image = Images.fighter_look_right;
        if ((!keyEventHandler.isKeyPressed("D") && isOnGround()) && (!keyEventHandler.isKeyPressed("A") && isOnGround())) {
            applyImpulse(new Vector2(-2 * getLinearVelocity().x, 0));
        }
    }

    private void createHadouken() {
        cooldown = 1;
        //hadouken = new Hadouken(this.getWorldCenter().x, this.getWorldCenter().y, physicWorld);
        hadouken=null;
        physicWorld.addBody(hadouken);
        doesFighterNeedsToReturnHadouken = true;
    }

    public boolean isDoesFighterNeedsToReturnHadouken() {
        return doesFighterNeedsToReturnHadouken;
    }

    public Hadouken getHadouken() {
        doesFighterNeedsToReturnHadouken = false;
        return this.hadouken;
    }

    private void duck() {
        if (keyEventHandler.isKeyPressed("S")) {
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


    public void jump() {
        if (isOnGround()) {
            this.image = Images.jump_right;
            applyImpulse(new Vector2(0, -100));

        }
    }


    public void walkLeft() {
        setLinearVelocity(-4, getLinearVelocity().y);
        this.image = Images.fighter_Bwalk_right;
    }

    public void walkRight() {
        setLinearVelocity(4, getLinearVelocity().y);
        this.image = Images.fighter_walk_right;
    }

    public void punch(double elapsedTime) {

        if (keyEventHandler.isKeyPressed("E") && punchcooldown > 0 && p) {
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

    public void block() {
        this.image = Images.block;
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
