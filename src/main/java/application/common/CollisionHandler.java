package application.common;

import application.GameObjects.*;

import application.main.Game;
import org.dyn4j.dynamics.Body;
import org.dyn4j.world.World;


public class CollisionHandler {

    private final Game game;
    private Body body1;
    private Body body2;
    private World<Body> physicWorld;


    public CollisionHandler(Game game) {
        this.game = game;

    }

    public void handle(Body body1, Body body2, World<Body> physicWorld) {
        this.physicWorld = physicWorld;
        this.body1 = body1;
        this.body2 = body2;
        handleFighterPain();

    }

    public void handleFighterPain() {
        if ((body1 instanceof Fighter_2) && (body2 instanceof Fist) || (body2 instanceof Fighter_2) && (body1 instanceof Fist)) {
            game.handleHitFighter2();
        }


        if ((body1 instanceof Fighter) && (body2 instanceof Fist) || (body2 instanceof Fighter) && (body1 instanceof Fist)) {
            game.handleHitFighter1();
        }


        if ((body1 instanceof Fighter_2) && (body2 instanceof Foot)) {
            if (body2.getLinearVelocity().y > 10) {
                game.handleHitFighter2();
            }
        }

        if ((body1 instanceof Fighter) && (body2 instanceof Foot)) {
            if (body2.getLinearVelocity().y > 10) {
                game.handleHitFighter1();
            }

        }
        if ((body2 instanceof Fighter_2) && (body1 instanceof Foot)) {
            if (body1.getLinearVelocity().y > 10) {
                game.handleHitFighter2();
            }
        }

        if ((body2 instanceof Fighter) && (body1 instanceof Foot)) {
            if (body2.getLinearVelocity().y > 10) {
                game.handleHitFighter1();
            }

        }

        if ((body1 instanceof Fighter_2) && (body2 instanceof Hadouken)) {
            Hadouken hadouken = (Hadouken) body2;

            game.handleHitFighter1();
            hadouken.explode();


        }

        if ((body2 instanceof Fighter_2) && (body1 instanceof Hadouken)) {
            Hadouken hadouken = (Hadouken) body1;


            game.handleHitFighter2();
            hadouken.explode();


        }
        if ((body1 instanceof Fighter) && (body2 instanceof Hadouken)) {
            Hadouken hadouken = (Hadouken) body2;
            game.handleHitFighter1();
            hadouken.explode();

        }
        if ((body2 instanceof Fighter) && (body1 instanceof Hadouken)) {
            Hadouken hadouken = (Hadouken) body1;


            game.handleHitFighter1();
            hadouken.explode();
        }

    }
}




