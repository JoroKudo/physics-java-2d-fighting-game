package application.common;

import application.GameObjects.*;
import application.Sound.Sound;
import application.Sound.SoundEffectType;
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
        if ((body1 instanceof BasePlayer) && (body2 instanceof Fist)) {
            BasePlayer fighter = (BasePlayer) body1;
            Sound.play(SoundEffectType.FISTPUNCH);
            game.handleHitFighter(fighter.id);
        }
        if ((body2 instanceof BasePlayer) && (body1 instanceof Fist)) {
            BasePlayer fighter = (BasePlayer) body2;
            Sound.play(SoundEffectType.FISTPUNCH);
            game.handleHitFighter(fighter.id);
        }
        if ((body1 instanceof BasePlayer) && (body2 instanceof Foot)) {
            BasePlayer fighter = (BasePlayer) body1;
            if (body2.getLinearVelocity().y > 10) {
                game.handleHitFighter(fighter.id);
            }
        }
        if ((body2 instanceof BasePlayer) && (body1 instanceof Foot)) {
            BasePlayer fighter = (BasePlayer) body2;
            if (body1.getLinearVelocity().y > 10) {
                game.handleHitFighter(fighter.id);
            }
        }
        if ((body1 instanceof BasePlayer) && (body2 instanceof Hadouken)) {
            Hadouken hadouken = (Hadouken) body2;
            if (hadouken.owner != body1) {
                game.handleHitFighter(hadouken.owner.id);
                physicWorld.removeBody(hadouken);
            }
        }
        if ((body2 instanceof BasePlayer) && (body1 instanceof Hadouken)) {
            Hadouken hadouken = (Hadouken) body1;
            if (hadouken.owner != body2) {
                game.handleHitFighter(hadouken.owner.id);
                physicWorld.removeBody(hadouken);
            }
        }
    }
}




