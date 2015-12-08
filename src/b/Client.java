package b;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) {
        try {
            final int portNo;
            if (args.length < 1) {
                portNo = 65432;
            } else {
                portNo = Integer.parseInt(args[0]);
            }

            // Get the computer
            Registry registry = LocateRegistry.getRegistry(portNo);
            Compute comp = (Compute) registry.lookup("Compute");

            // Tell it to calc Pi
            Pi task = new Pi(5);
            BigDecimal pi = comp.executeTask(task);
            System.out.println(pi);

        } catch (Exception e) {
            System.err.println("Client exception:");
            e.printStackTrace();
        }
    }
}
