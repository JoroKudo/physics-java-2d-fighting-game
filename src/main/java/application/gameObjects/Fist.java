package application.gameObjects;
import application.constants.Images;
import org.dyn4j.geometry.MassType;

public class Fist extends GameObject {
    public Fist(double x, double y) {
        super(Images.hitbox_fist, x, y);
        setMass(MassType.NORMAL);
    }
}