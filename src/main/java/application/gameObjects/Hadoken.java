package application.gameObjects;

import javafx.scene.image.Image;
import org.dyn4j.geometry.MassType;

public class Hadoken extends GameObject {

    private final int speed;
    public BasePlayer owner;

    public Hadoken(double x, double y, BasePlayer owner, int speed, Image image) {
        super(image, x + 1.8, y);
        this.owner = owner;
        this.speed = speed;
        this.setGravityScale(0);
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
    }

    public void update() {
        setLinearVelocity(speed, getLinearVelocity().y);
    }


}
