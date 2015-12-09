package d2;

import java.rmi.Remote;
import java.rmi.RemoteException;

// What?! I couldn't think of a good analogy!
public interface Example extends Remote {

    public void hereTakeThis(Example exampleObject)
            throws RemoteException;

    public void saySomething()
            throws RemoteException;

    public boolean doYourThing()
            throws RemoteException;
}
