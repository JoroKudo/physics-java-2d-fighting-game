package application.constants;


import javafx.scene.image.Image;

public class Images {
    //FIGHTER
    public final static Image fighter_look_right = getImageSize("fighter/lookRight.gif");
    public final static Image fighter_look_left = getImageSize("fighter/lookLeft.gif");
    public final static Image fighter_Bwalk_right = getImageSize("fighter/bWalkRight.gif");
    public final static Image fighter_Bwalk_left = getImageSize("fighter/bWalkLeft.gif");
    public final static Image fighter_walk_right = getImageSize("fighter/fWalkRight.gif");
    public final static Image fighter_walk_left = getImageSize("fighter/fWalkLeft.gif");
    public final static Image punch_right = getImageSize("fighter/punchRight.gif");
    public final static Image punch_left = getImageSize("fighter/punchLeft.gif");
    public final static Image jump_right = getImageSize("fighter/jumpRight.gif");
    public final static Image jump_left = getImageSize("fighter/jumpLeft.gif");
    public final static Image block_right = getImageSize("fighter/blockRight.gif");
    public final static Image block_left = getImageSize("fighter/blockLeft.gif");
    public final static Image shoot_right = getImageSize("fighter/shootRight.gif");
    public final static Image shoot_left = getImageSize("fighter/shootLeft.gif");
    public final static Image charge_right = getImageSize("fighter/chargeRight.gif");
    public final static Image charge_left = getImageSize("fighter/chargeLeft.gif");
    public final static Image duck_left = getImageSize("fighter/duckLeft.gif");
    public final static Image duck_right = getImageSize("fighter/duckRight.gif");

    //ATTACKS
    public final static Image hitbox_fist = getImageSize("fist.png");
    public final static Image hitbox_stomp = getImageSize("foot.png");
    public final static Image hadoken_right = getImageSize("fighter/hadoken.png");
    public final static Image hadoken_left = getImageSize("fighter/hadokenLeft.png");
    public final static Image explosion = getImageSize("fighter/hadokenExplosion.gif");

    //UI
    public final static Image controller = getImageSize("controller.png");
    public final static Image keyboard = getImageSize("keyboard.png");
    public final static Image microphone = getImageSize("microphone.png");
    public final static Image ko = getImageSize("ko.png");
    public final static Image logo = getImageSize("logo.png");

    //Animations
    //public final static Image ko_animation = getImageSize("KO_anim.gif");

    //SCENES
    public final static Image welcome = getImageSize("welcome.gif");
    public final static Image background = getImageSize("background.png");
    public final static Image gameWin = getImageSize("gameWin.png");
    public final static Image leaderboard = getImageSize("leaderboard.png");

    //ENVIRONMENTS
    public final static Image wall = getImageSize("wall.png");
    public final static Image floor = getImageSize("floor.png");

    private static Image getImage(String imagePath, double height, double width) {
        try {
            return new Image("/images/" + imagePath, width, height, false, false);
        } catch (Exception ex) {
            throw new RuntimeException("File not found: " + imagePath);
        }
    }

    private static Image getImageSize(String imagePath) {
        String firstFourChars;
        if (imagePath.length() > 8) {
            firstFourChars = imagePath.substring(0, 8);
        } else {
            firstFourChars = imagePath;
        }
        int multiplier = 1;
        if (firstFourChars.equals("fighter/")) multiplier = 2;
        try {
            Image measureImage = new Image("/images/" + imagePath);
            double h = measureImage.getHeight() * multiplier;
            double w = measureImage.getWidth() * multiplier;
            return getImage(imagePath, h, w);
        } catch (Exception ex) {
            throw new RuntimeException("File not found: " + imagePath);
        }
    }
}
