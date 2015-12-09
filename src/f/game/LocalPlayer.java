package f.game;

import java.rmi.RemoteException;

import f.operations.MultiplyAmount;
import f.operations.Operation;

public class LocalPlayer implements Player {
    private final static Operation myTurn = new MultiplyAmount(2);

    @Override
    public Operation yourTurn(int turnNo) throws RemoteException {
        return myTurn;
    }
}
