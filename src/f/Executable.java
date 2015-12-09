package f;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Executable {
    public final static int portNo = 64246;

    // Server-only
    public static Registry serverRegistry;

    public static void main(String[] args) {
        try {
            if (args.length > 0)    executeServer();
            else                    executeClient();
        } catch (RemoteException | NotBoundException e) {
            if (args.length > 0) System.err.println("Server exception:");
            else                 System.err.println("Client exception:");
            e.printStackTrace();
        }
    }

    /* Server-only methods */

    private static void executeServer() throws RemoteException {
        establishServerRegistry();
        System.out.print("Server started!");

        register("thing", new RemoteServerThing());

    }

    /** Instantiate the server's registry */
    private static void establishServerRegistry() throws RemoteException {
        serverRegistry = LocateRegistry.createRegistry(portNo);
    }

    /** Create and register a stub for the remote object */
    private static void register(String name, Remote remoteObject) throws RemoteException {
        Remote stub = UnicastRemoteObject.exportObject(remoteObject,0);
        serverRegistry.rebind(name, stub);
    }

    /* Client-only methods */

    private static void executeClient() throws RemoteException, NotBoundException {
        // Connect to the registry
        Registry registry = LocateRegistry.getRegistry(portNo);

        // Grab the thing on the server
        Example serverThing = (Example) registry.lookup("thing");

        // Make sure it's working
        serverThing.saySomething();
        System.out.println("(I told the serverthing to say something.)");

        // Now make one on your end and send it over
        Example clientThing = new RemoteClientThing();
        Example clientThingStub = (Example) UnicastRemoteObject.exportObject(clientThing, 0);
        serverThing.hereTakeThis(clientThingStub);

        // Now tell the server thing to do its thing.
        serverThing.doYourThing();
        System.out.println("Client is done");
    }
}
