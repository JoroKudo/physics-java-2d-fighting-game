package application.GameObjects;

import application.constants.Images;
import org.dyn4j.geometry.MassType;


public class Wall extends GameObject {
    public Wall(double x, double y) {
        super(Images.wall, x, y);
        setMass(MassType.INFINITE);
    }
}
