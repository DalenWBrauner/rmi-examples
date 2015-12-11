package h;

import h.game.Game;
import h.game.LocalPlayer;
import h.game.Player;
import h.networking.Coordinator;
import h.networking.Server;
import h.networking.ServerPlayer;
import h.networking.SpyPlayer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Executable {
    public final static String coordinatorName = "Coordinator";
    public final static int portNo = 64246;
    public static Server server;

    public static void main(String[] args) {
        // If we have arguments, execute the server too
        if (args.length > 0) executeServer();

        // Launch the Client
        try {
            executeClient();
            //executeClient2Player();
        } catch (Exception e) {
            System.err.println("Client exception:");
            e.printStackTrace();
        }
    }

    private static void executeServer() {
        server = new Server();
        server.run();
    }

    public static void endServer() throws RemoteException, NotBoundException {
        server.endServer();
    }

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
        coordinator.imReady(ID);

        // Then START!
        System.out.println("PLAYING!");
        ourGame.start();

        // Once the game is over
        System.out.println("Good game, everyone!");
        coordinator.goodGame(ID);

    }

    private static void executeClient2Player() throws RemoteException, NotBoundException {
        // Connect* to the registry
        Registry registry = LocateRegistry.getRegistry(portNo);
        System.out.println("Client started!");

        // *we haven't actually connected, just created an object
        // that will point to registry; if it doesn't actually exist,
        // we'll find out the first time we try to USE it.

        // Get the coordinator
        Coordinator coordinator = (Coordinator) registry.lookup(coordinatorName);

        // Some prep
        int maxPlayers = coordinator.maxPlayers();
        int[] IDs = new int[2];

        // Startup 2 players
        for (int player = 0; player < 2; player++) {
            // Ask to play
            System.out.println("Can I play?");
            IDs[player] = coordinator.canIPlay();
            if (IDs[player] < 0) {
                System.out.println("Aww, the game was full...");
                return; // If I can't, leave
            }
            System.out.println("YES! I'm player "+String.valueOf(IDs[player])+"!");
        }

        // Create all of our players

        Player[] players = new Player[maxPlayers];
        for (int i = 0; i < maxPlayers; i++) {

            // If it's us, create a local wrapped in a spy
            if (i == IDs[0] || i == IDs[1]) {
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
        coordinator.imReady(IDs);

        // Then START!
        System.out.println("PLAYING!");
        ourGame.start();

        // Once the game is over
        System.out.println("Good game, everyone!");
        coordinator.goodGame(IDs);
    }
}
