package b;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Compute {

    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        return t.execute();
    }

    public static void main(String[] args) {
        try {
            final int portNo;
            if (args.length < 1) {
                portNo = 65432;
            } else {
                portNo = Integer.parseInt(args[0]);
            }

            // Create a registry
            Registry registry = LocateRegistry.createRegistry(portNo);

            // Create an engine and a stub for it
            Compute engine = new Server();
            Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);//, portNo+1);

            // Register that stub in the registry
            registry.rebind("Compute", stub);

            // Hooray!
            System.out.println("Server Registered!");

        } catch (Exception e) {
            System.err.println("Server exception:");
            e.printStackTrace();
        }
    }
}
