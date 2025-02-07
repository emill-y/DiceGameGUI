//Eisha Yadav, CS2
//Kingdom's Dice
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
public class Game implements ActionListener {

    private static GameView GameView;
    private Die playersDie;
    private Die dealersDie;

    public Game(){
        this.GameView = new GameView(this);
        //initialize die
        playersDie = new Die(GameView);
        dealersDie = new Die(GameView);

    }

    public Die getPlayersDie(){
        return playersDie;
    }

    public Die getDealersDie(){
        return dealersDie;
    }

    static void gameinstructions() {
        //Share game instructions
        System.out.println("🏰 Welcome to Kingdom's Dice!! Ready to gamble away your wealth at the luck of the roll? Let's start playing!");
        System.out.println();
        System.out.println("The rules of the game are simple. First, decide how much of your cash you want to bet. Then the dealer will roll die, and you will roll die. In most versions (other than version 3) your die's value is HIGHER than the dealers, you win! That means you get all of the crowns you bet added into your bank account.");
        System.out.println();
        System.out.println("If you lose, bummer. All of the crowns you bet is taken by the dealer!! Make wiser bet's next time:). The game continues until you have no money (👑 ) left, or when you have reached the ultimate cash total of 1000 crowns.");
        System.out.println("\n-------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Game game = new Game();

        GameView.repaint();

        //Share game instructions
        gameinstructions();

        //Get player name
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want your players name to be?");
        String playerName = scanner.nextLine();

        //Share different game levels
        System.out.println("What game version do you want to play? \n Version 1: The winner is the one whose individual die has the highest value! \n Version 2: The winner is the one whose set of die have the highest sum. \n Version 3: Kinda like Blackjack, whoever gets closer to the typical roll amount, which is 3 * the number of die, wins! \n Version 4: For stats lovers, the winner is the one with the highest median of the die rolled!");
        System.out.println("\nJust type out the number of your desired game level.");

        //allows user to input the level of game they want to play
        int level = scanner.nextInt();
        scanner.nextLine();
        //ensures that user inputs a valid level
        while (level > 5 || level < 0) {
            System.out.println("Looks like you are trying to play a level that doesn't exist! Try entering a level from 1-4");
            level = scanner.nextInt();
        }

        int numofDie = 0;
        //if user is playing a level that needs multiple die, asks user how many die they wish to use
        if (level > 1) {
            System.out.println("How many die do you want to play with? (Keep it less than 50!)");
            numofDie = scanner.nextInt();
            scanner.nextLine();
            //ensures that user inputs a valid number of die
            while (1 > numofDie || numofDie > 51) {
                System.out.println("Looks like you are trying to play with more than 50 die! Try inputting a number of die between 1 and 50");
                numofDie = scanner.nextInt();
            }
        }

        //initializes a new player, and the dealer (aka the game)
        Player player = new Player(playerName, level, GameView);
        Player dealer = new Player("Dealer", level, GameView);

        //prompts user to clear screen
        System.out.println("\nHit ENTER to continue!");
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        //continues to run while the game is still occuring, when game is ended, stops
        while (player.stillPlaying() == true) {

            //tell the player their current amount of crowns
            System.out.println("\n" + player.returnCash());

            //ask the player for their bet
            System.out.println("\nHow much 👑  would you like to bet?");
            int bet = scanner.nextInt();
            //check if players bet is valid
            while (bet > player.playersCash())
            {
                System.out.println("Looks like you are betting money you don't have! Try betting again- within your budget.");
                bet = scanner.nextInt();
            }
            scanner.nextLine();
            //simulate rolling the die
            System.out.println("\nrolling die... 🎲");

            int playersScore = 0;
            int dealersScore = 0;

            //give the player a different score depending on the game played

            //if game one, just compare the base value of the players rolls
            if (player.playerLevel() == 1) {

                //roll the players die
                playersScore = game.playersDie.roll();
                //display players score as a die
                System.out.println("\n" + game.playersDie.dieface(playersScore));
                //roll the dealers die
                dealersScore = game.dealersDie.roll();

                GameView.repaint();
                //share score to user
                System.out.println("\nYou rolled a " + playersScore);
                //display dealers score as a die
                System.out.println("\n" + game.dealersDie.dieface(dealersScore));
                //share dealers score to user
                System.out.println("\nThe dealer rolled a " + dealersScore);

            }
            //if game two, compare the sum of players rolls (out of all of their die)
            else if (player.playerLevel() == 2){
                playersScore = game.playersDie.getSumofRolls(numofDie);
                dealersScore = game.dealersDie.getSumofRolls(numofDie);
                System.out.println("\nThe sum of your rolls is " + playersScore);
                System.out.println("\nThe sum of the dealers rolls is " + dealersScore);

            }

            //if game three, compare the distance of player rolls from 3*the number of die they are using, whoever is closer wins
            else if (player.playerLevel() == 3) {
                int triplenumofDie = numofDie*3;
                playersScore = Math.abs((triplenumofDie) - game.playersDie.getSumofRolls(numofDie));
                dealersScore = Math.abs((triplenumofDie) - game.dealersDie.getSumofRolls(numofDie));
                System.out.println("\nThe sum of your rolls is  " + playersScore + " away from " + triplenumofDie);
                System.out.println("\nThe sum of the dealers rolls is  " + dealersScore + " away from " + triplenumofDie);
                //swap scores, because in this one the smaller number wins, but the scoring mechanism is looking for higher number wins
                int temp = playersScore;
                playersScore = dealersScore;
                dealersScore = temp;

            }
            //if game four, compare the median of the players and the dealers rolls
            else if (player.playerLevel() == 4) {
                playersScore = game.playersDie.getMedianofRolls(numofDie);
                dealersScore = game.dealersDie.getMedianofRolls(numofDie);
                System.out.println("\nThe median of your rolls is  " + playersScore);
                System.out.println("\nThe median of the dealers rolls is  " + dealersScore);
            }

            //compare score values and change players cash total
            if (dealersScore > playersScore) {
                System.out.println("\nBummer..looks like the dealer beat you.. Taking crowns from your stash...");
                player.loseMoney(bet);
                dealer.winMoney(bet);
            }
            else if (dealersScore < playersScore) {
                System.out.println("\nNice!! You won this round. Adding crowns...");
                dealer.loseMoney(bet);
                player.winMoney(bet);
            }
            else {
                System.out.println("Looks like you and the dealer are in sync! Nothing happens to you crowns:)");
            }

            //prompt user to clear screen
            System.out.println("\nHit ENTER to continue!");
            scanner.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();

        }

        //check to see if game is won or lost, and share message with player
        if (player.playersCash() >= 1000) {
            System.out.println("\nWOW! 👑👑👑 You won the game:) Looks like you have awesome dice luck!!");
        }
        else {
            System.out.println("\noops.. looks like you gambled away all of your crowns... Better luck next time!");
        }

    }
    public void actionPerformed(ActionEvent e) {

    }
}