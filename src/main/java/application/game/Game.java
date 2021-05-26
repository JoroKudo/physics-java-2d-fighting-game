package application.game;

import application.gameObjects.*;
import application.navigation.Navigator;
import application.navigation.SceneType;
import application.common.Controller;
import application.controller.GamepadController;
import application.controller.VoiceController;
import application.constants.Const;
import application.constants.Images;
import application.gui.UserSelectionScene;
import application.stats.Lifebar;
import application.stats.Timer;
import javafx.scene.canvas.GraphicsContext;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.BroadphaseCollisionData;
import org.dyn4j.world.World;
import org.dyn4j.world.listener.CollisionListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends CopyOnWriteArrayList<GameObject> {

    private final Controller keyboardController, gamepadcontroller;
    private final VoiceController voiceController;
    private final World<Body> physicWorld = new World<>();
    private final Navigator<?> navigator;
    private final CollisionHandler collision;
    private double timePassedSinceCooldown;
    private BasePlayer fighter, fighter_2;
    private final Lifebar lifebar1;
    private final Lifebar lifebar2;
    private Timer timer;
    private final UserSelectionScene userSelectionScene;
    private final Runnable gameLoopStopper;
    private final ArrayList<Hadoken> hadokens = new ArrayList<>();


    public Game(Controller keyboardController, GamepadController gamepadController, VoiceController voiceController, Navigator<?> navigator, Lifebar lifebar1, Lifebar lifebar2, Runnable gameLoopStopper, UserSelectionScene userSelectionScene) {
        this.keyboardController = keyboardController;
        this.gamepadcontroller = gamepadController;
        this.voiceController = voiceController;
        this.navigator = navigator;
        this.collision = new CollisionHandler(this);
        this.lifebar1 = lifebar1;
        this.lifebar2 = lifebar2;
        this.gameLoopStopper = gameLoopStopper;
        this.userSelectionScene = userSelectionScene;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(Images.background, 0, 0);
        gc.drawImage(Images.KO, (Const.CANVAS_WIDTH - Const.DISTANCE_BETWEEN_LIFEBAR) >> 1, 50);
        lifebar1.draw(gc);
        lifebar2.draw(gc);
        timer.draw(gc);
        for (Body body : physicWorld.getBodies()) {
            GameBody gameBody = (GameBody) body;
            gameBody.draw(gc);
        }
    }

    public void load() {


        switch (userSelectionScene.getcontroll1()) {
            case "mic" -> {
                fighter = new BasePlayer(1, 10, 8, voiceController, physicWorld);
                voiceController.initiate(voiceController.configuration);
            }
            case "key" -> fighter = new BasePlayer(1, 10, 8, keyboardController, physicWorld);
            case "ctrl" -> fighter = new BasePlayer(1, 10, 8, gamepadcontroller, physicWorld);
        }
        switch (userSelectionScene.getcontroll2()) {
            case "mic" -> {
                fighter_2 = new BasePlayer(2, 14, 8, voiceController, physicWorld);
                voiceController.initiate(voiceController.configuration);
            }
            case "key" -> fighter_2 = new BasePlayer(2, 14, 8, keyboardController, physicWorld);
            case "ctrl" -> fighter_2 = new BasePlayer(2, 14, 8, gamepadcontroller, physicWorld);
        }
        timer = new Timer();
        Floor floor = new Floor(15, 17);
        Wall wall1 = new Wall(30.2, 7);
        Wall wall2 = new Wall(0, 7);

        physicWorld.setGravity(new Vector2(0, 15));

        for (BasePlayer basePlayer : Arrays.asList(fighter, fighter_2)) {
            physicWorld.addBody(basePlayer);
            physicWorld.addBody(basePlayer.fist);
            physicWorld.addBody(basePlayer.foot);
            for (WeldJoint weldJoint : Arrays.asList(basePlayer.punchTargetPlace, basePlayer.punchfoot)) {
                physicWorld.addJoint(weldJoint);
            }
        }
        physicWorld.addBody(floor);
        for (Wall wall : Arrays.asList(wall1, wall2)) {
            physicWorld.addBody(wall);
        }

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

        for (BasePlayer player : Arrays.asList(fighter, fighter_2)) {
            player.handleNavigationEvents(elapsedTime);
            player.dirupdate();
        }

        timePassedSinceCooldown += elapsedTime;
        timer.update(elapsedTime);
        if (timer.getTime() < 0) {
            navigator.goTo(SceneType.GAME_WIN_SCENE);
            gameLoopStopper.run();
        }

        if (fighter.isDoesFighterNeedsToReturnHadouken()) {
            hadokens.add(fighter.getHadouken());
        }
        for (Hadoken hadoken : hadokens) {
            hadoken.update();
        }
    }

    public void handleHitFighter(int id) {
        if (timePassedSinceCooldown >= 0.7) {
            double blockProtection = 1;
            ArrayList<BasePlayer> basePlayer = new ArrayList<>();
            basePlayer.add(fighter);
            basePlayer.add(fighter_2);
            ArrayList<Lifebar> lifebars = new ArrayList<>();
            lifebars.add(lifebar1);
            lifebars.add(lifebar2);

            if (basePlayer.get(id - 1).isBlocking) {
                blockProtection = 0.35;
            }
            lifebars.get(id - 1).increaseDamage(Const.HIT_DMG * blockProtection);
            timePassedSinceCooldown = 0;

            if (lifebars.get(id - 1).isKo()) {
                physicWorld.removeBody(basePlayer.get(id - 1));
                navigator.goTo(SceneType.GAME_WIN_SCENE);
                gameLoopStopper.run();
            }
        }
    }
}

