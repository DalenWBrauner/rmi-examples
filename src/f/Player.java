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
        System.out.println("'Sure I'll play, just gimmie a sec to get ready.'");
        // Gotta make sure you're ready first, brah
        getReady();
        System.out.println("'LET'S PLAY!'");

        // Alright time to play the game brah
        try {
            while(theGame.isGoing()) {
                System.out.println("'I'll just... take my turn, here...'");
                theGame.takeTurn(myID);
            }
        } catch (RemoteException e) { e.printStackTrace(); }

        System.out.println("'Alright, fun game. I'm done.'");
        System.out.println("'gg wp'");
    }

    private void getReady() {
        // See if I can play
        try { myID = theGame.canIPlay(); }
        catch (RemoteException e) { e.printStackTrace(); }

        // If not
        if (myID == 0) {
            System.out.println("'I wasn't allowed to play...'");
            return;
        }

        // Otherwise, well, I'm ready!
        System.out.println("'Sweet, my ID# is "+String.valueOf(myID)+" and I'm ready to play!'");

        // Tell the game I'm ready
        try { theGame.imReady(); }
        catch (RemoteException e) { e.printStackTrace(); }

        System.out.println("'Everyone's ready?! I'm excited!'");
    }
}
