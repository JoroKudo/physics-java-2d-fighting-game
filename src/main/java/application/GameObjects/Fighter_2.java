package application.GameObjects;


import application.constants.Images;

import application.common.KeyEventHandler;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.MassType;

import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;

public class Fighter_2 extends BasePlayer {
    private final World<Body> physicWorld;


    public Fighter_2(double x, double y, World<Body> physicWorld, KeyEventHandler keyEventHandler) {
        super(Images.fighter_look_left, x, y,keyEventHandler);
        this.physicWorld = physicWorld;
        setMass(MassType.FIXED_ANGULAR_VELOCITY);



    }


    public void handleNavigationEvents(double elapsedTime) {
        punch("P",elapsedTime);
        duck("K");

        if (keyEventHandler.isKeyPressed("L"))
            walkRight();
        if (keyEventHandler.isKeyPressed("J"))
            walkLeft();
        if (keyEventHandler.isKeyPressed("I"))
            jump(isOnGround());
        if (keyEventHandler.isKeyPressed("U"))
            block();
        if (keyEventHandler.isKeyPressed("L"))
            walkRight();
        if (keyEventHandler.isKeyPressed("M")) {

            if (cooldown <= 0) {

                hadoukenShoot(elapsedTime);
                if (animcooldown <= 0) {
                    createHadouken(elapsedTime,physicWorld);
                }
            } else {
                hadoukenCharge(elapsedTime);

            }
        }
        if (!keyEventHandler.isKeyPressed("J") && !keyEventHandler.isKeyPressed("L") && !keyEventHandler.isKeyPressed("I") && !keyEventHandler.isKeyPressed("O") && !keyEventHandler.isKeyPressed("K") && !keyEventHandler.isKeyPressed("U") && !keyEventHandler.isKeyPressed("M"))
            this.image = Images.fighter_look_left;
        if ((!keyEventHandler.isKeyPressed("L") && isOnGround()) && (!keyEventHandler.isKeyPressed("J") && isOnGround())) {
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
