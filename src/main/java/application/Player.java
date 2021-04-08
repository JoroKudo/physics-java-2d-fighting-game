package application;


import org.dyn4j.dynamics.joint.PrismaticJoint;
import org.dyn4j.dynamics.joint.RevoluteJoint;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.*;
import application.common.SimulationBody;
import application.common.SimulationFrame;
import application.common.input.BooleanStateKeyboardInputHandler;

import java.awt.*;

import java.awt.event.KeyEvent;

import java.awt.geom.Line2D;
;

/**
 * A somewhat complex scene with a ragdoll.
 * @author William Bittle
 * @since 3.2.1
 * @version 3.2.0
 */

public class Player extends SimulationFrame {


    /** The serial version id */
    private static final long serialVersionUID = -2350301592218819726L;

    /**
     * Default constructor.
     */

    private final BooleanStateKeyboardInputHandler up;
    private final BooleanStateKeyboardInputHandler down;
    private final BooleanStateKeyboardInputHandler left;
    private final BooleanStateKeyboardInputHandler right;


    public static Player simulation = new Player();


    /**
     * Custom key adapter to listen for key events.
     * @author William Bittle
     * @version 3.2.1
     * @since 3.2.0
     */

    public Player() {
        super("Ragdoll", 64.0);


        this.up = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_UP);
        this.down = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_DOWN);
        this.left = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_LEFT);
        this.right = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_RIGHT);

        this.up.install();
        this.down.install();
        this.left.install();
        this.right.install();
        this.setOffsetY(300);


    }
    public SimulationBody rightUlna;
    public SimulationBody righthand;
    public SimulationBody torso ;

    /**
     * Creates game objects and adds them to the world.
     */
    protected void initializeWorld() {
        // Ground
        SimulationBody ground = new SimulationBody();
        ground.addFixture(Geometry.createRectangle(100.0, 1.0));
        ground.translate(new Vector2(0.6875, -8.75));
        ground.setMass(MassType.INFINITE);
        world.addBody(ground);

        // the ragdoll



        // Torso
        torso = new SimulationBody();
        torso.addFixture(Geometry.createRectangle(0.5, 1.0));
        {
            Convex c = Geometry.createRectangle(1.0, 0.25);
            c.translate(new Vector2(0.00390625, 0.375));
            torso.addFixture(c);
        }
        torso.translate(new Vector2(0.0234375, -0.8125));
        torso.setMass(MassType.FIXED_ANGULAR_VELOCITY);
        world.addBody(torso);
        SimulationBody torso2 = new SimulationBody();
        torso2.addFixture(Geometry.createRectangle(0.5, 1.0));
        {
            Convex c = Geometry.createRectangle(1.0, 0.25);
            c.translate(new Vector2(0.00390625, 0.375));
            torso2.addFixture(c);
        }
        torso2.translate(new Vector2(3.3234375, -0.8125));
        torso2.setMass(MassType.NORMAL);
        world.addBody(torso2);


        // Right Humerus
        SimulationBody rightHumerus = new SimulationBody();
        rightHumerus.addFixture(Geometry.createRectangle(0.5, 0.2));
        rightHumerus.translate(new Vector2(0.609375,-0.4375));

        rightHumerus.setMass(MassType.NORMAL);
        world.addBody(rightHumerus);

        // Right Ulna
        rightUlna = new SimulationBody();
        rightUlna.addFixture(Geometry.createRectangle(0.5, 0.07));
        rightUlna.translate(new Vector2( 0.98828125,-0.44040625));
        rightUlna.setMass(MassType.NORMAL);
        world.addBody(rightUlna);

        // Right elbowstopper
        SimulationBody righteb = new SimulationBody();
        righteb.addFixture(Geometry.createRectangle(0.15, 0.1));
        righteb.translate(new Vector2( 0.77828125,-0.53040625));
        righteb.setMass(MassType.NORMAL);
        world.addBody(righteb);

        // Right hand
        righthand = new SimulationBody();
        righthand.addFixture(Geometry.createRectangle(0.4, 0.25));
        righthand.translate(new Vector2( 1.33828125,-0.44140625));
        righthand.setMass(MassType.NORMAL);
        world.addBody(righthand);


        // Left Humerus
        SimulationBody leftHumerus = new SimulationBody();
        leftHumerus.addFixture(Geometry.createRectangle(0.25, 0.5));
        leftHumerus.translate(new Vector2(-0.3828125, -0.609375));
        leftHumerus.setMass(MassType.NORMAL);
        world.addBody(leftHumerus);

        // Left Ulna
        SimulationBody leftUlna = new SimulationBody();
        leftUlna.addFixture(Geometry.createRectangle(0.25, 0.4));
        leftUlna.translate(new Vector2(-0.3828125, -0.8765625));
        leftUlna.setMass(MassType.NORMAL);
        world.addBody(leftUlna);









        // Torso to Left Humerus
        RevoluteJoint<SimulationBody> torsoToLeftHumerus = new RevoluteJoint<SimulationBody>(torso, leftHumerus, new Vector2(-0.4, -0.4));
        world.addJoint(torsoToLeftHumerus);

        // Torso to Right Humerus
        RevoluteJoint<SimulationBody> torsoToRightHumerus = new RevoluteJoint<SimulationBody>(torso, rightHumerus, new Vector2(0.4, -0.4));
        world.addJoint(torsoToRightHumerus);



        // Right Humerus to Right Ulna
        RevoluteJoint<SimulationBody> rightHumerusToRightUlna = new RevoluteJoint<SimulationBody>(rightHumerus, rightUlna, new Vector2(0.82,-0.43));
        world.addJoint(rightHumerusToRightUlna);


        // Right ulna to Right eb
        WeldJoint<SimulationBody> rightHumerusToRighteb = new WeldJoint<SimulationBody>(rightUlna, righteb, new Vector2(0.77,-0.64) );
        world.addJoint(rightHumerusToRighteb);



        // Right Ulna to Right Hand
        RevoluteJoint<SimulationBody> rightHumerusToRighthand = new RevoluteJoint<SimulationBody>( rightUlna,righthand ,new Vector2(1.12,-0.43));
        world.addJoint(rightHumerusToRighthand);

        // Left Humerus to Left Ulna
        RevoluteJoint<SimulationBody> leftHumerusToLeftUlna = new RevoluteJoint<SimulationBody>(leftHumerus, leftUlna, new Vector2(-0.4, -0.81));
        world.addJoint(leftHumerusToLeftUlna);

        PrismaticJoint<SimulationBody> pistonPathJoint = new PrismaticJoint<SimulationBody>(torso, righthand, new Vector2( 0,0), new Vector2( 1.0,0.4));
        world.addJoint(pistonPathJoint);




    }

    @Override
    protected void render(Graphics2D g, double elapsedTime) {
        super.render(g, elapsedTime);

        final double scale = this.getScale();
        final double force = 1000 * elapsedTime;

        final Vector2 r = new Vector2(rightUlna.getTransform().getRotationAngle() + Math.PI * 0.5);
        final Vector2 c = rightUlna.getWorldCenter();

        // apply thrust
        if (this.up.isActive()) {
            Vector2 f = r.product(force);
            Vector2 p = c.sum(r.product(-0.9));

            righthand.applyForce(f);

            g.setColor(Color.ORANGE);
            g.draw(new Line2D.Double(p.x * scale, p.y * scale, (p.x - f.x) * scale, (p.y - f.y) * scale));
        }
        if (this.down.isActive()) {
            Vector2 f = r.product(-force);
            Vector2 p = c.sum(r.product(0.9));

            righthand.applyForce(f);

            g.setColor(Color.ORANGE);
            g.draw(new Line2D.Double(p.x * scale, p.y * scale, (p.x - f.x) * scale, (p.y - f.y) * scale));
        }
        if (this.left.isActive()) {

            Vector2 f2 = r.product(force).left();
            Vector2 p1 = c.sum(r.product(0.9));
            Vector2 p2 = c.sum(r.product(-0.9));

            // apply a force to the top going left

            // apply a force to the bottom going right
            righthand.applyForce(f2, p2);
            torso.setLinearVelocity(0,0);

            g.setColor(Color.RED);

            g.draw(new Line2D.Double(p2.x * scale, p2.y * scale, (p2.x - f2.x) * scale, (p2.y - f2.y) * scale));
        }
        if (this.right.isActive()) {
            Vector2 f1 = r.product(force ).left();
            Vector2 f2 = r.product(force ).right();
            Vector2 p1 = c.sum(r.product(0.9));
            Vector2 p2 = c.sum(r.product(-0.9));

            // apply a force to the top going left
            righthand.applyForce(f2, p2);
            // apply a force to the bottom going right


            g.setColor(Color.RED);

            g.draw(new Line2D.Double(p2.x * scale, p2.y * scale, (p2.x - f2.x) * scale, (p2.y - f2.y) * scale));
        }
    }



    /**
     * Entry point for the example application.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        simulation.run();


    }
}
