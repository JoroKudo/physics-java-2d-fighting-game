package application.main;

import application.GameObjects.*;

import application.common.KeyEventHandler;
import application.common.Navigator;

import application.constants.Images;
import javafx.scene.canvas.GraphicsContext;
import org.dyn4j.dynamics.Body;

import org.dyn4j.dynamics.joint.PrismaticJoint;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.Vector2;

import org.dyn4j.world.World;


import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends CopyOnWriteArrayList<GameObject> {

    public Fighter fighter;
    public Fighter_2 fighter_2;
    public Fist fist;
    public WeldJoint<Body> punchshould;
    public final KeyEventHandler keyEventHandler;
    public final World<Body> physicWorld = new World<>();
    private final Navigator navigator;

    //RoomChanger
    private double punchcooldown = 1;

    public Game(KeyEventHandler keyEventHandler, Navigator navigator) {
        this.keyEventHandler = keyEventHandler;
        this.navigator = navigator;
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
        fighter_2 = new Fighter_2(10, 8, physicWorld, keyEventHandler);
        fist = new Fist(fighter.getWorldCenter().x, fighter.getWorldCenter().y);
        Floor floor = new Floor(10, 13);
        physicWorld.setGravity(new Vector2(0, 15));
        physicWorld.addBody(fighter);
        physicWorld.addBody(fighter_2);
        physicWorld.addBody(fist);
        punchshould = new WeldJoint<Body>(fighter, fist, new Vector2(fighter.getWorldCenter().x, fighter.getWorldCenter().y));
        physicWorld.addJoint(punchshould);
        PrismaticJoint<Body> puncharm = new PrismaticJoint<Body>(fighter, fist, new Vector2(fighter.getWorldCenter().x, fighter.getWorldCenter().y), new Vector2(fighter.getWorldCenter().x, 0));
        physicWorld.addJoint(puncharm);

        physicWorld.addBody(floor);
    }

    public void update(double elapsedTime) {
        physicWorld.update(elapsedTime);
        fighter.handleNavigationEvents();
        fighter_2.handleNavigationEvents();
        fist.update(fighter);


        if (punchcooldown > 1) {
            fist.setLinearVelocity(5, 0);


            if (keyEventHandler.isEPressed()) {

                physicWorld.removeJoint(punchshould);

                punchcooldown = 0;
            }
        } else {
            punchcooldown += 10 * elapsedTime;


        }


    }


}

