package application.GameObjects;

import application.constants.Images;
import org.dyn4j.geometry.MassType;


public class Hadouken extends GameObject {


    private final int speed ;
    public BasePlayer owner;


    public Hadouken(double x, double y, BasePlayer owner,int speed) {
        super(Images.hadouken,x+1.8,y);
        this.owner=owner;
        this.speed = speed;
        this.setGravityScale(0);
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
    }

    public void update() {
        setLinearVelocity(speed, getLinearVelocity().y);


    }




}
