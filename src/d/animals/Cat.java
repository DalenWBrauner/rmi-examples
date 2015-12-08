package d.animals;

import java.rmi.RemoteException;
import java.util.Random;

import d.food.Food;

public class Cat implements Animal {
    private Random random = new Random();
    private String[] catNoises = {"Meow", "Mow", "...", "Prrrr", ".....", "HISSSSSS", "Nyan"};
    private int stomach = 0;

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

    @Override
    public void eat(Food food) throws RemoteException {
        System.out.print("A cat eats some small amount of the "+food.whatKind()+" in front of it.");
        intoTheStomach( food.consumeSome(random.nextInt(20)) );
        System.out.println(catNoise()+".");
    }

    // Returns a random cat noise.
    private String catNoise() {
        return catNoises[random.nextInt(catNoises.length)];
    }

    private void intoTheStomach(int nutrients) {
        stomach += nutrients;
        if (stomach >= 100) {
            System.out.print("A cat has overeaten, and procedes to vomit everywhere. ");
            stomach = 0;
            System.out.println(catNoise()+".");
        }
    }
}
