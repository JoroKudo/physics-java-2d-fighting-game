package application.GameObjects;

import application.game.Images;
import org.dyn4j.geometry.MassType;

public class Fist extends GameObject {
    public Fist(double x, double y) {
        super(Images.fist_hitbox, x, y);
        setMass(MassType.NORMAL);
    }

    public void update(Fighter fighter) {
        System.out.println(fighter.getLocalCenter().x + fighter.getLocalCenter().y);
    }



}
