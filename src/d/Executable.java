package d;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

import d.animals.Animal;
import d.animals.Cat;
import d.animals.Dog;
import d.food.Food;
import d.food.Garbage;
import d.food.Pizza;
import d.food.Raspberry;
import d.food.Sub;

public class Executable {
    public final static int portNo = 64246;
    public final static String thingName = "thing";

    // Server-only
    public static Registry serverRegistry;

    // Client-only
    public final static String[] menu = {"Raspberry", "Pizza", "Sub"};
    public static Random chef = new Random();


    public static void main(String[] args) {
        if (args.length > 0)    executeServer();
        else                    executeClient();
    }

    /* Server-only methods */

    private static void executeServer() {
        try {
            establishServerRegistry();

            // Create some number of random animals, register each of them.
            Random coin = new Random();
            final int numAnimals = coin.nextInt(12); // Coins can totally get you numbers between 1 and 12
            for (int i = 0; i < numAnimals; i++) {
                Animal housePet;

                // Randomly create either a cat or a dog
                if (coin.nextBoolean()) housePet = new Dog();
                else                    housePet = new Cat();

                // Register it as whatever animal
                register(String.valueOf(i), housePet);
            }

            System.out.print("Server started");
            System.out.println(" with "+String.valueOf(serverRegistry.list().length)+" registered animals.");

        } catch (Exception e) {
            System.err.println("Server exception:");
            e.printStackTrace();
        }
    }

    /** Instantiate the server's registry */
    private static void establishServerRegistry() throws RemoteException {
        serverRegistry = LocateRegistry.createRegistry(portNo);
    }

    /** Create and register a stub for the remote object */
    private static void register(String name, Remote remoteObject) throws RemoteException {
        Remote stub = UnicastRemoteObject.exportObject(remoteObject,0);
        serverRegistry.rebind(name, stub);
    }

    /* Client-only methods */

    private static void executeClient() {
        try {
            // Connect to the registry
            Registry registry = LocateRegistry.getRegistry(portNo);

            // Create a little house for our animals
            ArrayList<Animal> littleHouse = new ArrayList<>();

            // Give each animal in the registry a room in our house
            for (String entry : registry.list()) {
                littleHouse.add( (Animal) registry.lookup(entry));
            }

            // Pet each animal in our house
            for (Animal animal : littleHouse) {
                System.out.print("I don't know what animal this is, ");
                System.out.println("but let's pet it!");
                animal.pet();
            }

            // Feed each animal a random food item, and see what happens
            Random chef = new Random();
            for (Animal animal : littleHouse) {
                Food meal = randomMeal();
                System.out.println("Meal time! Come eat your "+meal.whatKind()+"!");
                animal.feed(meal);
                System.out.println("Alright, looks like there's "+meal.isLeft()+"% left.");
            }


        } catch (Exception e) {
            System.err.println("Client exception:");
            e.printStackTrace();
        }
    }

    private static Food randomMeal() {
        String selection = menu[chef.nextInt(menu.length)];

        if (selection == "Raspberry")   return new Raspberry();
        else if (selection == "Pizza")  return new Pizza();
        else if (selection == "Sub")    return new Sub();
        else                            return new Garbage();
    }

}
