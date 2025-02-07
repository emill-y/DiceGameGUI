import java.awt.*;
import java.util.Arrays;

public class Die
{
    /** Instance Variables **/

    private int numSides;
    private GameView GameView;
    private int value;
    private final static int DIE_SIDE = 45;

    /** Constructors **/

    public Die(int numSides, GameView GameView) {
        // NOTE: if the user enters an int less than 2
        // set numSides to 6.
        if (numSides < 2) {
            this.numSides = 6;
        }
        else {
            this.numSides = numSides;
        }
        this.GameView = GameView;
        this.value = 0;

    }

    public Die(GameView GameView) {
        this.GameView = GameView;
        numSides = 6;
        this.value = 0;
    }

    /** Methods **/

    /**
     * Returns the number of sides on the Die.
     */
    public int getSides() {
        return numSides;
    }

    /**
     * Returns a random int between 1 and 
     * the number of sides on the Die
     */
    public int roll() {
        int roll = (int) (Math.random() * numSides + 1);
        this.value = roll;
        return roll;
    }

    public int getValue(){
        return value;
    }

    //displays an image of the die rolled
    public char dieface(int roll) {
        char[] diefaces = {'⚀','⚁','⚂','⚃','⚄','⚅'};
        return diefaces[roll - 1];
    }

    public void draw(Graphics g){
        int die_cornerx = (int)(Math.random() * 500) + 200;
        int die_cornery = (int)(Math.random() * 500) + 200;
        g.drawImage(GameView.getDie_images()[roll()-1], die_cornerx, die_cornery, DIE_SIDE, DIE_SIDE, GameView);
    }

    /**
     * Rolls the dice the numRolls times 
     * and returns the max value of the rolls
     */
    public int getMaxRoll(int numRolls) {
        int MaxRoll = 0;
        int currentRoll = 0;
        for (int i = 0; i < numRolls; i++) {
            currentRoll = roll();

            if (currentRoll > MaxRoll) {
                MaxRoll = currentRoll;
            }
        }
        return MaxRoll;
    }

    //calculates the sum of all rolls, given a certain number of die
    public int getSumofRolls(int numRolls) {
        int sumofrolls = 0;
        int currentRoll = 0;
        for (int i = 0; i < numRolls; i++){
            currentRoll = roll();
            sumofrolls += currentRoll;
        }
        return sumofrolls;
    }

    //calculates the median of all roles, if given a certain number of die
    public int getMedianofRolls(int numRolls) {

        int allrolls[] = new int[numRolls];

        for (int i = 0; i < numRolls; i++){
            allrolls[i] = roll();
        }

        //sorts array in ascending order
        Arrays.sort(allrolls);

        return allrolls[numRolls/2];

    }

    /**
     * Returns a String in the following form:
     * "This is a n-sided die."
     */
    public String toString() {
        return "This is a " + numSides + " sided die.";
    }
}