package application.GameObjects;


import application.constants.Images;

import application.common.KeyEventHandler;
import application.main.Game;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.MassType;

import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;

public class Fighter_2 extends BasePlayer {
    private final World<Body> physicWorld;
    private final KeyEventHandler keyEventHandler;
    private  Game game;
    public Fist fist;
    private Hadouken hadouken;
    public Foot  foot;
    public WeldJoint punchshould;
    public WeldJoint punchfoot;
    private boolean p =true;
    private double cooldown = 1;
    private double punchcooldown = 0;
    private boolean doesFighterNeedsToReturnHadouken = false;
    public Fighter_2(double x, double y, World<Body> physicWorld, KeyEventHandler keyEventHandler) {
        super(Images.fighter_look_left, x, y);
        this.physicWorld = physicWorld;
        this.keyEventHandler = keyEventHandler;
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
        fist =  new Fist(x, y, keyEventHandler);
        foot =  new Foot(x, y+2.23, keyEventHandler);

        punchshould = new WeldJoint<Body>(this, fist, new Vector2(x, y));
        punchfoot = new WeldJoint<Body>(this, foot, new Vector2(x, y+2.23));


    }


    public void handleNavigationEvents(double elapsedTime) {

        cooldown -= 10* elapsedTime;


        punch(elapsedTime);
        if (keyEventHandler.isKeyPressed("J"))
            walkLeft();
        if (keyEventHandler.isKeyPressed("I"))
            jump();
        if (keyEventHandler.isKeyPressed("K"))
            duck();
        if (keyEventHandler.isKeyPressed("U"))
            block();
        if (keyEventHandler.isKeyPressed("L"))
            walkRight();
        if (keyEventHandler.isKeyPressed("M")) {
            if (cooldown <= 0)
                createHadouken();
        }
        if (!keyEventHandler.isKeyPressed("J") && !keyEventHandler.isKeyPressed("L") && !keyEventHandler.isKeyPressed("I") && !keyEventHandler.isKeyPressed("O") && !keyEventHandler.isKeyPressed("K") && !keyEventHandler.isKeyPressed("U"))
            this.image = Images.fighter_look_left;
        if ((!keyEventHandler.isKeyPressed("L") && isOnGround()) && (!keyEventHandler.isKeyPressed("J") && isOnGround())) {
            applyImpulse(new Vector2(-2*getLinearVelocity().x,0));
        }
    }

    private void createHadouken() {
        cooldown = 1;
        hadouken = new Hadouken(this.getWorldCenter().x, this.getWorldCenter().y, physicWorld);
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
        this.image = Images.duck_right;
        applyImpulse(new Vector2(0, 100));

    }





    public void jump() {
        if (isOnGround()) {
            this.image = Images.jump_right;
            applyImpulse(new Vector2(0, -100));

        }
    }


    public void walkLeft() {
        setLinearVelocity(-4, getLinearVelocity().y);
        this.image = Images.fighter_Bwalk_left;
    }

    public void walkRight() {
        setLinearVelocity(4, getLinearVelocity().y);
        this.image = Images.fighter_walk_left;
    }




    public void punch(double elapsedTime) {

        if (keyEventHandler.isKeyPressed("O")&&punchcooldown>0&&p) {
            this.image = Images.punch_left;
            this.fist.getFixture(0).getShape().translate(2,0);
            p=false;

        }
        else{
            punchcooldown += 10 * elapsedTime;

            if (!p&& punchcooldown>3) {
                this.fist.getFixture(0).getShape().translate(-2,0);
                p=true;
                punchcooldown=-2.5;

            }}
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
