package c;

import java.rmi.RemoteException;

// Originally, there was no Thing interface, there was just a
// Thing class, and it implemented Remote and implemented doSomething.

// However, as it turns out this threw a Proxy exception; I think
// UnicastRemoteObject.exportObject() returns a Proxy object, and
// I guess Proxy objects don't like being cast to Objects, but
// they're cool with being cast to Interfaces.

public class Something implements Thing {

    @Override
    public String doSomething() throws RemoteException {
        System.out.println("I'm doing something!");
        return "Something";
    }
}
