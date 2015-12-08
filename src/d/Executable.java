package d;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import d.animals.Animal;
import d.animals.Cat;
import d.animals.Dog;

public class Executable {
    public final static int portNo = 64246;
    public final static String thingName = "thing";

    public static void main(String[] args) {
        if (args.length > 0)    executeServer();
        else                    executeClient();
    }

    private static void executeServer() {
        try {
            // Create the registry
            Registry registry = LocateRegistry.createRegistry(portNo);

            // Create a Dog, its stub, and register it.
            Animal spot = new Dog();
            Animal spotStub = (Animal) UnicastRemoteObject.exportObject(spot, 0);
            registry.rebind("Dog", spotStub);

            // Create a Cat, its stub, and register it.
            Animal garfield = new Cat();
            Animal garfieldStub = (Animal) UnicastRemoteObject.exportObject(garfield, 0);
            registry.rebind("Cat", garfieldStub);

        } catch (Exception e) {
            System.err.println("Server exception:");
            e.printStackTrace();
        }
    }

    private static void executeClient() {
        try {
            // Connect to the registry
            Registry registry = LocateRegistry.getRegistry(portNo);

            // Get a Dog, let's forget it's on a server somewhere
            Animal localDog = (Animal) registry.lookup("Dog");

            // Get a Cat, let's forget it's on a server somewhere
            Animal localCat = (Animal) registry.lookup("Cat");

            System.out.println("Petting the dog!");
            localDog.pet();
            System.out.println("Petting the cat!");
            localCat.pet();

        } catch (Exception e) {
            System.err.println("Client exception:");
            e.printStackTrace();
        }

    }

}
