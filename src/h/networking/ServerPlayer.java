package h.networking;

import h.game.Player;
import h.operations.Operation;

import java.rmi.RemoteException;

public class ServerPlayer implements Player {
    private Coordinator coordinator;

    public ServerPlayer(Coordinator c) {
        coordinator = c;
    }

    @Override
    public Operation yourTurn(int turnNo) throws RemoteException {
        System.out.println("Asking the server what the player chose...");
        return coordinator.whatHappened(turnNo);
    }
}
