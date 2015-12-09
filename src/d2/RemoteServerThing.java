package d2;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class RemoteServerThing implements Example {

    private ArrayList<Example> remoteThings = new ArrayList<>();

    @Override
    public void hereTakeThis(Example exampleObject) throws RemoteException {
        System.out.println("As a serverThing, I humbly except this exampleObject.");
        remoteThings.add(exampleObject);

    }

    @Override
    public void saySomething() throws RemoteException {
        System.out.println("Lalala I'm a RemoteServerThing");

    }

    @Override
    public boolean doYourThing() throws RemoteException {
        // You're the coordinator.
        System.out.println("I'm the serverThing about to do my thing.");
        // For all the remote things you have, tell them to do their thing.
        System.out.println("For all the remote things I have, I'm going to tell them to do THEIR thing.");
        // Okay yes but you now actually have to DO that
        for (Example thing : remoteThings) {
            System.out.println("\tI'm telling someone to do a thing.");
            thing.saySomething();
            boolean theyDidIt = thing.doYourThing();
            if (theyDidIt) System.out.println("\tTHEY DID THE THING!");
            else           System.out.println("\tTHEY CAME CLOSE TO DOING THE THING!");
        }
        // Okay good you did the thing
        System.out.println("As a serverThing, I do think I did my thing quite well!");
        return true;

    }

}
