package application.constants;


import javafx.scene.image.Image;

public class Images {

    public final static Image fighter_look_right = getImageSize("fighter/LookRight.gif");
    public final static Image fighter_Bwalk_right= getImageSize("fighter/BWalkRight.gif");
    public final static Image fighter_walk_right= getImageSize("fighter/FWalkRight.gif");
    public final static Image jump_right= getImageSize("fighter/JumpRight.gif");
    public final static Image fighter_Bwalk_left= getImageSize("fighter/BWalkLeft.gif");
    public final static Image fighter_walk_left= getImageSize("fighter/FWalkLeft.gif");
    public final static Image punch_right= getImageSize("fighter/PunchRight2.gif");
    public final static Image punch_left= getImageSize("fighter/PunchLeft.gif");
    public final static Image fighter_look_left = getImageSize("fighter/LookLeft.gif");
    public final static Image FLOOR = getImageSize("Floor.png");
    public final static Image fist_hitbox = getImageSize("fist.png");
    public final static Image foot_hitbox = getImageSize("foot.png");
    public final static Image block = getImageSize("fighter/Block.gif");
    public final static Image Game_over = getImageSize("GameOver.png");
    public final static Image hadouken = getImageSize("fighter/Hadouken.png");
    public final static Image shootright = getImageSize("fighter/ShootRight.gif");
    public final static Image chargeright = getImageSize("fighter/RightCharge.gif");
    public final static Image expolsion = getImageSize("fighter/hadoukenexplosion.gif");

    public final static Image duck_right= getImageSize("fighter/DuckRight.gif");
    public final static Image background = getImageSize("background.png");
    public final static Image KO = getImageSize("KO.png");
    public final static Image welcome = getImageSize("Welcome.gif");
    public final static Image GameWin = getImageSize("GameWin.gif");


    private static Image getImage(String imagePath, double height, double width) {
        try {
            return new Image("/Images/" + imagePath, width, height, false, false);
        } catch (Exception ex) {
            throw new RuntimeException("File not found: " + imagePath);
        }
    }

    private static Image getImageSize(String imagePath) {
        String firstFourChars = "";

        if (imagePath.length() > 8)
        {
            firstFourChars = imagePath.substring(0, 8);
        }
        else
        {
            firstFourChars = imagePath;
        }
        if(firstFourChars.equals("fighter/")) {
            try {
                Image wimg = new Image("/Images/" + imagePath);
                double h = wimg.getHeight()*2 ;
                double w = wimg.getWidth()*2 ;
                return getImage(imagePath, h, w);
            } catch (Exception ex) {
                throw new RuntimeException("File not found: " + imagePath);
            }
        }
        else{
        try {
            Image wimg = new Image("/Images/" + imagePath);
            double h = wimg.getHeight() ;
            double w = wimg.getWidth() ;
            return getImage(imagePath, h, w);
        } catch (Exception ex) {
            throw new RuntimeException("File not found: " + imagePath);
        }
    }}
}
