package application.GameObjects;

import application.common.Controller;
import application.constants.Images;
import org.dyn4j.geometry.MassType;

public class Foot extends GameObject {

    private final Controller controller;

    public Foot(double x, double y, Controller controller) {
        super(Images.foot_hitbox, x, y);
        this.controller = controller;
        setMass(MassType.NORMAL);
    }
}
