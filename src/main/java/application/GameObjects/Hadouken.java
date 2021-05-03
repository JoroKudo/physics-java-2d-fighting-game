package application.GameObjects;

import application.constants.Images;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;
import org.dyn4j.world.World;

public class Hadouken extends GameObject {

    private final World<Body> physicWorld;

    public Hadouken(double x, double y, World<Body> physicWorld) {
        super(Images.hadouken,x+1.8,y);
        this.physicWorld = physicWorld;
        this.setGravityScale(0);
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
    }

    public void update() {
        setLinearVelocity(10, getLinearVelocity().y);
    }
}
