package a;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MyServer {

    public static void main(String args[]) {
        try {
            // Create a registry
            Registry registry = LocateRegistry.createRegistry(Port.NUMBER);

            // Create the adder and a stub for it
            Adder adder = new AdderRemote();
            Adder stub = (Adder) UnicastRemoteObject.exportObject(adder, 0);
            // We use 0 as an argument because we don't care what port the object is on
            // All we care about is what port the REGISTRY is on

            // Register that stub in the registry
            registry.rebind("adder", stub);

//            final int portNum = 5000;
//            Registry r = LocateRegistry.createRegistry(portNum);
//            stub = (Adder) UnicastRemoteObject.exportObject(stub, portNum);
//            r.bind("Adder",stub);


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
