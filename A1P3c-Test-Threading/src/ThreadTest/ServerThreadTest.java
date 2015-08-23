package ThreadTest;

import java.io.*;
import java.net.*;

public class ServerThreadTest {
    private static final int SERVER_PORT = 2015;
    
    public static void main(String[] args) throws IOException {
        System.out.println("Server operating on "
            +InetAddress.getLocalHost().getHostAddress()
            +" on port "+SERVER_PORT+"\n");
        
        ServerSocket ssock = new ServerSocket(SERVER_PORT); // set server socket
        Socket sock = ssock.accept();
        
        System.out.println(sock.getInetAddress().getHostAddress() 
            + " is now connected");
        
        // new thread and run
        Thread clientThread = new Thread(new ProcessThreadTest(sock));
        clientThread.start();
    }
}
