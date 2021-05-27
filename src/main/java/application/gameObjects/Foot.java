package application.gameObjects;

import application.constants.Images;
import org.dyn4j.geometry.MassType;

public class Foot extends GameObject {

    public Foot(double x, double y) {
        super(Images.hitbox_stomp, x, y);
        setMass(MassType.NORMAL);
    }
}
