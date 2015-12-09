package f;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import f.game.Game;

public class Executable {
    public final static int portNo = 64246;

    // Server-only
    public static Registry serverRegistry;

    public static void main(String[] args) {
        // If we have arguments, we're a server
        if (args.length > 0) {
            try {
                executeServer();

            } catch (Exception e) {
                System.err.println("Server exception:");
                e.printStackTrace();
            }
        }

        // Otherwise, we're a client
        else {
            try {
                executeClient();

            } catch (Exception e) {
                System.err.println("Client exception:");
                e.printStackTrace();
            }
        }
    }

    /* Server-only methods */

    private static void executeServer() throws RemoteException {
        establishServerRegistry();
        System.out.println("Server started!");

        // Just run a local game for now
        Game game = new Game();
        game.start();
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

    private static void executeClient() throws RemoteException {
        // Connect to the registry
        Registry registry = LocateRegistry.getRegistry(portNo);

        //Cast thing = (cast) registry.lookup(name);
        System.out.println("Client started!");
    }

}
