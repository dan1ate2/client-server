package ThreadTest;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessThreadTest implements Runnable {
    // variables
    Socket sock;

    // constructor
    public ProcessThreadTest (Socket s) {
        this.sock = s;
    }
    
    @Override
    public void run() {
        ObjectOutputStream outStream = 
            null;
        try {
            // initiate streams
            outStream = new ObjectOutputStream(sock.getOutputStream());
            outStream.flush();
            BufferedReader inStream =
                    new BufferedReader(
                            new InputStreamReader(sock.getInputStream()));
            System.out.println("Initiated streams");
            
            // client interaction
            System.out.println(inStream.readLine()); // in
            outStream.writeObject("First reply"); // out
            System.out.println(inStream.readLine()); // in
            outStream.writeObject("Second reply"); // out
        } catch (IOException ex) {
            Logger.getLogger(ProcessThreadTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ProcessThreadTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
