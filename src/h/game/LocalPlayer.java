package h.game;

import h.operations.AddAmount;
import h.operations.DivideAmount;
import h.operations.MultiplyAmount;
import h.operations.Operation;
import h.operations.SubtractAmount;

import java.rmi.RemoteException;
import java.util.Random;

public class LocalPlayer implements Player {
    private Operation myTurn;
    private Random strategy;
    private final int playstyle;

    public LocalPlayer() {
        strategy = new Random();
        playstyle = strategy.nextInt(4); // Decide your playstyle ahead of time
    }

    @Override
    public Operation yourTurn(int turnNo) throws RemoteException {
        System.out.println("Player: Okay, it's my turn...");
        try { makeDecision(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return myTurn;
    }

    private void makeDecision() throws InterruptedException {
        // Sleep some random period of time
        //synchronized (this) { TimeUnit.SECONDS.sleep(strategy.nextInt(3)); }

        // Then go
        int amount;
        // Each playstyle corresponds to a different Operation
        switch (playstyle) {
            case 0: // Addition
                amount = strategy.nextInt(10) + 1; //From 1 to 10
                myTurn = new AddAmount(amount);
                break;
            case 1: // Subtraction
                amount = strategy.nextInt(10) + 1; //From 1 to 10
                myTurn = new SubtractAmount(amount);
                break;
            case 2: // Multiplication
                amount = strategy.nextInt(4) + 2;  //From 2 to 5
                myTurn = new MultiplyAmount(amount);
                break;
            case 3: // Division
                amount = strategy.nextInt(5) + 1;  //From 1 to 5
                myTurn = new DivideAmount(amount);
                break;
        }
        System.out.println("Player: I've made my decision!");
    }
}
