package application.game;

import application.gameObjects.*;
import application.sound.Sound;
import application.sound.SoundEffectType;
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

    protected void handle(Body body1, Body body2, World<Body> physicWorld) {
        this.physicWorld = physicWorld;
        this.body1 = body1;
        this.body2 = body2;
        handleFighterCollisions();
        removeObjects();
    }

    private void handleFighterCollisions() {
        checkForHit(body1, body2);
        checkForHit(body2, body1);
        checkForStomp(body1, body2);
        checkForStomp(body2, body1);
        checkForCollisionWithHadoken(body1, body2);
        checkForCollisionWithHadoken(body2, body1);
    }

    private void checkForHit(Body body1, Body body2) {
        if ((body1 instanceof BasePlayer) && (body2 instanceof Fist)) {
            BasePlayer fighter = (BasePlayer) body1;
            game.handleHitFighter(fighter.getId());
        }
    }

    private void checkForStomp(Body body1, Body body2) {
        if ((body1 instanceof BasePlayer) && (body2 instanceof Foot)) {
            BasePlayer fighter = (BasePlayer) body1;
            if (body2.getLinearVelocity().y > 10) {
                game.handleHitFighter(fighter.getId());
            }
        }
    }

    private void checkForCollisionWithHadoken(Body body1, Body body2) {
        if ((body1 instanceof BasePlayer) && (body2 instanceof Hadoken)) {
            Hadoken hadoken = (Hadoken) body2;
            if (hadoken.owner != body1) {
                switch (hadoken.owner.getId()) {
                    case 1:
                        game.handleHitFighter(2);

                    case 2:
                        game.handleHitFighter(1);
                }
                physicWorld.removeBody(hadoken);
            }
        }
    }

    private void removeObjects() {
        if ((body1 instanceof Wall) && (body2 instanceof Hadoken)) {
            physicWorld.removeBody(body2);
        }
        if ((body2 instanceof Wall) && (body1 instanceof Hadoken)) {
            physicWorld.removeBody(body1);
        }
    }

}




