package application.GameObjects;

import application.constants.Images;
import javafx.scene.image.Image;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;
import org.dyn4j.world.World;

public class Hadouken extends GameObject {

    private final World<Body> physicWorld;
    private int speed = 10;
    private Image img =Images.hadouken;

    public Hadouken(double x, double y, World<Body> physicWorld) {
        super(Images.hadouken,x+1.8,y);
        this.physicWorld = physicWorld;
        this.setGravityScale(0);
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
    }

    public void update() {
        setLinearVelocity(speed, getLinearVelocity().y);

        this.image =Images.expolsion;
    }

    public void explode() {

        setMass(MassType.INFINITE);


        speed = 0;
        img = Images.expolsion;

    }

}
