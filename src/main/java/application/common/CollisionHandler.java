package application.common;

import application.GameObjects.*;

import application.Sound.Sound;
import application.Sound.SoundEffectType;
import application.constants.Images;
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
        if ((body1 instanceof BasePlayer) && (body2 instanceof Fist) || (body2 instanceof BasePlayer) && (body1 instanceof Fist)) {
            Sound.play(SoundEffectType.FISTPUNCH);
            game.handleHitFighter2();
        }


        if ((body1 instanceof BasePlayer) && (body2 instanceof Fist) || (body2 instanceof BasePlayer) && (body1 instanceof Fist)) {
            Sound.play(SoundEffectType.FISTPUNCH);
            game.handleHitFighter1();
        }


        if ((body1 instanceof BasePlayer) && (body2 instanceof Foot)) {
            if (body2.getLinearVelocity().y > 10) {
                game.handleHitFighter2();
            }
        }

        if ((body1 instanceof BasePlayer) && (body2 instanceof Foot)) {
            if (body2.getLinearVelocity().y > 10) {
                game.handleHitFighter1();
            }

        }
        if ((body2 instanceof BasePlayer) && (body1 instanceof Foot)) {
            if (body1.getLinearVelocity().y > 10) {
                game.handleHitFighter2();
            }
        }

        if ((body2 instanceof BasePlayer) && (body1 instanceof Foot)) {
            if (body1.getLinearVelocity().y > 10) {
                game.handleHitFighter1();
            }

        }

        if ((body1 instanceof BasePlayer) && (body2 instanceof Hadouken)) {
            Hadouken hadouken = (Hadouken) body2;
            if (hadouken.owner != body1) {
                game.handleHitFighter2();

                physicWorld.removeBody(hadouken);
            }


        }

        if ((body2 instanceof BasePlayer) && (body1 instanceof Hadouken)) {
            Hadouken hadouken = (Hadouken) body1;
            if (hadouken.owner != body2) {

                game.handleHitFighter2();
                physicWorld.removeBody(hadouken);
            }


        }
        if ((body1 instanceof BasePlayer) && (body2 instanceof Hadouken)) {
            Hadouken hadouken = (Hadouken) body2;
            if (hadouken.owner != body1) {
                game.handleHitFighter1();
                physicWorld.removeBody(hadouken);
            }

        }
        if ((body2 instanceof BasePlayer) && (body1 instanceof Hadouken)) {
            Hadouken hadouken = (Hadouken) body1;
            if (hadouken.owner != body2) {

                game.handleHitFighter1();
                physicWorld.removeBody(hadouken);
            }
        }

    }
}




