package application.constants;

import javafx.scene.image.Image;

public class Images {

    public final static Image background = getImage("background.gif");

    private static Image getImage(String imagePath) {
        return new Image("/Images/" + imagePath);
    }

}
