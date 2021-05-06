package application.main;

import application.GameObjects.*;
import application.Navigation.SceneType;

import application.common.CollisionHandler;
import application.Navigation.Navigator;

import application.constants.Const;
import application.constants.Images;
import application.stats.Lifebar;
import application.stats.Timer;
import javafx.scene.canvas.GraphicsContext;
import org.dyn4j.dynamics.Body;

import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Vector2;

import org.dyn4j.world.BroadphaseCollisionData;
import org.dyn4j.world.World;
import org.dyn4j.world.listener.CollisionListenerAdapter;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends CopyOnWriteArrayList<GameObject> {

    private BasePlayer fighter;
    private BasePlayer fighter_2;
    public RagFighter ragfighter;
    private Lifebar lifebar1;
    private Lifebar lifebar2;
    private Timer timer;
    public Floor floor;
    public boolean rag = false;
    private final Controller controller;
    private ArrayList<Hadouken> hadoukens = new ArrayList<>();
    private final World<Body> physicWorld = new World<>();
    private final Navigator navigator;
    private final CollisionHandler collision;
    public boolean hitFighter1 = false;
    public boolean hitFighter2 = false;
    public double timePassedSinceCooldown;

    public Game(Controller controller, Navigator navigator) {
        this.controller = controller;
        this.navigator = navigator;
        this.collision = new CollisionHandler(this);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(Images.background, 0, 0);
        gc.drawImage(Images.KO, (Const.CANVAS_WIDTH - Const.DISTANCE_BETWEEN_LIFEBAR) / 2, 50);
        lifebar1.draw(gc);
        lifebar2.draw(gc);
        timer.draw(gc);
        for (Body body : physicWorld.getBodies()) {
            GameBody gameBody = (GameBody) body;
            gameBody.draw(gc);
        }
    }

    public void load() {
        fighter = new BasePlayer(1, 10, 8, controller, physicWorld);
        fighter_2 = new BasePlayer(2, 14, 8, controller, physicWorld);
        lifebar1 = new Lifebar(1);
        lifebar2 = new Lifebar(2);
        timer = new Timer();
        floor = new Floor(15, 17);
        physicWorld.setGravity(new Vector2(0, 15));

        physicWorld.addBody(fighter);
        physicWorld.addBody(fighter_2);
        physicWorld.addBody(fighter_2.fist);
        physicWorld.addBody(fighter_2.foot);
        physicWorld.addBody(fighter.fist);
        physicWorld.addBody(fighter.foot);

        physicWorld.addJoint(fighter.punchshould);
        physicWorld.addJoint(fighter.punchfoot);
        physicWorld.addJoint(fighter_2.punchshould);
        physicWorld.addJoint(fighter_2.punchfoot);
        physicWorld.addBody(floor);
        physicWorld.setGravity(new Vector2(0, 15));

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
        fighter.dirupdate();
        fighter_2.dirupdate();


        timePassedSinceCooldown += elapsedTime;
        timer.update(elapsedTime);
        if (fighter.isDoesFighterNeedsToReturnHadouken()) {
            hadoukens.add(fighter.getHadouken());
        }
        for (Hadouken hadouken : hadoukens) {
            hadouken.update();
        }
        if (rag)
            ragfighter.handleNavigationEventss(elapsedTime);
        /*if (controller.isKeyPressed("B") && !rag) {

            ragfighter = new RagFighter(fighter.getWorldCenter().x, fighter.getWorldCenter().y, controller);
            physicWorld.removeBody(fighter);
            physicWorld.removeBody(fighter.foot);
            physicWorld.removeBody(fighter.fist);
            ragfighter.initializeWorld(physicWorld);
            ragfighter.setup();
            rag = true;
        }*/
    }


    public void handleHitFighter(int id) {
        if (timePassedSinceCooldown >= 0.7) {
            if (id == 1) {
                if (fighter.isblocking) {
                    lifebar1.update(50 * 0.35);
                } else {
                    lifebar1.update(50);
                }
                timePassedSinceCooldown = 0;
                hitFighter1 = false;
                if (lifebar1.getKo()) {
                    physicWorld.removeBody(fighter);
                    lifebar1.setDamagetoNull();
                    navigator.goTo(SceneType.GAME_WIN_SCENE);
                }

            }

            if (id == 2) {
                if (fighter_2.isblocking) {
                    lifebar2.update(50 * 0.35);
                } else {
                    lifebar2.update(50);
                }

                timePassedSinceCooldown = 0;
                hitFighter2 = false;
                if (lifebar2.getKo()) {
                    physicWorld.removeBody(fighter);
                    lifebar2.setDamagetoNull();
                    navigator.goTo(SceneType.GAME_WIN_SCENE);
                }


            }

        }
    }


}






