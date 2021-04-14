package application.GameObjects;


import application.game.Const;
import application.game.Images;

import application.game.KeyEventHandler;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;

import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;

public class Fighter extends GameObject {
    private final World<Body> physicWorld;
    private final KeyEventHandler keyEventHandler;
    public Fighter(double x, double y, World<Body> physicWorld, KeyEventHandler keyEventHandler) {
        super(Images.fighter_look_right, x, y);
        this.physicWorld = physicWorld;
        this.keyEventHandler = keyEventHandler;
        setMass(MassType.NORMAL);
    }

    public void handleNavigationEvents() {
        if (keyEventHandler.isDPressed())
            walkRight();
        if (keyEventHandler.isAPressed())
            walkLeft();
        if (keyEventHandler.isWPressed())
            jump();
        if (keyEventHandler.isEPressed())
            punch();
        if (keyEventHandler.isAReleased() && keyEventHandler.isDReleased() && keyEventHandler.isWReleased() && keyEventHandler.isEReleased())
            this.image = Images.fighter_look_right;
        if ((keyEventHandler.isDReleased() && isOnGround()) && (keyEventHandler.isAReleased() && isOnGround())) {
            setLinearVelocity(0, 0);
        }
    }

    private void jump() {
        this.applyImpulse(new Vector2(0,-5));
        this.image = Images.jump_right;
    }

    public void walkLeft() {
        this.applyImpulse(new Vector2(-1,0));
        this.image = Images.fighter_Bwalk_right;
    }

    public void walkRight() {
        this.applyImpulse(new Vector2(1,0));
        this.image = Images.fighter_walk_right;
    }

    public void punch() {
        this.image = Images.punch_right;
    }

    public boolean isOnGround() {
        for (Body body : physicWorld.getBodies()) {
            if (physicWorld.isInContact(this, body)) {
                if (!(body instanceof Fighter)) {
                    setLinearVelocity(getLinearVelocity().x, 0);
                    return true;
                }
            }
        }
        return false;
    }
}
