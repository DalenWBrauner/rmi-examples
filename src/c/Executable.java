package c;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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

            // Create the RemoteThing and a stub for it
            Thing thing = new Something();
            Thing stub = (Thing) UnicastRemoteObject.exportObject(thing, 0);

            // Bind that Thing in the registry
            registry.rebind(thingName, stub);

        } catch (Exception e) {
            System.err.println("Server exception:");
            e.printStackTrace();
        }
    }

    private static void executeClient() {
        try {
            // Connect to the registry
            Registry registry = LocateRegistry.getRegistry(portNo);

            // Get a remote thing
            Thing rThing = (Thing) registry.lookup(thingName);

            // Create a local thing
            Thing lThing = new Something();

            // Call .doSomething();
            System.out.println("Calling rThing.doSomething();");
            System.out.println(
                    rThing.doSomething());
            System.out.println("Calling lThing.doSomething();");
            System.out.println(
                    lThing.doSomething());

        } catch (Exception e) {
            System.err.println("Client exception:");
            e.printStackTrace();
        }

    }

}
