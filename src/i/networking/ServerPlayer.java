package i.networking;

import i.enums.CardShape;
import i.enums.CardinalDirection;
import i.enums.PlayerID;
import i.enums.SpellID;
import i.game.PlayerRepresentative;
import i.tile.PropertyTile;
import i.tile.Tile;

import java.rmi.RemoteException;

public class ServerPlayer implements PlayerRepresentative {
    private Coordinator coordinator;
    private int questionNumber;
    private int playerNumber;

    public ServerPlayer(Coordinator c, int playerID) {
        coordinator = c;
        playerNumber = playerID;
        questionNumber = 0;
    }

//    @Override
//    public Operation yourTurn(int turnNo) throws RemoteException {
//        System.out.println("Asking the server what the player chose...");
//        return coordinator.whatHappened(turnNo);
//    }

    @Override
    public SpellID getSpellCast(SpellID[] availableSpells)
            throws RemoteException {
        System.out.println("getSpellCast Asking the server what the player chose...");
        SpellID result = (SpellID) coordinator.whatHappened(playerNumber, questionNumber);
        questionNumber++;
        return result;
    }

    @Override
    public int getUsersRoll() throws RemoteException {
        System.out.println("getUsersRoll Asking the server what the player chose...");
        int result = (int) coordinator.whatHappened(playerNumber, questionNumber);
        questionNumber++;
        return result;
    }

    @Override
    public CardinalDirection forkInTheRoad(CardinalDirection[] availableDirections) throws RemoteException {
        System.out.println("forkInTheRoad Asking the server what the player chose...");
        CardinalDirection result = (CardinalDirection) coordinator.whatHappened(playerNumber, questionNumber);
        questionNumber++;
        return result;
    }

    @Override
    public boolean buyThisTile(PropertyTile tileForPurchase)
            throws RemoteException {
        System.out.println("buyThisTile Asking the server what the player chose...");
        boolean result = (boolean) coordinator.whatHappened(playerNumber, questionNumber);
        questionNumber++;
        return result;
    }

    @Override
    public CardShape placeWhichCard()
            throws RemoteException {
        System.out.println("placeWhichCard Asking the server what the player chose...");
        CardShape result = (CardShape) coordinator.whatHappened(playerNumber, questionNumber);
        questionNumber++;
        return result;
    }

    @Override
    public CardShape swapCardOnThisTile(PropertyTile tileForSwapping)
                    throws RemoteException {
        System.out.println("swapCardOnThisTile Asking the server what the player chose...");
        CardShape result = (CardShape) coordinator.whatHappened(playerNumber, questionNumber);
        questionNumber++;
        return result;
    }

    @Override
    public Tile swapCardOnWhichTile()
            throws RemoteException {
        System.out.println("swapCardOnWhichTile Asking the server what the player chose...");
        Tile result = (Tile) coordinator.whatHappened(playerNumber, questionNumber);
        questionNumber++;
        return result;
    }

    @Override
    public Tile upgradeWhichTile(PropertyTile[] upgradeableTiles)
            throws RemoteException {
        System.out.println("upgradeWhichTileAsking the server what the player chose...");
        Tile result = (Tile) coordinator.whatHappened(playerNumber, questionNumber);
        questionNumber++;
        return result;
    }

    @Override
    public int upgradeToWhatLevel(PropertyTile upgradingTile)
            throws RemoteException {
        System.out.println("upgradeToWhatLevel Asking the server what the player chose...");
        int result = (int) coordinator.whatHappened(playerNumber, questionNumber);
        questionNumber++;
        return result;
    }

    @Override
    public PropertyTile sellWhichTile(PlayerID sellingPlayer)
            throws RemoteException {
        System.out.println("sellWhichTile Asking the server what the player chose...");
        PropertyTile result = (PropertyTile) coordinator.whatHappened(playerNumber, questionNumber);
        questionNumber++;
        return result;
    }

    @Override
    public PlayerID castOnPlayer(SpellID spellCast)
            throws RemoteException {
        System.out.println("castOnPlayer Asking the server what the player chose...");
        PlayerID result = (PlayerID) coordinator.whatHappened(playerNumber, questionNumber);
        questionNumber++;
        return result;
    }
}
