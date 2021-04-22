package application.main;

import application.GameObjects.*;
import application.Navigation.SceneType;
import application.common.KeyEventHandler;

import application.common.CollisionDetector;
import application.Navigation.Navigator;

import application.constants.Const;
import application.constants.Images;
import application.gui.GameWinScene;
import application.stats.Lifebar_1;
import application.stats.Lifebar_2;
import application.stats.Timer;
import javafx.scene.canvas.GraphicsContext;
import org.dyn4j.dynamics.Body;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Vector2;

import org.dyn4j.world.BroadphaseCollisionData;
import org.dyn4j.world.World;
import org.dyn4j.world.listener.CollisionListenerAdapter;

import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends CopyOnWriteArrayList<GameObject> {

    private Fighter fighter;
    private Fighter_2 fighter_2;
    private Fist fist;
    private Lifebar_2 lifebar2;
    private Lifebar_1 lifebar_1;
    private Timer timer;
    private Hadouken hadouken;
    public Floor floor;
    private final KeyEventHandler keyEventHandler;
    private final World<Body> physicWorld = new World<>();
    private final Navigator navigator;
    private final CollisionDetector collision;
    public boolean hit = false;
    public double timePassedSinceCooldown;

    public boolean Player1Wins = false;
    public boolean Player2Wins = false;

    //RoomChanger
    private double punchcooldown = 2;

    public Game(KeyEventHandler keyEventHandler, Navigator navigator) {
        this.keyEventHandler = keyEventHandler;
        this.navigator = navigator;
        this.collision = new CollisionDetector(this);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(Images.background, 0, 0);
        gc.drawImage(Images.KO, (Const.CANVAS_WIDTH - Const.DISTANCE_BETWEEN_LIFEBAR) / 2, 50);
        lifebar_1.draw(gc);
        lifebar2.draw(gc);
        timer.draw(gc);
        hadouken.draw(gc);
        for (Body body : physicWorld.getBodies()) {
            if (body == fighter) {
                fighter.draw(gc);


            } else if (body == fighter_2) {
                fighter_2.draw(gc);


            } else {
                GameObject gameObject = (GameObject) body;
                gameObject.draw(gc);
            }
        }
    }

    public void load() {
        fighter = new Fighter(10, 8, physicWorld, keyEventHandler);
        fighter_2 = new Fighter_2(14, 8, physicWorld, keyEventHandler);
        hadouken = new Hadouken(12, 4, physicWorld, keyEventHandler);
        lifebar_1 = new Lifebar_1();
        lifebar2 = new Lifebar_2();
        timer = new Timer();
        floor = new Floor(10, 13);
        physicWorld.addBody(hadouken);
        physicWorld.setGravity(new Vector2(0, 15));

        physicWorld.addBody(fighter);
        physicWorld.addBody(fighter_2);
        physicWorld.addBody(fighter.fist);
        physicWorld.addBody(fighter.foot);
        physicWorld.addBody(floor);
        physicWorld.addJoint(fighter.punchshould);
        physicWorld.addJoint(fighter.punchfoot);


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
        fighter.handleNavigationEvents(elapsedTime);
        fighter_2.handleNavigationEvents(elapsedTime);
        hadouken.update();
        timePassedSinceCooldown += elapsedTime;
        timer.update(elapsedTime);
        if (hit && timePassedSinceCooldown >= 0.7) {
            lifebar2.update(100);
            timePassedSinceCooldown = 0;
            hit = false;
            if (lifebar2.getKO()) {
                navigator.registerScene(SceneType.GAME_OVER_SCENE, new GameWinScene(navigator));
                physicWorld.removeBody(fighter_2);
                navigator.goTo(SceneType.GAME_OVER_SCENE);
            }
            if (lifebar_1.getKO()) {
                navigator.registerScene(SceneType.GAME_OVER_SCENE, new GameWinScene(navigator));
                physicWorld.removeBody(fighter);
                navigator.goTo(SceneType.GAME_OVER_SCENE);
            }

        }
    }

    public void handleHit() {
        hit = true;
    }
}





