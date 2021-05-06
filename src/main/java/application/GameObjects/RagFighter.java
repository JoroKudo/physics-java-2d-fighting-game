package application.GameObjects;


import application.common.Controller;
import application.constants.Images;

import javafx.scene.canvas.GraphicsContext;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.joint.PrismaticJoint;
import org.dyn4j.dynamics.joint.RevoluteJoint;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;



public class RagFighter  {
    private Controller controller;
    private double x;
    private double y;

    public RagFighter(double x, double y, Controller controller) {

        this.controller = controller;
        this.x=x;
        this.y=y;
    }

    public void handleNavigationEventss(double elapsedTime) {
        if (controller.RagFighterJump()) {
            righthand.applyImpulse(new Vector2(0,-20));
            torso.setLinearVelocity(new Vector2(0,0));

        }
        if (controller.RagFighterDuck()) {
            righthand.applyImpulse(new Vector2(0,20));
            torso.setLinearVelocity(new Vector2(0,0));

        }
        if (controller.RagFighterLeft()) {

            righthand.applyImpulse(new Vector2(20,0));
            torso.setLinearVelocity(new Vector2(0,0));

        }
        if (controller.RagFighterRight()) {

            righthand.applyImpulse(new Vector2(-20,0));
            torso.setLinearVelocity(new Vector2(0,0));

        }
        }

    public GameBody torso;

    public GameBody rightUlna;
    public GameBody righthand;
    public GameBody rightHumerus;

    public GameBody righteb;


    public GameBody leftUlna;
    public GameBody lefthand;
    public GameBody leftHumerus;

    public GameBody lefteb;

    private double scale=2;




