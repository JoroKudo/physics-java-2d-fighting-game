package application.gameObjects;

import application.constants.Images;
import org.dyn4j.geometry.MassType;

public class Floor extends GameObject {
    public Floor() {
        super(Images.floor, 15, 17);
        setMass(MassType.INFINITE);
    }
}
