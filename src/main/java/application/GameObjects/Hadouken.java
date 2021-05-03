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
        setMass(MassType.FIXED_LINEAR_VELOCITY);
    }

    public void update() {
        setLinearVelocity(4, getLinearVelocity().y);
    }
}
