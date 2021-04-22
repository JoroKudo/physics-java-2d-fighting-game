package application.GameObjects;

import application.common.KeyEventHandler;
import application.constants.Images;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;

public class Foot extends GameObject {
    private final KeyEventHandler keyEventHandler;
    public Foot(double x, double y , KeyEventHandler keyEventHandler) {
        super(Images.foot_hitbox, x, y);
        this.keyEventHandler = keyEventHandler;
        setMass(MassType.NORMAL);
    }




}
