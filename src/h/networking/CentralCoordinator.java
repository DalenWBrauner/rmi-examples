package h.networking;

import h.Executable;
import h.operations.Operation;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;

public class CentralCoordinator implements Coordinator {

    private static final int playerSlots = 4;
    private static final int[] playerIDs = new int[playerSlots];

    private int emptyPlayerSlots;
    private int readyPlayers;
    private int finishedPlayers;

    private HashMap<Integer,Operation> allTurns;

    public CentralCoordinator() {
        emptyPlayerSlots = playerSlots;
        readyPlayers = 0;
        finishedPlayers = 0;
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
        synchronized (this) { readyPlayers++; } // synchronized just to be safe

        // Don't progress until all players are ready
        if (readyPlayers < playerSlots) System.out.println("Waiting for other players...");
        synchronized (this) {
            // Wait for everyone else
            while (readyPlayers < playerSlots) {
                try { wait(); }
                catch (InterruptedException e) {}
            }

            // In case you're the only one who escaped the while loop,
            // you need to let everyone else know they can leave.
            notifyAll();
        }
        System.out.println("Players ready!");
    }

    @Override
    public Operation whatHappened(int turnNo) throws RemoteException {
        if (allTurns.containsKey(turnNo)) return allTurns.get(turnNo);

        // If we DON'T know the information...
        else {
            System.out.println("Waiting for info on turn "+String.valueOf(turnNo));

            // We need to wait for it.
            while (!allTurns.containsKey(turnNo)) {
                synchronized (this) {
                    try { wait(); }
                    catch (InterruptedException e) {}
                }
            }
            return whatHappened(turnNo);
        }
    }

    @Override
    public void reportBack(int turnNo, Operation theirChoice)
            throws RemoteException {
        // Input their decision
        System.out.println("Learning about turn "+String.valueOf(turnNo)+"!");
        allTurns.put(turnNo, theirChoice);

        // Tell everyone waiting on the server that it's updated
        synchronized(this) { notifyAll(); }
    }


    @Override
    public synchronized void goodGame(int ID) throws RemoteException, NotBoundException {
        // For now, we're going to assume this method only
        // gets called when the game is over, not if someone
        // decides to duck out early.
        finishedPlayers++;

        // If there aren't anymore players
        if (finishedPlayers == playerSlots) {
            // Tell the server to deregister you
            Executable.endServer();
        }
    }
}
