package application.gameObjects;


import application.constants.Images;
import org.dyn4j.geometry.MassType;

public class Fist extends GameObject {
    public Fist(double x, double y) {
        super(Images.fist_hitbox, x, y);
        setMass(MassType.NORMAL);
    }
}