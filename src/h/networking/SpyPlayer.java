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
        System.out.println("Heh, time to spy on the player..!");
        Operation theirChoice = myTarget.yourTurn(turnNo);
        System.out.println("Heh, I know what they chose!");

        // Report back to HQ
        System.out.println("Time to tell the boss...");
        myBoss.reportBack(turnNo,theirChoice);
        System.out.println("Heh, now the boss knows!");

        // Continue pretending I'm the player
        System.out.println("Time to go back into disguise...");
        return theirChoice;
    }

}
