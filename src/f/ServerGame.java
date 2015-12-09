package f;

import java.rmi.RemoteException;

public class ServerGame implements Game {

    private static final int playerSlots = 4;
    private static final int[] playerIDs = new int[playerSlots];

    private int emptyPlayerSlots;
    private int readyPlayers;

    private int turnNumber;
    private boolean going;

    public ServerGame() {
        emptyPlayerSlots = playerSlots;
        readyPlayers = 0;
        going = false;
        turnNumber = 0;
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
            startGame();
            notifyAll(); // Wow! This worked!
        }
        going = true;
        System.out.println("Players ready!");
    }

    @Override
    public boolean isGoing() throws RemoteException { return going; }

    @Override
    public void takeTurn(int playerID) throws RemoteException {

        // Wait for your turn
        synchronized (this) {
            while (itsNotYourTurn(playerID)) {
                try { wait(); }
                catch (InterruptedException e) {}
            }
        }

        // If the game is still going (THIS CHECK REALLY SHOULDN'T BE HERE)
        // (THIS SHOULD BE PREVENTED IN THE FIRST PLACE)
        if (isGoing()) {

            // Take your turn
            System.out.println("Player "+String.valueOf(playerID)+" is taking their turn.");
            turnNumber++;
            System.out.println("It is now turn "+String.valueOf(turnNumber)+".");

            // End the game if neccessary
            if (turnNumber >= 25) endGame();
        }

        // Tell everyone your turn is over
        synchronized (this) { notifyAll(); }
    }

    private void waitYourTurn(int playerID) {

    }

    private boolean itsNotYourTurn(int playerID) {
        return (((turnNumber % playerSlots) + 1) == playerID);
    }

    private void startGame() { if (!going) going = true;  }
    private void endGame()   { if ( going) going = false; }

}
