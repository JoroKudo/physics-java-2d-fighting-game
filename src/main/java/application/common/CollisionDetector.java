package application.common;

import application.GameObjects.*;

import application.main.Game;
import org.dyn4j.dynamics.Body;

import org.dyn4j.world.World;

public class CollisionDetector {

    private final Game game;


    private Body body1;
    private Body body2;
    private World<Body> physicWorld;


    public CollisionDetector(Game game) {
        this.game = game;

    }

    public void handle(Body body1, Body body2, World<Body> physicWorld) {
        this.physicWorld = physicWorld;
        this.body1 = body1;
        this.body2 = body2;

        handleFighterPain();

    }


    public void handleFighterPain() {

        if ((body1 instanceof Fighter_2) && (body2 instanceof Fist)) {

            game.handleHit();

        }
        if ((body2 instanceof Fighter_2) && (body1 instanceof Fist)) {

            game.handleHit();

        }
    }



}