package a;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class MyClient {
    static final Random r = new Random();

    public static void main(String args[]) {
        try {
             Registry registry = LocateRegistry.getRegistry(Port.NUMBER);
             Adder stub = (Adder) registry.lookup("adder");

            //test1(stub);

            //Adder stub = (Adder) Naming.lookup("rmi://localhost:5000/sonoo");
            int compare = 10;
            test2(stub, compare);
            test3(compare);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void test0() {
        int x = r.nextInt(99);
        int y = r.nextInt(99);
        //System.out.print(String.valueOf(x)+" plus "+String.valueOf(y)+" is ");
        int z = x + y;
        //System.out.println(z);
    }

    public static void test1(Adder stub) throws RemoteException {
        int x = r.nextInt(99);
        int y = r.nextInt(99);
//        System.out.print(String.valueOf(x)+" plus "+String.valueOf(y)+" is ");
        int z = stub.add(x,y);
//        System.out.println(z);
    }

    public static void test2(Adder stub, int problems) throws RemoteException {
        System.out.print("test2(): ");
        long t0 = System.nanoTime();
        int i = 0;
        while (i < problems) {
            test1(stub);
            i++;
        }
        long t1 = System.nanoTime();
        double secondsElapsed = (t1 - t0) / 1000000000.0;
        System.out.println(String.valueOf(i) + " problems in " + secondsElapsed + " seconds.");
    }

    public static void test3(int problems) throws RemoteException {
        System.out.print("test3(): ");
        long t0 = System.nanoTime();
        int i = 0;
        while (i < problems) {
            test0();
            i++;
        }
        long t1 = System.nanoTime();
        double secondsElapsed = (t1 - t0) / 1000000000.0;
        System.out.println(String.valueOf(i) + " problems in " + secondsElapsed + " seconds.");
    }
}
