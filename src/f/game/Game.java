package f.game;

import java.rmi.RemoteException;

import f.operations.MultiplyAmount;
import f.operations.Operation;

public class Game {
    public final static int numPlayers = 4;
    public final static int startingNumber = 5;

    private int gameNumber;
    private final Player[] players;
    private int turnNumber;

    // Creates a game with all-local players
    public Game() {
        turnNumber = 0;
        gameNumber = startingNumber;
        Player[] thePlayers = {new LocalPlayer(), new LocalPlayer(),
                               new LocalPlayer(), new LocalPlayer()};
        players = thePlayers; // Array constants can only be used in initializers
    }

    public void start() {
        System.out.println("LET THE GAMES BEGIN!");
        while (turnNumber < 20) {
            nextTurn();
        }
        System.out.println("\nAND SO THE GAME COMES TO ITS EXCITING CONCLUSION!");
        System.out.print("Ladies, Gentlemen and all other readers, our concluding ");
        System.out.println("Game Number is...!\n");
        System.out.print(gameNumber);
        System.out.print("!!\n\nThank you all for coming, we hope you enjoyed the show");
        System.out.println(" and to see you again next time!");
    }


    public void nextTurn() {
        System.out.println("\nIt's Turn "+String.valueOf(turnNumber)+": ");

        // Discern who's turn it is
        int whoseTurn = turnNumber % players.length;
        System.out.println("Player "+String.valueOf(whoseTurn)+"'s turn!");

        // Tell that player to take their turn
        Operation playerSelection;
        try {
            playerSelection = players[whoseTurn].yourTurn(turnNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
            playerSelection = new MultiplyAmount(2);
        }
        System.out.println("The Player has selected!");
        System.out.println("The Game Number was "+String.valueOf(gameNumber)+", ");

        // Execute their chosen operation
        gameNumber = playerSelection.execute(gameNumber);
        System.out.println("And now the game number is "+String.valueOf(gameNumber)+"!");

        // The turn is over
        turnNumber++;
        System.out.println("Player "+String.valueOf(whoseTurn)+"'s turn is over!");
    }
}