    public void initializeWorld(World<Body> physicWorld) {


        // the ragdoll


        // Torso
        torso = new GameBody(Images.fist_hitbox);
        torso.addFixture(Geometry.createRectangle(scale*0.5, scale*1.0));
        {
            Convex c = Geometry.createRectangle(scale*1.0, scale*0.25);
            c.translate(new Vector2(scale*0.00390625, scale*0.375));
            torso.addFixture(c);
        }
        torso.translate(new Vector2(scale*0.0234375, scale*-0.8125));

        torso.setMass(MassType.FIXED_ANGULAR_VELOCITY);
        physicWorld.addBody(torso);



        // Right Humerus
         rightHumerus = new GameBody(Images.fist_hitbox);
        rightHumerus.addFixture(Geometry.createRectangle(scale*0.5, scale*0.2));
        rightHumerus.translate(new Vector2(scale*0.609375, scale*-0.4375));

        rightHumerus.setMass(MassType.NORMAL);
        physicWorld.addBody(rightHumerus);

        // Right Ulna
        rightUlna = new GameBody(Images.fist_hitbox);
        rightUlna.addFixture(Geometry.createRectangle(scale*0.5, scale*0.07));
        rightUlna.translate(new Vector2(scale*0.98828125, scale*-0.44040625));
        rightUlna.setMass(MassType.NORMAL);
        physicWorld.addBody(rightUlna);

        // Right elbowstopper
         righteb = new GameBody(Images.fist_hitbox);
        righteb.addFixture(Geometry.createRectangle(scale*0.15, scale*0.1));
        righteb.translate(new Vector2(scale*0.77828125, scale*-0.53040625));
        righteb.setMass(MassType.NORMAL);
        physicWorld.addBody(righteb);

        // Right hand
        righthand = new GameBody(Images.fist_hitbox);
        righthand.addFixture(Geometry.createRectangle(scale*0.4, scale*0.25));
        righthand.translate(new Vector2(scale*1.33828125, scale*-0.44140625));
        righthand.setMass(MassType.NORMAL);
        physicWorld.addBody(righthand);



        // Torso to Right Humerus
        RevoluteJoint<Body> torsoToRightHumerus = new RevoluteJoint<Body>(torso, rightHumerus, new Vector2(scale*0.4, scale*-0.4));
        physicWorld.addJoint(torsoToRightHumerus);


        // Right Humerus to Right Ulna
        RevoluteJoint<Body> rightHumerusToRightUlna = new RevoluteJoint<Body>(rightHumerus, rightUlna, new Vector2(scale*0.82, scale*-0.43));
        physicWorld.addJoint(rightHumerusToRightUlna);


        // Right ulna to Right eb
        WeldJoint<Body> rightHumerusToRighteb = new WeldJoint<Body>(rightUlna, righteb, new Vector2(scale*0.77, scale*-0.64));
        physicWorld.addJoint(rightHumerusToRighteb);


        // Right Ulna to Right Hand
        RevoluteJoint<Body> rightHumerusToRighthand = new RevoluteJoint<Body>(rightUlna, righthand, new Vector2(scale*1.12, scale*-0.43));
        physicWorld.addJoint(rightHumerusToRighthand);



        PrismaticJoint<Body> pistonPathJoint = new PrismaticJoint<Body>(torso, righthand, new Vector2(0, 0), new Vector2(scale*1.0, scale*0.4));
        physicWorld.addJoint(pistonPathJoint);


        // Right Humerus
        leftHumerus = new GameBody(Images.fist_hitbox);
        leftHumerus.addFixture(Geometry.createRectangle(scale*0.5, scale*0.2));
        leftHumerus.translate(new Vector2(scale*-0.609375, scale*-0.4375));

        leftHumerus.setMass(MassType.NORMAL);
        physicWorld.addBody(leftHumerus);

        // Right Ulna
       leftUlna = new GameBody(Images.fist_hitbox);
       leftUlna.addFixture(Geometry.createRectangle(scale*0.5, scale*0.07));
       leftUlna.translate(new Vector2(scale*-0.98828125, scale*-0.44040625));
       leftUlna.setMass(MassType.NORMAL);
        physicWorld.addBody(leftUlna);

        // Right elbowstopper
        lefteb = new GameBody(Images.fist_hitbox);
        lefteb.addFixture(Geometry.createRectangle(scale*0.15, scale*0.1));
        lefteb.translate(new Vector2(scale*-0.77828125, scale*-0.53040625));
        lefteb.setMass(MassType.NORMAL);
        physicWorld.addBody(lefteb);

        // Right hand
        lefthand = new GameBody(Images.fist_hitbox);
        lefthand.addFixture(Geometry.createRectangle(scale*0.4, scale*0.25));
        lefthand.translate(new Vector2(scale*-1.33828125, scale*-0.44140625));
        lefthand.setMass(MassType.NORMAL);
        physicWorld.addBody(lefthand);



        // Torso to Right Humerus
        RevoluteJoint<Body> torsoToLeftHumerus = new RevoluteJoint<Body>(torso, leftHumerus, new Vector2(scale*-0.4, scale*-0.4));
        physicWorld.addJoint(torsoToLeftHumerus);


        // Right Humerus to Right Ulna
        RevoluteJoint<Body> rightHumerusToLeftUlna = new RevoluteJoint<Body>(leftHumerus, leftUlna, new Vector2(scale*-0.82, scale*-0.43));
        physicWorld.addJoint(rightHumerusToLeftUlna);


        // Right ulna to Right eb
        WeldJoint<Body> rightHumerusToLefteb = new WeldJoint<Body>(leftUlna, lefteb, new Vector2(scale*-0.77, scale*-0.64));
        physicWorld.addJoint(rightHumerusToLefteb);


        // Right Ulna to Right Hand
        RevoluteJoint<Body> rightHumerusToLefthand = new RevoluteJoint<Body>(leftUlna, lefthand, new Vector2(scale*-1.12, scale*-0.43));
        physicWorld.addJoint(rightHumerusToLefthand);



        PrismaticJoint<Body> pistonPathJointt = new PrismaticJoint<Body>(torso, lefthand, new Vector2(0, 0), new Vector2(scale*-1.0, scale*0.4));
        physicWorld.addJoint(pistonPathJointt);


    }
    public void setup() {

       this.torso.rotate(154);
       this.righteb.rotate(154);
       this.righthand.rotate(154);
       this.rightHumerus.rotate(154);
       this.rightUlna.rotate(154);
       this.lefteb.rotate(154);
       this.lefthand.rotate(154);
       this.leftHumerus.rotate(154);
       this.leftUlna.rotate(154);


       this.torso.translate(12,4);
       this.righteb.translate(12,4);
       this.righthand.translate(12,4);
       this.rightHumerus.translate(12,4);
       this.rightUlna.translate(12,4);
       this.lefteb.translate(12,4);
       this.lefthand.translate(12,4);
       this.leftHumerus.translate(12,4);
       this.leftUlna.translate(12,4);


    }
    public void drawed(GraphicsContext gc) {
righthand.draw(gc);
        rightUlna.draw(gc);
        torso.draw(gc);
        rightHumerus.draw(gc);
        righteb.draw(gc);

        lefthand.draw(gc);
        leftUlna.draw(gc);

        leftHumerus.draw(gc);
        lefteb.draw(gc);
    }


}
