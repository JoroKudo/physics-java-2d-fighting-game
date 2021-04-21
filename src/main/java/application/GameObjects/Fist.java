package application.GameObjects;

import application.common.KeyEventHandler;
import application.constants.Images;
import org.dyn4j.geometry.MassType;

public class Fist extends GameObject {
    private final KeyEventHandler keyEventHandler;
    public Fist(double x, double y , KeyEventHandler keyEventHandler) {
        super(Images.fist_hitbox, x, y);
        this.keyEventHandler = keyEventHandler;
        setMass(MassType.NORMAL);
    }


}
