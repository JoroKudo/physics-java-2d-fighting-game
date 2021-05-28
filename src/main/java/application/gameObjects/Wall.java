package application.gameObjects;

import application.constants.Images;
import org.dyn4j.geometry.MassType;


public class Wall extends GameObject {
    public Wall(double x) {
        super(Images.wall, x, 8);
        setMass(MassType.INFINITE);
    }
}
