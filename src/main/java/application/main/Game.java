package application.main;

import application.GameObjects.*;
import application.Navigation.SceneType;
import application.common.KeyEventHandler;

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

    private Fighter fighter;
    private Fighter_2 fighter_2;
    public RagFighter ragfighter;
    private Lifebar lifebar1;
    private Lifebar lifebar2;
    private Fist fist;
    private Timer timer;
    public Floor floor;
    private final KeyEventHandler keyEventHandler;
    private ArrayList<Hadouken> hadoukens = new ArrayList<>();
    private final World<Body> physicWorld = new World<>();
    private final Navigator navigator;
    private final CollisionHandler collision;
    public boolean  hitFighter1 = false;
    public boolean  hitFighter2 = false;
    public double timePassedSinceCooldown;





    public Game(KeyEventHandler keyEventHandler, Navigator navigator) {
        this.keyEventHandler = keyEventHandler;
        this.navigator = navigator;
        this.collision = new CollisionHandler(this);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(Images.background, 0, 0);
        gc.drawImage(Images.KO, (Const.CANVAS_WIDTH - Const.DISTANCE_BETWEEN_LIFEBAR) / 2, 50);
        lifebar1.draw(gc);
        lifebar2.draw(gc);
        timer.draw(gc);
        for (Body  body : physicWorld.getBodies()) {
                GameBody gameBody = (GameBody) body;
                gameBody.draw(gc);

        }
    }

    public void load() {



        fighter = new Fighter(10, 8, physicWorld, keyEventHandler);
        fighter_2 = new Fighter_2(14, 8, physicWorld, keyEventHandler);
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
        physicWorld.addBody(floor);
        physicWorld.addJoint(fighter.punchshould);
        physicWorld.addJoint(fighter.punchfoot);
        physicWorld.addJoint(fighter_2.punchshould);
        physicWorld.addJoint(fighter_2.punchfoot);

        physicWorld.setGravity(new Vector2(0, 15));

/*
        ragfighter = new RagFighter(11, 8,  keyEventHandler);
        ragfighter.initializeWorld(physicWorld);

        //BAD GARBAGE CODE THAT JIRO WILL FIX
        ragfighter.torso.rotate(154);
        ragfighter.righteb.rotate(154);
        ragfighter.righthand.rotate(154);
        ragfighter.rightHumerus.rotate(154);
        ragfighter.rightUlna.rotate(154);
        ragfighter.lefteb.rotate(154);
        ragfighter.lefthand.rotate(154);
        ragfighter.leftHumerus.rotate(154);
        ragfighter.leftUlna.rotate(154);

        //BAD GARBAGE CODE PT 2
        ragfighter.torso.translate(12,4);
        ragfighter.righteb.translate(12,4);
        ragfighter.righthand.translate(12,4);
        ragfighter.rightHumerus.translate(12,4);
        ragfighter.rightUlna.translate(12,4);
        ragfighter.lefteb.translate(12,4);
        ragfighter.lefthand.translate(12,4);
        ragfighter.leftHumerus.translate(12,4);
        ragfighter.leftUlna.translate(12,4);
*/

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

        public void update(double elapsedTime){
            physicWorld.update(elapsedTime);
            fighter.handleNavigationEvents(elapsedTime);
            fighter_2.handleNavigationEvents(elapsedTime);
            //ragfighter.handleNavigationEventss(elapsedTime);
            timePassedSinceCooldown += elapsedTime;
            timer.update(elapsedTime);
            if (fighter.isDoesFighterNeedsToReturnHadouken()) {
                hadoukens.add(fighter.getHadouken());
            }
            for (Hadouken hadouken : hadoukens) {
                hadouken.update();
            }
            if (hitFighter1 && timePassedSinceCooldown >= 0.7 ) {
                lifebar1.update(100);
                timePassedSinceCooldown = 0;
                hitFighter1 = false;
                if (lifebar1.getKo()) {
                    physicWorld.removeBody(fighter);
                    navigator.goTo(SceneType.GAME_WIN_SCENE);
                }
                Body f = (Body) fighter.getFixture(3).getShape();
                if(physicWorld.isInContact(f,fighter_2)){
                }
            }
            if (hitFighter2 && timePassedSinceCooldown >= 0.7) {
                lifebar2.update(100);
                timePassedSinceCooldown = 0;
                hitFighter2 = false;
                if (lifebar2.getKo()) {
                    physicWorld.removeBody(fighter);
                    navigator.goTo(SceneType.GAME_WIN_SCENE);
                }
                Body f = (Body) fighter.getFixture(3).getShape();
                if(physicWorld.isInContact(f,fighter_2)){
                }
            }

        }


    public void handleHitFighter1() {
        hitFighter1 = true;
    }

    public void handleHitFighter2() {
        hitFighter2 = true;
    }

}







