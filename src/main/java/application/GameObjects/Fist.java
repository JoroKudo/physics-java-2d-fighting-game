package application.GameObjects;

import application.game.Images;
import org.dyn4j.geometry.MassType;

public class Fist extends GameObject {
    public Fist(double x, double y) {
        super(Images.FLOOR, x, y);
        setMass(MassType.NORMAL);
    }
}
