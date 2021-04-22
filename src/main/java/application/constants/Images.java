package application.constants;


import javafx.scene.image.Image;

public class Images {
    public final static Image background = getImageSize("background.png");
    public final static Image fighter_look_right = getImageSize("LookRight.gif");
    public final static Image fighter_Bwalk_right= getImageSize("BWalkRight.gif");
    public final static Image fighter_walk_right= getImageSize("FWalkRight.gif");
    public final static Image jump_right= getImageSize("JumpRight.gif");
    public final static Image punch_right= getImageSize("PunchRight.gif");
    public final static Image duck_right= getImageSize("DuckRight.gif");
    public final static Image fighter_look_left = getImageSize("LookLeft.gif");
    public final static Image FLOOR = getImageSize("Floor.png");
    public final static Image fist_hitbox = getImageSize("fist.png");
    public final static Image foot_hitbox = getImageSize("foot.png");
    public final static Image block = getImageSize("Block.gif");
    public final static Image KO = getImageSize("KO.png");
    public final static Image welcome = getImageSize("Welcome.gif");
    public final static Image Game_over = getImageSize("GameOver.png");
    public final static Image hadouken = getImageSize("Hadouken.png");
    public final static Image GameWin = getImageSize("GameWin.gif");


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
