package application.common;

import application.GameObjects.*;

import application.main.Game;
import org.dyn4j.dynamics.Body;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.world.BroadphaseCollisionData;
import org.dyn4j.world.ManifoldCollisionData;
import org.dyn4j.world.NarrowphaseCollisionData;
import org.dyn4j.world.World;
import org.dyn4j.world.listener.CollisionListener;
import org.dyn4j.world.listener.CollisionListenerAdapter;

public class CollisionDetector implements CollisionListener<Body, BodyFixture> {

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

        if ((body1 instanceof Fighter_2) && (body2 instanceof Fist)||(body2 instanceof Fighter_2) && (body1 instanceof Fist)) {

            game.handleHit();


        }
        if ((body1 instanceof Fighter_2) && (body2 instanceof Foot)) {

            if(body2.getLinearVelocity().y>50){
            game.handleHit();}


        }
        if ((body2 instanceof Fighter_2) && (body1 instanceof Foot)) {

            if(body1.getLinearVelocity().y>50){
                game.handleHit();}

        }
    }

    public  boolean checkTypes(Body body1, Body body2, Class type1, Class type2) {
        return type1.isInstance(body1) && type2.isInstance(body2) || type1.isInstance(body2) && type2.isInstance(body1);
    }




    @Override
    public boolean collision(BroadphaseCollisionData<Body, BodyFixture> collision) {
        Body body1 = collision.getBody1();
        Body body2 = collision.getBody2();
        if (checkTypes(body1, body2, Fist.class, BasePlayer.class)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean collision(NarrowphaseCollisionData<Body, BodyFixture> collision) {
        return true;
    }
    @Override
    public boolean collision(ManifoldCollisionData<Body, BodyFixture> collision) {
        return true;
    }


}