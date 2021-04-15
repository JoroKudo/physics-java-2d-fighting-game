package application.GameObjects;


import application.common.KeyEventHandler;
import application.constants.Images;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;

public class Fighter_2 extends GameObject {
    private final World<Body> physicWorld;
    private final KeyEventHandler keyEventHandler;
    public Fighter_2(double x, double y, World<Body> physicWorld, KeyEventHandler keyEventHandler) {
        super(Images.fighter_look_right, x, y);
        this.physicWorld = physicWorld;
        this.keyEventHandler = keyEventHandler;
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
    }

    public void handleNavigationEvents() {
        if (keyEventHandler.isKeyPressed("L"))
            walkRight();
        if (keyEventHandler.isKeyPressed("J"))
            walkLeft();
        if (keyEventHandler.isKeyPressed("I"))
            jump();
        if (keyEventHandler.isKeyPressed("O"))
            punch();
        if (!keyEventHandler.isKeyPressed("J") && !keyEventHandler.isKeyPressed("L") && !keyEventHandler.isKeyPressed("I") && !keyEventHandler.isKeyPressed("O"))
            this.image = Images.fighter_look_right;
        if ((!keyEventHandler.isKeyPressed("L") && isOnGround()) && (!keyEventHandler.isKeyPressed("J") && isOnGround())) {
            setLinearVelocity(0, getLinearVelocity().y);
        }
    }

    private void jump() {
        if (isOnGround()) {
        this.applyImpulse(new Vector2(0,-60));
        this.image = Images.jump_right;}
    }

    public void walkLeft() {
        setLinearVelocity(-4, getLinearVelocity().y);
        this.image = Images.fighter_Bwalk_right;
    }

    public void walkRight() {
        setLinearVelocity(4, getLinearVelocity().y);
        this.image = Images.fighter_walk_right;
    }

    public void punch() {
        this.image = Images.punch_right;
    }

    public boolean isOnGround() {
        for (Body body : physicWorld.getBodies()) {
            if (physicWorld.isInContact(this, body)) {
                if (!(body instanceof Fighter_2)) {

                    return true;
                }
            }
        }
        return false;
    }

}
