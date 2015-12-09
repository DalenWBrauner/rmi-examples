package f.game;

import java.rmi.Remote;
import java.rmi.RemoteException;

import f.operations.Operation;

public interface Player extends Remote {
    public Operation yourTurn(int turnNo) throws RemoteException;

}
