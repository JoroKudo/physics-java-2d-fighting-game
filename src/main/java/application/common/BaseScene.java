package application.common;

import application.Navigation.Navigator;
import application.constants.Const;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class BaseScene extends Scene {
    protected final Navigator navigator;
    protected final Canvas canvas;

    protected BaseScene(Navigator navigator) {
        super(new Group());
        this.navigator = navigator;
        canvas = new Canvas(Const.CANVAS_WIDTH, Const.CANVAS_HEIGHT);
        ((Group) getRoot()).getChildren().add(canvas);
    }

    protected BaseScene(Navigator navigator, Image backgroundImage) {
        this(navigator);
        drawBackgroundImage(backgroundImage);
    }



    private void drawBackgroundImage(Image backgroundImage) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(backgroundImage, 0, 0);
    }
    //NOT USED BUT LOOKS IMPORTANT
/*
    private void drawtext(String text, int x, int y){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillText(text, x, y);
    }
    protected BaseScene(Navigator navigator, Image backgroundImage, String text, int x, int y) {
        this(navigator);
        drawBackgroundImage(backgroundImage);
        drawtext(text, x, y);
    }

    protected BaseScene(Navigator navigator, String text, int x, int y) {
        this(navigator);
        drawtext(text, x, y);
    }
*/

}
