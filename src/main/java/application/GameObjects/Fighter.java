package application.GameObjects;


import application.game.Images;

import application.game.KeyEventHandler;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;

import org.dyn4j.world.World;

public class Fighter extends GameObject {
    private final World<Body> physicWorld;
    private Direction currentDirect = Direction.RIGHT;
    private final KeyEventHandler keyEventHandler;

    public Fighter(double x, double y, World<Body> physicWorld, KeyEventHandler keyEventHandler) {
        super(Images.fighter_standing, x, y);
        this.physicWorld = physicWorld;
        this.keyEventHandler = keyEventHandler;

        setMass(MassType.NORMAL);
    }

    public void handleNavigationEvents() {

        if (keyEventHandler.isRightKeyPressed())
            walkRight();
        if (keyEventHandler.isLeftKeyPressed())
            walkLeft();
    }

    public void walkLeft() {
        currentDirect = Direction.LEFT;
        setLinearVelocity(-4, getLinearVelocity().y);
    }

    public void walkRight() {

        currentDirect = Direction.RIGHT;
        setLinearVelocity(4, getLinearVelocity().y);
    }


}
