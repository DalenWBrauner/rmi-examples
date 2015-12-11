package h.networking;

import h.Executable;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements Runnable {

    private Registry serverRegistry;
    private ArrayList<Remote> registeredObjects = new ArrayList<>();

    @Override
    public void run() {
        try {
            executeServer();
        } catch (RemoteException e) {
            System.out.println("Server exception:");
            e.printStackTrace();
        }
    }

    private void executeServer() throws RemoteException {
        // Start the server
        establishServerRegistry();
        System.out.println("Server started!");

        // Add the coordinator
        Coordinator theCoordinator = new CentralCoordinator();
        register(Executable.coordinatorName, theCoordinator);
    }

    /** Instantiate the server's registry */
    private void establishServerRegistry() throws RemoteException {
        serverRegistry = LocateRegistry.createRegistry(Executable.portNo);
    }

    /** Create and register a stub for the remote object */
    private void register(String name, Remote remoteObject) throws RemoteException {
        Remote stub = UnicastRemoteObject.exportObject(remoteObject,0);
        serverRegistry.rebind(name, stub);
        registeredObjects.add(remoteObject); // We'll need this to unexport it later
    }

    public void endServer() throws RemoteException, NotBoundException {
        System.out.println("Server exiting...");

        // Clean out our registry
        for (Remote object : registeredObjects) {
            UnicastRemoteObject.unexportObject(object, true);
        }

        // Unexport the registry itself
        UnicastRemoteObject.unexportObject(serverRegistry, true);
    }

}
