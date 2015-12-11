package h;

import h.game.Game;
import h.game.LocalPlayer;
import h.game.Player;
import h.networking.CentralCoordinator;
import h.networking.Coordinator;
import h.networking.ServerPlayer;
import h.networking.SpyPlayer;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Executable {
    public final static String coordinatorName = "Coordinator";
    public final static int portNo = 64246;
    private static Registry serverRegistry;
    private static Coordinator theCoordinator;
    private static ArrayList<Remote> stubs = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // If we have arguments, we're a server, otherwise a client
            if (args.length > 0) executeServer();
            else                 executeClient();

        } catch (Exception e) {
            if (args.length > 0) System.err.println("Server exception:");
            else                 System.err.println("Client exception:");
            e.printStackTrace();
        }
    }

    /* Server-only methods */

    private static void executeServer() throws RemoteException {
        // Start the server
        establishServerRegistry();
        System.out.println("Server started!");

        // Add the coordinator
        //Coordinator theCoordinator = new CentralCoordinator();
        theCoordinator = new CentralCoordinator();
        register(coordinatorName, theCoordinator);
    }

    /** Instantiate the server's registry */
    private static void establishServerRegistry() throws RemoteException {
        serverRegistry = LocateRegistry.createRegistry(portNo);
    }

    /** Create and register a stub for the remote object */
    private static void register(String name, Remote remoteObject) throws RemoteException {
        Remote stub = UnicastRemoteObject.exportObject(remoteObject,0);
        serverRegistry.rebind(name, stub);
        stubs.add(stub); // We'll need this to unexport it later
    }

    /* Client-only methods */

    private static void executeClient() throws RemoteException, NotBoundException {
        // Connect* to the registry
        Registry registry = LocateRegistry.getRegistry(portNo);
        System.out.println("Client started!");

        // *we haven't actually connected, just created an object
        // that will point to registry; if it doesn't actually exist,
        // we'll find out the first time we try to USE it.

        // Get the coordinator
        Coordinator coordinator = (Coordinator) registry.lookup(coordinatorName);

        // Ask to play
        System.out.println("Can I play?");
        int ID = coordinator.canIPlay();
        if (ID < 0) {
            System.out.println("Aww, the game was full...");
            return; // If I can't, leave
        }
        System.out.println("YES! I'm player "+String.valueOf(ID)+"!");

        // Create all of our players
        int maxPlayers = coordinator.maxPlayers();
        Player[] players = new Player[maxPlayers];
        for (int i = 0; i < maxPlayers; i++) {

            // If it's us, create a local wrapped in a spy
            if (i == ID) {
                players[i] = new SpyPlayer(coordinator,
                        new LocalPlayer());

            } else {
                // Create server players
                players[i] = new ServerPlayer(coordinator);
            }
        }

        // Now create our game!
        Game ourGame = new Game(players);

        // Make sure everyone's ready...
        System.out.println("Ready...");
        coordinator.imReady();

        // Then START!
        System.out.println("PLAYING!");
        ourGame.start();

        // Once the game is over
        System.out.println("Good game, everyone!");
        coordinator.goodGame(ID);

    }

    public static void endServer() throws RemoteException, NotBoundException {
        System.out.println("Server exiting...");

//        // Clean up our stubs
//        System.out.println("Cleaning stubs...");
//        for (Remote stub : stubs) {
//            UnicastRemoteObject.unexportObject(stub, false);
//        }

        // Remove our coordinator from the registry
        System.out.println("Unbinding coordinator...");
        serverRegistry.unbind(coordinatorName);

        // Unexport the coordinator
        System.out.println("Unexporting coordinator...");
        UnicastRemoteObject.unexportObject(theCoordinator, true);

        // Unexport the registry itself
        System.out.println("Unbinding registry...");
        UnicastRemoteObject.unexportObject(serverRegistry, true);
    }
}
