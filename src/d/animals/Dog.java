package d.animals;

import java.rmi.RemoteException;

public class Dog implements Animal {
    private int happiness = 5;

    @Override
    public String speak() throws RemoteException {
        System.out.println("A dog barks.");
        return "Bark!";
    }

    @Override
    public <T> T fetch(T something) throws RemoteException {
        System.out.println("A dog plays fetch.");
        return something;
    }

    @Override
    public void pet() throws RemoteException {
        happiness *= 2;
        System.out.println("A dog was pet. Happiness: "+Integer.toString(happiness));
    }

}
