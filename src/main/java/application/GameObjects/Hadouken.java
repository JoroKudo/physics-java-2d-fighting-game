package application.GameObjects;

import application.common.KeyEventHandler;
import application.constants.Images;
import application.main.Game;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;

public class Hadouken extends GameObject {

    private final World<Body> physicWorld;
    private final KeyEventHandler keyEventHandler;

    public Hadouken(double x, double y, World<Body> physicWorld, KeyEventHandler keyEventHandler) {
        super(Images.hadouken, x, y);
        this.physicWorld = physicWorld;
        this.keyEventHandler = keyEventHandler;
        setMass(MassType.FIXED_LINEAR_VELOCITY);
    }

    public void update() {
        setLinearVelocity(4, getLinearVelocity().y);
    }
}
