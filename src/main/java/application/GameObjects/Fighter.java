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


    public Fighter(double x, double y, World<Body> physicWorld, KeyEventHandler keyEventHandler) {
        super(Images.fighter_look_right, x, y,keyEventHandler);
        this.physicWorld = physicWorld;
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
        fist = new Fist(x, y, keyEventHandler);
        foot = new Foot(x, y + 4.23, keyEventHandler);

        punchshould = new WeldJoint<Body>(this, fist, new Vector2(x, y));
        punchfoot = new WeldJoint<Body>(this, foot, new Vector2(x, y + 2.23));


    }


    public void handleNavigationEvents(double elapsedTime) {
        punch("E",elapsedTime);
        duck("S");
        block("Q");

        if (keyEventHandler.isKeyPressed("D"))
            walkRight();
        if (keyEventHandler.isKeyPressed("A"))
            walkLeft();
        if (keyEventHandler.isKeyPressed("W"))
            jump(isOnGround());


        if (keyEventHandler.isKeyPressed("D"))
            walkRight();
        if (keyEventHandler.isKeyPressed("V")) {

            if (cooldown <= 0) {

                hadoukenShoot(elapsedTime);
                if (animcooldown <= 0) {
                    createHadouken(elapsedTime,physicWorld);
                }
            } else {
                hadoukenCharge(elapsedTime);

            }
        }
        if (!keyEventHandler.isKeyPressed("A") && !keyEventHandler.isKeyPressed("D") && !keyEventHandler.isKeyPressed("W") && !keyEventHandler.isKeyPressed("E") && !keyEventHandler.isKeyPressed("S") && !keyEventHandler.isKeyPressed("Q") && !keyEventHandler.isKeyPressed("V"))
            this.image = Images.fighter_look_right;
        if ((!keyEventHandler.isKeyPressed("D") && isOnGround()) && (!keyEventHandler.isKeyPressed("A") && isOnGround())) {
            applyImpulse(new Vector2(-2 * getLinearVelocity().x, 0));
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
