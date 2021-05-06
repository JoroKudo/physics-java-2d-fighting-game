package application.GameObjects;

import application.constants.Images;
import org.dyn4j.geometry.MassType;

public class Fist extends GameObject {
    private final Controller controller;
    public Fist(double x, double y, Controller controller) {
        super(Images.fist_hitbox, x, y);
        this.controller = controller;
        setMass(MassType.NORMAL);
    }
}