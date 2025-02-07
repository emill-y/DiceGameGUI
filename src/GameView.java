import javax.swing.*;
import java.awt.*;

import static java.awt.Font.SANS_SERIF;

public class GameView extends JFrame {
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private final int TITLE_BAR_HEIGHT = 23;
    private Game backend;
    private Image[] die_images;
    private Die playerDie;
    private Die gameDie;
    private Image[] character_images;
    private Image[] item_images;
    private static boolean isInstructions = true;


    public GameView(Game backend){
        // Store backend reference
        this.backend = backend;
        //initialize die
        this.playerDie = backend.getPlayersDie();
        this.gameDie = backend.getDealersDie();

        // Initialize die graphics
        this.die_images = new Image[6];
        for(int i = 0; i < 6; i++){
            die_images[i] = new ImageIcon("Resources/" + (i+1) + ".png").getImage();
        }
        this.character_images = new Image[5];
        for(int i = 0; i < 5; i++) {
            character_images[i] = new ImageIcon("Resources/costume" + i + ".png").getImage();
        }

        this.item_images = new Image[4];
        String[] items = {"tea", "meal", "drink", "pocky"};
        for(int i = 0; i < 4; i++) {
            item_images[i] = new ImageIcon("Resources/" + items[i] + ".png").getImage();
        }
        // Routine Window operations
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Growing Up Dice Game!");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void setDie(Die playerDie, Die dealerDie){
        this.playerDie = playerDie;
        this.gameDie = dealerDie;
    }

    public Image[] getDie_images(){
        return die_images;
    }

    public Image[] getCharacter_images(){
        return character_images;
    }

    public Image[] getItem_images(){
        return item_images;
    }

    public void displayInstructions(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Header Font", Font.BOLD, 45 ));
        g.drawString("Welcome to Growing Up Dice!!!", 250, 1500);
        g.setFont(new Font("Body Font", Font.PLAIN, 20 ));
        g.drawString("Click a button 2 get started :)", 200, 500);
    }

    public void paint(Graphics g){
        // Clear the background
        setDie(backend.getPlayersDie(), backend.getDealersDie());
        g.setColor(Color.white);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        //display instructions
        if (isInstructions){
            displayInstructions(g);
        }
        //Draw the die
        playerDie.draw(g);
        gameDie.draw(g);

    }
}
