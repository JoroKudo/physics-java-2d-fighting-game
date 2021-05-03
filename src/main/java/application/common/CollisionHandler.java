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
        //player 2 hits player 1 by fist
        if ((body1 instanceof Fighter_2) && (body2 instanceof Fist)) {
            game.handleHitFighter1();
        }

        //player 1 hits player 2 by fist
        if ((body2 instanceof Fighter_2) && (body1 instanceof Fist)) {
            game.handleHitFighter2();
        }

        // Player 2 hits Player 1 by Foot
        if ((body1 instanceof Fighter_2) && (body2 instanceof Foot)) {
            if(body2.getLinearVelocity().y>50){
            game.handleHitFighter1();}


        }
        // Player 1 hits Player 2 by Foot
        if ((body2 instanceof Fighter_2) && (body1 instanceof Foot)) {
            if(body1.getLinearVelocity().y>50){
                game.handleHitFighter2();}

        }
    }




}