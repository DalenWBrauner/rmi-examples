package d;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Thing extends Remote {
    String doSomething() throws RemoteException;
}
