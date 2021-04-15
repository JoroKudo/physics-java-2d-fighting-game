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

    public void update(Fighter fighter) {
        System.out.println(this.getWorldCenter());

        if (keyEventHandler.isKeyPressed("E")) {
            this.translateToOrigin();
            this.translate(fighter.getWorldCenter().x+2, fighter.getWorldCenter().y-1);
        }
        else{
            this.translateToOrigin();
            this.translate(fighter.getWorldCenter().x, fighter.getWorldCenter().y-1);
        }

    }



}
