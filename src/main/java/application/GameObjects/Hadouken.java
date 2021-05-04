package application.GameObjects;

import application.constants.Images;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;
import org.dyn4j.world.World;

public class Hadouken extends GameObject {


    private int speed = 10;
    public BasePlayer owner;


    public Hadouken(double x, double y, BasePlayer owner) {
        super(Images.hadouken,x+1.8,y);
        this.owner=owner;
        this.setGravityScale(0);
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
    }

    public void update() {
        setLinearVelocity(speed, getLinearVelocity().y);


    }




}
