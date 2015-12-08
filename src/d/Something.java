package d;

import java.rmi.RemoteException;

public class Something implements Thing {

    @Override
    public String doSomething() throws RemoteException {
        System.out.println("I'm doing something!");
        return "Something";
    }
}
