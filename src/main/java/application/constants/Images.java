package application.constants;


import javafx.scene.image.Image;

public class Images {
    //FIGHTER
    public final static Image fighter_look_right = getImageSize("fighter/LookRight.gif");
    public final static Image fighter_look_left = getImageSize("fighter/LookLeft.gif");
    public final static Image fighter_Bwalk_right = getImageSize("fighter/BWalkRight.gif");
    public final static Image fighter_Bwalk_left = getImageSize("fighter/BWalkLeft.gif");
    public final static Image fighter_walk_right = getImageSize("fighter/FWalkRight.gif");
    public final static Image fighter_walk_left = getImageSize("fighter/FWalkLeft.gif");
    public final static Image punch_right = getImageSize("fighter/PunchRight2.gif");
    public final static Image punch_left = getImageSize("fighter/PunchLeft.gif");
    public final static Image jump_right = getImageSize("fighter/JumpRight.gif");
    public final static Image jump_left = getImageSize("fighter/JumpLeft.gif");
    public final static Image block_right = getImageSize("fighter/BlockRight.gif");
    public final static Image block_left = getImageSize("fighter/BlockLeft.gif");
    public final static Image shoot_right = getImageSize("fighter/ShootRight.gif");
    public final static Image shoot_left = getImageSize("fighter/ShootLeft.gif");
    public final static Image charge_right = getImageSize("fighter/ChargeRight.gif");
    public final static Image charge_left = getImageSize("fighter/ChargeLeft.gif");
    public final static Image duck_left = getImageSize("fighter/DuckLeft.gif");
    public final static Image duck_right = getImageSize("fighter/DuckRight.gif");

    //ATTACKS
    public final static Image fist_hitbox = getImageSize("fist.png");
    public final static Image foot_hitbox = getImageSize("foot.png");
    public final static Image hadouken_right = getImageSize("fighter/Hadouken.png");
    public final static Image hadouken_left = getImageSize("fighter/HadoukenLeft.png");
    public final static Image explosion = getImageSize("fighter/hadoukenexplosion.gif");

    //UI
    public final static Image controller = getImageSize("controller.png");
    public final static Image keyboard = getImageSize("keyboard.png");
    public final static Image microphone = getImageSize("microphone.png");

    //GAME
    public final static Image background = getImageSize("background.png");
    public final static Image KO = getImageSize("KO.png");

    //SCENES
    public final static Image welcome = getImageSize("Welcome.gif");
    public final static Image GameWin = getImageSize("GameWin.gif");

    //ENVIROMENT
    public final static Image wall = getImageSize("wall.png");
    public final static Image leaderboard = getImageSize("leaderboard.png");
    public final static Image FLOOR = getImageSize("Floor.png");

    private static Image getImage(String imagePath, double height, double width) {
        try {
            return new Image("/Images/" + imagePath, width, height, false, false);
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
            Image measureImage = new Image("/Images/" + imagePath);
            double h = measureImage.getHeight() * multiplier;
            double w = measureImage.getWidth() * multiplier;
            return getImage(imagePath, h, w);
        } catch (Exception ex) {
            throw new RuntimeException("File not found: " + imagePath);
        }
    }
}
