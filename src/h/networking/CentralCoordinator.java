package h.networking;

import h.operations.Operation;

import java.rmi.RemoteException;
import java.util.HashMap;

public class CentralCoordinator implements Coordinator {

    private static final int playerSlots = 4;
    private static final int[] playerIDs = new int[playerSlots];

    private int emptyPlayerSlots;
    private int readyPlayers;

    private HashMap<Integer,Operation> allTurns;

    public CentralCoordinator() {
        emptyPlayerSlots = playerSlots;
        readyPlayers = 0;
        allTurns = new HashMap<>();
    }

    @Override
    public int maxPlayers() throws RemoteException {
        return playerSlots;
    }

    @Override
    public synchronized int canIPlay() throws RemoteException {
        // You can if we have any empty slots
        if (emptyPlayerSlots == 0) return -1;

        // Generate their ID
        int ID = playerSlots - emptyPlayerSlots;

        // Assign them to that slot
        playerIDs[ID] = ID;
        emptyPlayerSlots--;

        // Hand that back to them
        return ID;
    }

    @Override
    public void imReady() throws RemoteException {
        // I don't want this getting weird
        synchronized (this) { readyPlayers++; }

        // I just realized the following synchronized blocks don't make sense
        // (at least not to me) but I'll leave them there if they work >~<

        // Don't progress until all players are ready
        if (readyPlayers < playerSlots) System.out.println("Waiting for other players...");
        synchronized (this) {
            while (readyPlayers < playerSlots) {
                try { wait(); }
                catch (InterruptedException e) {}
            }
            notifyAll(); // But will this work again?
        }
        System.out.println("Players ready!");
    }

    @Override
    public Operation whatHappened(int turnNo) throws RemoteException {
        // If we have it, get it
        if (allTurns.containsKey(turnNo)) return allTurns.get(turnNo);

        // Otherwise, don't return until we learn this information
        // I got a bad feeling about this code
        else {
            System.out.println("Waiting for info on turn "+String.valueOf(turnNo));
            synchronized (this) {
                while (!allTurns.containsKey(turnNo)) {
                    try { wait(); }
                    catch (InterruptedException e) {}
                }
                notifyAll(); // But will this work again?
            }
            return whatHappened(turnNo);
        }
    }

    @Override
    public void reportBack(int turnNo, Operation theirChoice)
            throws RemoteException {
        System.out.println("Someone's telling me they made a decision!");

        allTurns.put(turnNo, theirChoice);
        System.out.println("It's in, time to let everyone know...");

        synchronized(this) {
            notifyAll(); // But will this work again?
        }
        System.out.println("There, done.");
    }
}
