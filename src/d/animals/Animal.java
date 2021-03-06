package d.animals;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Animal extends Remote {

    // Says something
    public String speak()
            throws RemoteException;

    // Ideally, returns whatever you gave it
    public <T> T fetch(T something)
            throws RemoteException;

    // This function can do whatever it wants
    public void pet()
            throws RemoteException;

}
