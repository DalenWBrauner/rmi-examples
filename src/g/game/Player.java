package g.game;

import java.rmi.Remote;
import java.rmi.RemoteException;

import g.operations.Operation;

public interface Player extends Remote {
    public Operation yourTurn(int turnNo) throws RemoteException;

}
