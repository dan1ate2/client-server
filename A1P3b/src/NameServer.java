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
    private static final int CLIENT_PORT = 2014;
    private static final int SERVER_PORT = 2015;
    
    public static void main(String[] args) 
            throws UnknownHostException, IOException {
        String receiveData = "";
        String sendData;
        
        System.out.println("Server operating on "
            +InetAddress.getLocalHost().getHostAddress()
            +" on port "+SERVER_PORT);
        
        // set up server socket
        // ServerSocket server = new ServerSocket(SERVER_PORT);
        ServerSocket ssock = new ServerSocket(SERVER_PORT);
        
        // take input
        while (true) {
            try (Socket sock = ssock.accept()) {
                System.out.println(sock.getInetAddress().getHostAddress() + " is now connected");
                
                // create in and out streams
                PrintWriter outStream = new PrintWriter(sock.getOutputStream());
                BufferedReader inStream =
                        new BufferedReader(
                                new InputStreamReader(sock.getInputStream()));
                
                while (!"exit".equals(receiveData)) {
                    // TEST RECEIVING DATA
                    receiveData = inStream.readLine();
                    System.out.println("Received: "+receiveData);

                    // TEST FOR EXIT REQUEST
                    if ("exit".equals(receiveData)) {
                        sock.close(); // close socket
                    }
                    // TEST SENDING BACK TO CLIENT
                    else {
                        sendData = "What up??";
                        outStream.write(sendData);
                        outStream.flush();
                    }
                }
            }
        } // end while
    } // end main method
    
} // end class NameServer
