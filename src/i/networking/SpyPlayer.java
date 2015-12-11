package i.networking;

import i.enums.CardShape;
import i.enums.CardinalDirection;
import i.enums.PlayerID;
import i.enums.SpellID;
import i.game.PlayerRepresentative;
import i.tile.PropertyTile;
import i.tile.Tile;

import java.rmi.RemoteException;

public class SpyPlayer implements PlayerRepresentative {
    private Coordinator myBoss;
    private PlayerRepresentative myTarget;

    /** Creates a Spy that reports all of the player's actions back
     * to the coordinator.
     *
     * @param boss The Coordinator receiving the player's actions.
     * @param target The Player being spied on.
     */
    public SpyPlayer(Coordinator boss, PlayerRepresentative target) {
        myBoss = boss;
        myTarget = target;
    }

    @Override
    public SpellID getSpellCast(int questionNumber, SpellID[] availableSpells)
            throws RemoteException {

        // Tell the player it's their turn but intercept their response
        SpellID theirChoice = myTarget.getSpellCast(questionNumber, availableSpells);

        // Report back to HQ
        System.out.println("SPY: Reporting decision to HQ...");
        myBoss.reportBack(questionNumber, theirChoice);

        // Continue pretending I'm the player
        return theirChoice;
    }

    @Override
    public int getUsersRoll(int questionNumber)
            throws RemoteException {

        // Tell the player it's their turn but intercept their response
        int theirChoice = myTarget.getUsersRoll(questionNumber);

        // Report back to HQ
        System.out.println("SPY: Reporting decision to HQ...");
        myBoss.reportBack(questionNumber, theirChoice);

        // Continue pretending I'm the player
        return theirChoice;
    }

    @Override
    public CardinalDirection forkInTheRoad(int questionNumber,
            CardinalDirection[] availableDirections) throws RemoteException {

        // Tell the player it's their turn but intercept their response
        CardinalDirection theirChoice = myTarget.forkInTheRoad(questionNumber,
                availableDirections);

        // Report back to HQ
        System.out.println("SPY: Reporting decision to HQ...");
        myBoss.reportBack(questionNumber, theirChoice);

        // Continue pretending I'm the player
        return theirChoice;
    }

    @Override
    public boolean buyThisTile(int questionNumber, PropertyTile tileForPurchase)
            throws RemoteException {

        // Tell the player it's their turn but intercept their response
        boolean theirChoice = myTarget.buyThisTile(questionNumber, tileForPurchase);

        // Report back to HQ
        System.out.println("SPY: Reporting decision to HQ...");
        myBoss.reportBack(questionNumber, theirChoice);

        // Continue pretending I'm the player
        return theirChoice;
    }

    @Override
    public CardShape placeWhichCard(int questionNumber) throws RemoteException {

        // Tell the player it's their turn but intercept their response
        CardShape theirChoice = myTarget.placeWhichCard(questionNumber);

        // Report back to HQ
        System.out.println("SPY: Reporting decision to HQ...");
        myBoss.reportBack(questionNumber, theirChoice);

        // Continue pretending I'm the player
        return theirChoice;
    }

    @Override
    public CardShape swapCardOnThisTile(int questionNumber,
            PropertyTile tileForSwapping) throws RemoteException {

        // Tell the player it's their turn but intercept their response
        CardShape theirChoice = myTarget.swapCardOnThisTile(questionNumber,
                tileForSwapping);

        // Report back to HQ
        System.out.println("SPY: Reporting decision to HQ...");
        myBoss.reportBack(questionNumber, theirChoice);

        // Continue pretending I'm the player
        return theirChoice;
    }

    @Override
    public Tile swapCardOnWhichTile(int questionNumber) throws RemoteException {

        // Tell the player it's their turn but intercept their response
        Tile theirChoice = myTarget.swapCardOnWhichTile(questionNumber);

        // Report back to HQ
        System.out.println("SPY: Reporting decision to HQ...");
        myBoss.reportBack(questionNumber, theirChoice);

        // Continue pretending I'm the player
        return theirChoice;
    }

    @Override
    public Tile upgradeWhichTile(int questionNumber,
            PropertyTile[] upgradeableTiles) throws RemoteException {

        // Tell the player it's their turn but intercept their response
        Tile theirChoice = myTarget.upgradeWhichTile(questionNumber, upgradeableTiles);

        // Report back to HQ
        System.out.println("SPY: Reporting decision to HQ...");
        myBoss.reportBack(questionNumber, theirChoice);

        // Continue pretending I'm the player
        return theirChoice;
    }

    @Override
    public int upgradeToWhatLevel(int questionNumber, PropertyTile upgradingTile)
            throws RemoteException {

        // Tell the player it's their turn but intercept their response
        int theirChoice = myTarget.upgradeToWhatLevel(questionNumber, upgradingTile);

        // Report back to HQ
        System.out.println("SPY: Reporting decision to HQ...");
        myBoss.reportBack(questionNumber, theirChoice);

        // Continue pretending I'm the player
        return theirChoice;
    }

    @Override
    public PropertyTile sellWhichTile(int questionNumber, PlayerID sellingPlayer)
            throws RemoteException {

        // Tell the player it's their turn but intercept their response
        PropertyTile theirChoice = myTarget.sellWhichTile(questionNumber, sellingPlayer);

        // Report back to HQ
        System.out.println("SPY: Reporting decision to HQ...");
        myBoss.reportBack(questionNumber, theirChoice);

        // Continue pretending I'm the player
        return theirChoice;
    }

    @Override
    public PlayerID castOnPlayer(int questionNumber, SpellID spellCast)
            throws RemoteException {

        // Tell the player it's their turn but intercept their response
        PlayerID theirChoice = myTarget.castOnPlayer(questionNumber, spellCast);

        // Report back to HQ
        System.out.println("SPY: Reporting decision to HQ...");
        myBoss.reportBack(questionNumber, theirChoice);

        // Continue pretending I'm the player
        return theirChoice;
    }
}
