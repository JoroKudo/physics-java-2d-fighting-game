package application.common;

import application.Navigation.Navigator;
import application.constants.Const;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class BaseScene extends Scene {
    protected final Navigator<?> navigator;
    protected final Canvas canvas;
    protected ObservableList<Node> parent;

    protected BaseScene(Navigator<?> navigator) {
        super(new Group());
        this.navigator = navigator;
        canvas = new Canvas(Const.CANVAS_WIDTH, Const.CANVAS_HEIGHT);
        parent = ((Group) getRoot()).getChildren();
        ((Group) getRoot()).getChildren().add(canvas);
    }

    protected BaseScene(Navigator<?> navigator, Image backgroundImage) {
        this(navigator);
        drawBackgroundImage(backgroundImage);
    }

    private void drawBackgroundImage(Image backgroundImage) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(backgroundImage, 0, 0);
    }

}
