package f._subexamples.a;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Executable {
    public final static String gameName = "Game";
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

        Game theGame = new ServerGame();
        register(gameName,theGame);
        System.out.println("Server ready!");
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
        System.out.println("Client started!");

        // Get the game
        Game theGame = (Game) registry.lookup(gameName);

        // Create a player and tell them to play
        Player jerry = new Player(theGame);
        System.out.println("Jerry is ready to play...");
        jerry.startPlaying();
        System.out.println("Jerry's done playing now.");

    }

}
