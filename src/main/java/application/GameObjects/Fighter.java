package application.GameObjects;


import application.constants.Images;

import application.common.KeyEventHandler;
import application.main.Game;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.MassType;

import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;

public class Fighter extends BasePlayer {
    private final World<Body> physicWorld;
    private final KeyEventHandler keyEventHandler;
    private  Game game;
    public GameObject  fist;
    public WeldJoint punchshould;
    public boolean p =true;
    public double cooldown = 1.1;
    private double jumpCooldown = 1;
    public Fighter(double x, double y, World<Body> physicWorld, KeyEventHandler keyEventHandler) {
        super(Images.fighter_look_right, x, y);
        this.physicWorld = physicWorld;
        this.keyEventHandler = keyEventHandler;
        setMass(MassType.FIXED_ANGULAR_VELOCITY);
        fist = (GameObject) new Fist(x, y, keyEventHandler);

        punchshould = new WeldJoint<Body>(this, fist, new Vector2(x, y));

    }


    public void handleNavigationEvents(double elapsedTime) {


        if (keyEventHandler.isKeyPressed("D"))
            walkRight();
        if (keyEventHandler.isKeyPressed("E"))
            punch(elapsedTime);
        if (keyEventHandler.isKeyPressed("A"))
            walkLeft();
        if (keyEventHandler.isKeyPressed("W"))
            jump();
        if (keyEventHandler.isKeyPressed("S"))
            duck(elapsedTime);
        if (keyEventHandler.isKeyPressed("Q"))
            block();
        if (keyEventHandler.isKeyPressed("D"))
            walkRight();

        if (!keyEventHandler.isKeyPressed("A") && !keyEventHandler.isKeyPressed("D") && !keyEventHandler.isKeyPressed("W") && !keyEventHandler.isKeyPressed("E") && !keyEventHandler.isKeyPressed("S") && !keyEventHandler.isKeyPressed("Q"))
            this.image = Images.fighter_look_right;
        if ((!keyEventHandler.isKeyPressed("D") && isOnGround()) && (!keyEventHandler.isKeyPressed("A") && isOnGround())) {
            setLinearVelocity(0, getLinearVelocity().y);
        }
    }

    private void duck(double elapsedTime) {
        this.image = Images.duck_right;
        applyImpulse(new Vector2(0, 100));
        if (keyEventHandler.isKeyPressed("S")&&p) {

            this.fist.translate(0,3);
            p=false;

        }
        else{
            cooldown += 10 * elapsedTime;
        }
        if (!p&& cooldown>1) {

            this.fist.translate(0,-3);
            p=true;
            cooldown=0;

        }

    }


// public void jump(double deltaInSec) {
//     if (isOnGround()) {
//         if (jumpCooldown > 1) {
//             applyImpulse(new Vector2(0, -100));
//             jumpCooldown = 0;
//         }else {
//             jumpCooldown += 5 * deltaInSec;
//         }
//     }
// }


    public void jump() {
        if (isOnGround()) {
            this.image = Images.jump_right;
                applyImpulse(new Vector2(0, -100));

            }
        }


    public void walkLeft() {
        setLinearVelocity(-4, getLinearVelocity().y);
        this.image = Images.fighter_Bwalk_right;
    }

    public void walkRight() {
        setLinearVelocity(4, getLinearVelocity().y);
        this.image = Images.fighter_walk_right;
    }

    public void punch(double elapsedTime) {
        this.image = Images.punch_right;
        if (keyEventHandler.isKeyPressed("E")&&p) {

            fist.translate(2,0);
            p=false;

        }
        else{
            cooldown += 10 * elapsedTime;
        }
        if (!p&& cooldown>1) {

            fist.translate(-2,0);
            p=true;
            cooldown=0;

        }


    }

    public void block() {
        this.image = Images.block;
    }

    public boolean isOnGround() {
        for (Body body : physicWorld.getBodies()) {
            if (physicWorld.isInContact(this, body)) {
                if (!(body instanceof BasePlayer)) {
                    return true;
                }
            }
        }
        return false;
    }

}
