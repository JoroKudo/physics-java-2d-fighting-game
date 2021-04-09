package application.gameobjects;

import application.constants.Images;
import org.dyn4j.geometry.MassType;

public class Fighter extends GameObject  {

    public Fighter(double x, double y) {
        super(Images.fighter_standing, x, y);

        setMass(MassType.NORMAL);
    }
}
