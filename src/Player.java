public class Player {

    //instance variables
    private String playerName;
    private int playerCash;
    private int playerLevel;
    private GameView GameView;

    //constructor
    public Player(String playerName, int playerCash, int level) {
        this.playerName = playerName;
        this.playerCash = playerCash;
        playerLevel = level;
    }

    //default constructor
    public Player() {
        this.playerName = "Guest Player.";
        this.playerCash = 100;
        playerLevel = 1;
    }

    //starting game constructor
    public Player(String playerName, int level, GameView GameView) {
        this.GameView = GameView;
        if (playerName.length() < 1) {
            this.playerName = "Guest Player";
        }
        else {
            this.playerName = playerName;
        }
        this.playerCash = 100;
        playerLevel = level;
    }

    //Individual Methods

    //returns the players level
    public int playerLevel(){
        return playerLevel;
    }

    //returns the amount of cash the player has
    public int playersCash(){
        return playerCash;
    }

    //deposits money won by the player into their cash total
    public int winMoney(int moneyGained) {
        playerCash = playerCash + moneyGained;
        return playerCash;
    }

    //removes money earned by the player from their cash total
    public int loseMoney(int moneyLost) {
        playerCash = playerCash - moneyLost;
        return playerCash;
    }

    // returns the amount of money the player has
    public String returnCash() {
        return playerName + ", you currently have " + playerCash + " 👑.";
    }

    //checks if the player has won or lost the game
    public boolean stillPlaying() {

        boolean stillPlaying = false;

        if (playerCash > 0 && playerCash <= 1000) {
            stillPlaying = true;
        }

        return stillPlaying;
    }




























}