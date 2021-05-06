package application.GameObjects;

public interface Controller {
    boolean Fighter1isJUMP();
    boolean Fighter1isDUCK();
    boolean Fighter1isWALK_LEFT();
    boolean Fighter1isWALK_RIGHT();
    boolean Fighter1isPUNCH();
    boolean Fighter1isBLOCK();
    boolean Fighter1isHADOUKEN();

    boolean Fighter2isJUMP();
    boolean Fighter2isDUCK();
    boolean Fighter2isWALK_LEFT();
    boolean Fighter2isWALK_RIGHT();
    boolean Fighter2isPUNCH();
    boolean Fighter2isBLOCK();
    boolean Fighter2isHADOUKEN();


    boolean RagFighterJump();
    boolean RagFighterDuck();
    boolean RagFighterRight();
    boolean RagFighterLeft();
}
