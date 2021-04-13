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
    private Direction currentDirect = Direction.RIGHT;
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
    }

    public void walkLeft() {
        currentDirect = Direction.LEFT;
        System.out.println("LEFT");
        this.applyImpulse(new Vector2(-1,0));
    }

    public void walkRight() {
        currentDirect = Direction.RIGHT;
        System.out.println("RIGHT");
        this.applyImpulse(new Vector2(1,0));
    }
}
