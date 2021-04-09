package application.GameObjects;


import application.game.Images;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.MassType;

import org.dyn4j.world.World;

public class Fighter extends GameObject {

    private final World<Body> physicWorld;



    public Fighter(double x, double y, World<Body> physicWorld) {
        super(Images.fighter_standing, x, y);
        this.physicWorld = physicWorld;

        setMass(MassType.NORMAL);
    }



}
