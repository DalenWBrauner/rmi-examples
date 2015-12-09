package f;

import java.rmi.RemoteException;

public class Player {

    private Game theGame;
    private int myID;

    public Player(Game playThis) {
        theGame = playThis;
        myID = 0;
    }

    public void startPlaying() {
        // See if I can play
        try { myID = theGame.canIPlay(); }
        catch (RemoteException e) { e.printStackTrace(); }

        // If not
        if (myID == 0) {
            System.out.println("I wasn't allowed to play...");
            return;
        }

        // Otherwise, well, I'm ready!
        System.out.println("Sweet, my ID# is "+String.valueOf(myID)+" and I'm ready to play!");

        // Tell the game I'm ready
        try { theGame.imReady(); }
        catch (RemoteException e) { e.printStackTrace(); }

        System.out.println("Everyone's ready?! I'm excited!");

        // Lol there's no game
    }
}
