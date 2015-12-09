package f._subexamples.a;

import java.rmi.RemoteException;

public class ServerGame implements Game {

    private static final int playerSlots = 4;
    private static final int[] playerIDs = new int[playerSlots];

    private int emptyPlayerSlots;
    private int readyPlayers;

    public ServerGame() {
        emptyPlayerSlots = playerSlots;
        readyPlayers = 0;
    }

    @Override
    public int canIPlay() throws RemoteException {
        // You can if we have any empty slots
        int canYou = emptyPlayerSlots;

        // If you can,
        if (canYou != 0) {
            // assign them to a slot
            playerIDs[playerSlots - canYou] = canYou;
            emptyPlayerSlots--;
        }
        return canYou;
    }

    @Override
    public void imReady() throws RemoteException {
        // I don't want this getting weird
        synchronized (this) { readyPlayers++; }

        // Don't progress until all players are ready
        if (readyPlayers < playerSlots) System.out.println("Waiting for other players...");
        synchronized (this) {
            while (readyPlayers < playerSlots) {
                try { wait(); }
                catch (InterruptedException e) {}
            }
            notifyAll(); // Wow! This worked!
        }
        System.out.println("Players ready!");
    }
}
