package a;

import java.rmi.RemoteException;

public class AdderRemote implements Adder {//extends UnicastRemoteObject implements Adder {

    @SuppressWarnings("unused")
    private static final long serialVersionUID = -2446473799054017328L;

    AdderRemote() throws RemoteException {
        super();
    }

    public int add(int x, int y) {
        System.out.println(String.valueOf(x) + " + " + String.valueOf(y) + " = " +String.valueOf(x + y));
        return x+y;
    }
}
