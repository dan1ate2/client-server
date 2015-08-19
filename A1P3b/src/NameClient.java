// THIS WILL BE BRANCH??
/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 3b
    Purpose: Client
 */
import java.io.*;
import java.net.*;

public class NameClient {
    private static final int CLIENT_PORT = 2014;
    private static final int SERVER_PORT = 2015;
    
    public static void main(String[] args) 
            throws UnknownHostException, IOException {
        
        String inConsoleString;
        String inServerString;
        
        InetAddress server = InetAddress.getByName(args[0]); // server address
        try (Socket sock = new Socket(server, SERVER_PORT)) { // create socket
        
            System.out.println("Connected to " + args[0]); // print connection
            
            // setup stream output to server
            PrintStream outStream = new PrintStream(sock.getOutputStream());
            
            // setup stream input from server
            BufferedReader inStream =
                    new BufferedReader(
                            new InputStreamReader(sock.getInputStream()));
            
            // setup user console input
            BufferedReader inConsole =
                    new BufferedReader(
                            new InputStreamReader(System.in));
            
                // TEST GET INPUT
                System.out.print("Enter something: ");
                inConsoleString = inConsole.readLine();

                // TEST SEND TO SERVER
                outStream.println(inConsoleString);
                outStream.flush();

                // TEST RECEIVE FROM SERVER
                inServerString = inStream.readLine();
                System.out.println("Server said: "+inServerString);
            
            sock.close(); // close socket
        } // end try socket
    } // end main method
    
} // end class
