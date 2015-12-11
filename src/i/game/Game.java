package i.game;

import i.enums.CardShape;
import i.enums.CardinalDirection;
import i.enums.PlayerID;
import i.enums.SpellID;
import i.tile.NullTile;
import i.tile.PropertyTile;
import i.tile.Tile;

import java.rmi.RemoteException;


public class Game {
    public final static int numPlayers = 4;
    private final PlayerRepresentative[] players;
    private int turnNumber;
    private int questionNumber;

    // Default responses
    private static final int defaultResponse1               = 0;
    private static final boolean defaultResponse2           = false;
    private static final Tile defaultResponse3              = new NullTile();
    private static final PropertyTile defaultResponse4      = new PropertyTile(-1, -1, -1);
    private static final PlayerID defaultResponse5          = PlayerID.NOPLAYER;
    private static final SpellID defaultResponse6           = SpellID.NOSPELL;
    private static final CardShape defaultResponse7         = CardShape.NOCARD;
    private static final CardinalDirection defaultResponse8 = CardinalDirection.NONE;

    // Creates a game with all-local players
    public Game() {
        questionNumber = 0;
        turnNumber = 0;
        PlayerRepresentative[] thePlayers = {new LocalPlayer(), new LocalPlayer(),
                                             new LocalPlayer(), new LocalPlayer()};
        players = thePlayers; // Array constants can only be used in initializers
    }

    // Creates a game with predetermined players
    public Game(PlayerRepresentative[] thePlayers) {
        questionNumber = 0;
        turnNumber = 0;
        players = thePlayers;
    }

    public void start() {
        System.out.println("LET THE GAMES BEGIN!");
        while (turnNumber < 20) {
            nextTurn();
        }
        System.out.println("\nAND SO THE GAME COMES TO ITS EXCITING CONCLUSION!");
        System.out.print("Thank you all for coming, we hope you enjoyed the show");
        System.out.println(" and to see you again next time!");
    }

    public void nextTurn() {
        System.out.println("\nIt's Turn "+String.valueOf(turnNumber)+": ");

        // Discern who's turn it is
        int whoseTurn = turnNumber % players.length;
        PlayerRepresentative player = players[whoseTurn];
        System.out.println("Player "+String.valueOf(whoseTurn)+"'s turn!");

        // Ask that player a series of questions
        int response1               = defaultResponse1;
        boolean response2           = defaultResponse2;
        Tile response3              = defaultResponse3;
        PropertyTile response4      = defaultResponse4;
        PlayerID response5          = defaultResponse5;
        SpellID response6           = defaultResponse6;
        CardShape response7         = defaultResponse7;
        CardinalDirection response8 = defaultResponse8;
        try {
            response1 = player.getUsersRoll(questionNumber);
            questionNumber++;
            response2 = player.buyThisTile(questionNumber, null);
            questionNumber++;

            response3 = player.upgradeWhichTile(questionNumber, null);
            questionNumber++;
            response4 = player.sellWhichTile(questionNumber, null);
            questionNumber++;

            response5 = player.castOnPlayer(questionNumber, null);
            questionNumber++;
            response6 = player.getSpellCast(questionNumber, null);
            questionNumber++;
            response7 = player.placeWhichCard(questionNumber);
            questionNumber++;
            response8 = player.forkInTheRoad(questionNumber, null);
            questionNumber++;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("Player "+String.valueOf(whoseTurn)+
                           " on Turn "+String.valueOf(turnNumber)+":");

        // Print their selection
        System.out.println("r1: "+String.valueOf(response1));
        System.out.println("r2: "+String.valueOf(response2));
        System.out.println("r3: ("+String.valueOf(response3.getX())+
                              ", "+String.valueOf(response3.getY())+")");
        System.out.println("r4: ("+String.valueOf(response4.getX())+
                              ", "+String.valueOf(response4.getY())+")");
        System.out.println("r5: "+String.valueOf(response5));
        System.out.println("r6: "+String.valueOf(response6));
        System.out.println("r7: "+String.valueOf(response7));
        System.out.println("r8: "+String.valueOf(response8));

        // The turn is over
        turnNumber++;
        System.out.println("Player "+String.valueOf(whoseTurn)+"'s turn is over!");
    }
}
