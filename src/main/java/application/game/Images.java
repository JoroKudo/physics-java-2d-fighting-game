package application.game;


import javafx.scene.image.Image;

public class Images {
    public final static Image background = getImageSize("background.png");
    public final static Image fighter_standing = getImageSize("standing_still.gif");
    public final static Image FLOOR = getImageSize("Floor.png");



    private static Image getImage(String imagePath, double height, double width) {
        try {
            return new Image("/Images/" + imagePath, width, height, false, false);
        } catch (Exception ex) {
            throw new RuntimeException("File not found: " + imagePath);
        }
    }

    private static Image getImageSize(String imagePath) {
        try {
            Image wimg = new Image("/Images/" + imagePath);
            double h = wimg.getHeight() * (Const.BLOCK_SIZE / 40);
            double w = wimg.getWidth() * (Const.BLOCK_SIZE / 40);
            return getImage(imagePath, h, w);
        } catch (Exception ex) {
            throw new RuntimeException("File not found: " + imagePath);
        }
    }
}
