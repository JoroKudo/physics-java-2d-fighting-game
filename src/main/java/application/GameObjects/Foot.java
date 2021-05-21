package application.GameObjects;

import application.constants.Images;
import org.dyn4j.geometry.MassType;

public class Foot extends GameObject {

    public Foot(double x, double y) {
        super(Images.foot_hitbox, x, y);
        setMass(MassType.NORMAL);
    }
}
