package f;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Game extends Remote {

    public int canIPlay() // Returns 0 if false, ID# otherwise
            throws RemoteException;

    public void imReady() // Once you're ready, call this to wait on other players
            throws RemoteException;

    public boolean isGoing() // Is the game over yet or not
            throws RemoteException;

    public void takeTurn(int myID) // This doesn't end until that player is dones
            throws RemoteException;
}
