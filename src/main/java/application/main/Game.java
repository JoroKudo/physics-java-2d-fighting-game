package application.main;

import application.GameObjects.*;

import application.common.CollisionDetector;
import application.common.KeyEventHandler;
import application.common.Navigator;

import application.constants.Images;
import javafx.scene.canvas.GraphicsContext;
import org.dyn4j.dynamics.Body;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.joint.PrismaticJoint;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.Vector2;

import org.dyn4j.world.BroadphaseCollisionData;
import org.dyn4j.world.World;
import org.dyn4j.world.listener.CollisionListenerAdapter;


import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends CopyOnWriteArrayList<GameObject> {

    public Fighter fighter;
    public Fighter_2 fighter_2;
    public Fist fist;
    public WeldJoint<Body> punchshould;
    public final KeyEventHandler keyEventHandler;
    public final World<Body> physicWorld = new World<>();
    private final Navigator navigator;
    private final CollisionDetector collision;

    //RoomChanger
    private double punchcooldown = 2;

    public Game(KeyEventHandler keyEventHandler, Navigator navigator) {
        this.keyEventHandler = keyEventHandler;
        this.navigator = navigator;
        this.collision = new CollisionDetector(this);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(Images.background, 0, 0);
        for (Body body : physicWorld.getBodies()) {
            GameObject gameObject = (GameObject) body;
            gameObject.draw(gc);
        }
    }

    public void load() {
        fighter = new Fighter(10, 8, physicWorld, keyEventHandler);
        fighter_2 = new Fighter_2(14, 8, physicWorld, keyEventHandler);
        fist = new Fist(fighter.getWorldCenter().x, fighter.getWorldCenter().y-1,keyEventHandler);
        Floor floor = new Floor(10, 13);
        physicWorld.setGravity(new Vector2(0, 15));
        physicWorld.addBody(fighter);
        physicWorld.addBody(fighter_2);
        physicWorld.addBody(fist);
        physicWorld.addBody(floor);
        punchshould = new WeldJoint<Body>(fighter, fist, new Vector2(fighter.getWorldCenter().x, fighter.getWorldCenter().y-1));
        physicWorld.addJoint(punchshould);



        physicWorld.addCollisionListener(new CollisionListenerAdapter<>() {
            @Override
            public boolean collision(BroadphaseCollisionData<Body, BodyFixture> collision) {
                Body body1 = collision.getBody1();
                Body body2 = collision.getBody2();
                Game.this.collision.handle(body1, body2, physicWorld);
                return true;
            }


        });
    }

    public void update(double elapsedTime) {
        physicWorld.update(elapsedTime);
        fighter.handleNavigationEvents();
        fighter_2.handleNavigationEvents();
        fist.update(fighter);



    }



}

