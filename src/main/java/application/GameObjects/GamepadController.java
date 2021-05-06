package application.GameObjects;

public class GamepadController implements Controller {

    @Override
    public boolean isJUMP() {
        return true; // TODO
    }

    @Override
    public boolean isDUCK() {
        return true;
    }

    @Override
    public boolean isWALK_LEFT() {
        return true;
    }

    @Override
    public boolean isWALK_RIGHT() {
        return true;
    }

    @Override
    public boolean isPUNCH() {
        return true;
    }

    @Override
    public boolean isBLOCK() {
        return true;
    }

    @Override
    public boolean isHADOUKEN() {
        return true;
    }
}

