package f;

import java.rmi.RemoteException;

public class RemoteClientThing implements Example {

    @Override
    public void hereTakeThis(Example exampleObject) throws RemoteException {
        // Uhh, what am I supposed to do with this?
        System.out.println("Uhh, what am I supposed to do with this?");
        System.out.println("I guess, just, uhh, I'll tell it to say something.");
        exampleObject.saySomething();
        System.out.println("...Okay it said something. I'm not keeping it.");
    }

    @Override
    public void saySomething() throws RemoteException {
        System.out.println("Lalala I'm a RemoteClientThing");

    }

    @Override
    public boolean doYourThing() throws RemoteException {
        System.out.println("IT IS MY TIME! THE TIME OF THE CLIENTTHING!");
        boolean doneTheThing = true;
        System.out.println("ALRIGHT YES GOOD I HAVE DONE THE THING");
        return doneTheThing;
    }

}
