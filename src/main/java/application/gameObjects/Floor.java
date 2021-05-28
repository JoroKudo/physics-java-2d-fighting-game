package application.gameObjects;

import application.constants.Images;
import org.dyn4j.geometry.MassType;

public class Floor extends GameObject {
    public Floor(double x, double y) {
        super(Images.floor, x, y);
        setMass(MassType.INFINITE);
    }
}
