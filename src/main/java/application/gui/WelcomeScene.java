package application.gui;

import application.Navigation.Navigator;
import application.common.KeyEventHandler;
import application.common.*;
import application.main.Welcome;

public class WelcomeScene extends BaseScene implements Initializable {
    public WelcomeScene(Navigator navigator) {
        super(navigator);
    }

    @Override
    public void  onInitialize() {
        KeyEventHandler keyEventHandler = new KeyEventHandler();
        this.setOnKeyPressed(keyEventHandler);
        this.setOnKeyReleased(keyEventHandler);

        Welcome welcome= new Welcome(keyEventHandler, navigator);
        FancyAnimationTimer gameLoop = new FancyAnimationTimer() {
            @Override
            public void doHandle(double deltaInSec) {
                welcome.update(deltaInSec);
                welcome.draw(canvas.getGraphicsContext2D());
            }
        };
        gameLoop.start();


    }




}
