package application.constants;

import javafx.scene.image.Image;

public class Images {

    public final static Image background = getImage("background");
    public final static Image fighter_standing = getImage("standing_still");


    private static Image getImage(String imagePath) {
        return new Image("/Images/" + imagePath + ".gif");
    }

}
