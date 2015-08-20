/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 3b
    Purpose: Server
 */
import java.io.*;
import java.net.*;

public class NameServer {
    private static final int SERVER_PORT = 2015;
    
    public static void main(String[] args) 
            throws UnknownHostException, IOException {
        String inString;
        String outString;
        
        System.out.println("Server operating on "
            +InetAddress.getLocalHost().getHostAddress()
            +" on port "+SERVER_PORT);
        
        // set up server socket
        ServerSocket ssock = new ServerSocket(SERVER_PORT);
        
        // take input
        while (true) {
            try (Socket sock = ssock.accept()) {
                System.out.println(sock.getInetAddress().getHostAddress() 
                    + " is now connected");
                
                // create in and out streams
                PrintWriter outStream = new PrintWriter(sock.getOutputStream());
                BufferedReader inStream =
                        new BufferedReader(
                                new InputStreamReader(sock.getInputStream()));
                
            // CLIENT COMMUNICATION
            inString = inStream.readLine();
            while (!inString.equals("5")) { // loop until 5 (exit option) is sent
                System.out.println("Client said: "+inString);
                outStream.println(inString);
                outStream.flush();
                inString = inStream.readLine();
            }
            
            System.out.println("Closed connection for "
                +sock.getInetAddress().getHostAddress());
            sock.close();
            } // end try socket
        } // end while
    } // end main method
    
        // initiates client menu command request
        public static void menuCommand(int i) {
            switch (i) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    // 
                    break;
            }
        }
    
} // end class NameServer
