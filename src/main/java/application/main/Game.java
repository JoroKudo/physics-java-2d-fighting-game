package application.main;

import application.GameObjects.Fighter;
import application.GameObjects.Fist;
import application.GameObjects.Floor;
import application.GameObjects.GameObject;

import application.common.KeyEventHandler;
import application.common.Navigator;

import application.game.Images;
import javafx.scene.canvas.GraphicsContext;
import org.dyn4j.dynamics.Body;

import org.dyn4j.geometry.Vector2;

import org.dyn4j.world.World;


import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends CopyOnWriteArrayList<GameObject> {

    public Fighter fighter;
    public Fist fist;
    public final KeyEventHandler keyEventHandler;
    public final World<Body> physicWorld = new World<>();
    private final Navigator navigator;

    //RoomChanger
    private double switchcooler = 1;

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
        fist = new Fist(fighter.getWorldCenter().x, fighter.getWorldCenter().y);
        Floor floor  = new Floor(10, 13);
        physicWorld.setGravity(new Vector2(0, 15));
        physicWorld.addBody(fighter);
        physicWorld.addBody(fist);

        physicWorld.addBody(floor);
    }

    public void update(double elapsedTime) {
        physicWorld.update(elapsedTime);
        fighter.handleNavigationEvents();
        fist.update(fighter);
    }


}

