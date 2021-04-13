package application.game;


import javafx.scene.image.Image;

public class Images {
    public final static Image background = getImageSize("background.png");
    public final static Image fighter_look_right = getImageSize("LookRight.gif");
    public final static Image fighter_Bwalk_right= getImageSize("BWalkRight.gif");
    public final static Image fighter_walk_right= getImageSize("FWalkRight.gif");
    public final static Image jump_right= getImageSize("JumpRight.gif");
    public final static Image punch_right= getImageSize("PunchRight.gif");
    public final static Image fighter_look_left = getImageSize("LookLeft.gif");
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
