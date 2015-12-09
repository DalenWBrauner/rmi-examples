package d.animals;

import java.rmi.RemoteException;

import d.food.Food;

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

    @Override
    public void feed(Food food) throws RemoteException {
        System.out.print("A dog ate a "+ food.whatKind());
        food.consume();
        System.out.println(", and there was " + String.valueOf(food.isLeft()) + "% left.");
    }
}
