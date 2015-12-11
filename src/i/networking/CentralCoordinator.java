package i.networking;

import i.Executable;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;

public class CentralCoordinator implements Coordinator {

    private static final int playerSlots = 4;
    private static final int[] playerIDs = new int[playerSlots];

    private int readyPlayers;
    private int finishedPlayers;

    private HashMap<Object,Object> allTurns;

    public CentralCoordinator() {
        readyPlayers = 0;
        finishedPlayers = 0;
        allTurns = new HashMap<>();

        // -1 means "empty slot"
        for (int i = 0; i < playerIDs.length; i++) {
            playerIDs[i] = -1;
        }
    }

    @SuppressWarnings("unused")
    private int emptySlots() {
        int emptySlots = 0;
        for (int i = 0; i < playerIDs.length; i++) {
            if (playerIDs[i] == -1) emptySlots++;
        }
        return emptySlots;
    }

    @Override
    public int maxPlayers() throws RemoteException {
        return playerSlots;
    }

    @Override
    public synchronized int canIPlay() throws RemoteException {

        // Look for an empty slot
        for (int i = 0; i < playerIDs.length; i++) {
            if (playerIDs[i] == -1) {

                // Assign them to that slot
                int ID = i;
                playerIDs[i] = ID;

                // Quit early
                return ID;
            }
        }
        // If there weren't any slots...
        return -1;
    }

    @Override
    public synchronized int[] canIPlay(int numPlayers) throws RemoteException {
        int[] foundIDs = new int[numPlayers];
        int IDsNeeded = numPlayers;

        // Let's hunt down some empty slots!
        int i = 0;
        while (i < playerIDs.length && IDsNeeded > 0) {

            // Take note of any open IDs we find
            if (playerIDs[i] == -1) {
                foundIDs[(numPlayers - IDsNeeded)] = i;
                IDsNeeded--;
            }

            // (We're looping through playerIDs, by the way)
            i++;
        }

        // If we found enough IDs,
        if (IDsNeeded == 0) {
            // Reserve them
            for (int ID : foundIDs) playerIDs[ID] = ID;
            return foundIDs;

        // If not, you can't play
        } else {
            int[] negatory = {-1};
            return negatory;
        }
    }

    @Override
    public void imReady(int ID) throws RemoteException {
        int[] IDs = {ID};
        imReady(IDs);
    }

    @Override
    public void imReady(int[] IDs) throws RemoteException {
        synchronized (this) { readyPlayers += IDs.length; } // synchronized just to be safe

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
    public Object whatHappened(int turnNo) throws RemoteException {
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
    public void reportBack(int turnNo, Object theirChoice)
            throws RemoteException {
        // Input their decision
        System.out.println("Learning about turn "+String.valueOf(turnNo)+"!");
        allTurns.put(turnNo, theirChoice);

        // Tell everyone waiting on the server that it's updated
        synchronized(this) { notifyAll(); }
    }

    @Override
    public synchronized void goodGame(int[] IDs) throws RemoteException, NotBoundException {
        for (int ID : IDs) {
            goodGame(ID);
        }
    }

    @Override
    public void goodGame(int ID) throws RemoteException, NotBoundException {
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
