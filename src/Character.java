import java.awt.*;

public class Character {
    private GameView GameView;
    private Image[] character_images;
    private int health;
    private static final int STARTING_HEALTH = 100;
    private int costumeLevel;
    private static final int FIRST_COSTUME = 0;
    private static final int SECOND_COSTUME = 1;
    private static final int THIRD_COSTUME = 2;
    private static final int FOURTH_COSTUME = 3;
    private static final int FIFTH_COSTUME = 4;

    public Character(GameView GameView, Image[] character_images){
        this.GameView = GameView;
        this.character_images = GameView.getCharacter_images();
        this.health = STARTING_HEALTH;
    }
}
