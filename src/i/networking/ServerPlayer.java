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

    public ServerPlayer(Coordinator c) {
        coordinator = c;
    }

//    @Override
//    public Operation yourTurn(int turnNo) throws RemoteException {
//        System.out.println("Asking the server what the player chose...");
//        return coordinator.whatHappened(turnNo);
//    }

    @Override
    public SpellID getSpellCast(int questionNumber, SpellID[] availableSpells)
            throws RemoteException {
        System.out.println("getSpellCast Asking the server what the player chose...");
        return (SpellID) coordinator.whatHappened(questionNumber);
    }

    @Override
    public int getUsersRoll(int questionNumber) throws RemoteException {
        System.out.println("getUsersRoll Asking the server what the player chose...");
        return (int) coordinator.whatHappened(questionNumber);
    }

    @Override
    public CardinalDirection forkInTheRoad(int questionNumber,
            CardinalDirection[] availableDirections) throws RemoteException {
        System.out.println("forkInTheRoad Asking the server what the player chose...");
        return (CardinalDirection) coordinator.whatHappened(questionNumber);
    }

    @Override
    public boolean buyThisTile(int questionNumber, PropertyTile tileForPurchase)
            throws RemoteException {
        System.out.println("buyThisTile Asking the server what the player chose...");
        return (boolean) coordinator.whatHappened(questionNumber);
    }

    @Override
    public CardShape placeWhichCard(int questionNumber) throws RemoteException {
        System.out.println("placeWhichCard Asking the server what the player chose...");
        return (CardShape) coordinator.whatHappened(questionNumber);
    }

    @Override
    public CardShape swapCardOnThisTile(int questionNumber,
            PropertyTile tileForSwapping) throws RemoteException {
        System.out.println("swapCardOnThisTile Asking the server what the player chose...");
        return (CardShape) coordinator.whatHappened(questionNumber);
    }

    @Override
    public Tile swapCardOnWhichTile(int questionNumber) throws RemoteException {
        System.out.println("swapCardOnWhichTile Asking the server what the player chose...");
        return (Tile) coordinator.whatHappened(questionNumber);
    }

    @Override
    public Tile upgradeWhichTile(int questionNumber,
            PropertyTile[] upgradeableTiles) throws RemoteException {
        System.out.println("upgradeWhichTileAsking the server what the player chose...");
        return (Tile) coordinator.whatHappened(questionNumber);
    }

    @Override
    public int upgradeToWhatLevel(int questionNumber, PropertyTile upgradingTile)
            throws RemoteException {
        System.out.println("upgradeToWhatLevel Asking the server what the player chose...");
        return (int) coordinator.whatHappened(questionNumber);
    }

    @Override
    public PropertyTile sellWhichTile(int questionNumber, PlayerID sellingPlayer)
            throws RemoteException {
        System.out.println("sellWhichTile Asking the server what the player chose...");
        return (PropertyTile) coordinator.whatHappened(questionNumber);
    }

    @Override
    public PlayerID castOnPlayer(int questionNumber, SpellID spellCast)
            throws RemoteException {
        System.out.println("castOnPlayer Asking the server what the player chose...");
        return (PlayerID) coordinator.whatHappened(questionNumber);
    }
}
