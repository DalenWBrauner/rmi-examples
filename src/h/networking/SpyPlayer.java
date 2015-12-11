package h.networking;

import h.game.Player;
import h.operations.Operation;

import java.rmi.RemoteException;

public class SpyPlayer implements Player {
    private Coordinator myBoss;
    private Player myTarget;

    /** Creates a Spy that reports all of the player's actions back
     * to the coordinator.
     *
     * @param boss The Coordinator receiving the player's actions.
     * @param target The Player being spied on.
     */
    public SpyPlayer(Coordinator boss, Player target) {
        myBoss = boss;
        myTarget = target;
    }

    @Override
    public Operation yourTurn(int turnNo) throws RemoteException {
        // Tell the player it's their turn but intercept their response
        Operation theirChoice = myTarget.yourTurn(turnNo);

        // Report back to HQ
        System.out.println("SPY: Reporting decision to HQ...");
        myBoss.reportBack(turnNo,theirChoice);

        // Continue pretending I'm the player
        return theirChoice;
    }

}
