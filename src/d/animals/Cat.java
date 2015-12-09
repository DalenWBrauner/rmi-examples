package d.animals;

import java.rmi.RemoteException;
import java.util.Random;

public class Cat implements Animal {
    private Random random = new Random();
    private String[] catNoises = {"Meow", "Mow", "...", "Prrrr", ".....", "HISSSSSS", "Nyan"};

    @Override
    public String speak() throws RemoteException {
        System.out.print("A cat speaks up. ");
        String noise = catNoise();
        System.out.println(noise+".");
        return noise;
    }

    @Override
    public <T> T fetch(T something) throws RemoteException {
        System.out.print("A cat ignores what is brought in front of it.");
        System.out.println(catNoise()+".");
        return null;
    }

    @Override
    public void pet() throws RemoteException {
        System.out.print("A cat tolerates physical affection. ");
        System.out.println(catNoise()+".");
    }

    // Returns a random cat noise.
    private String catNoise() {
        return catNoises[random.nextInt(catNoises.length)];
    }
}
