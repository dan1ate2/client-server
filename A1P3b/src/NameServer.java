/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 3b
    Purpose: Server
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class NameServer {
    private static final int SERVER_PORT = 2015;
    private static ArrayList namesList = new ArrayList(); // LOOKUP CASE FORMAT FOR GLOBAL VARIABLE (IE CAMELCASE)
    
    public static void main(String[] args) 
            throws UnknownHostException, IOException {
        String inString;
        String outString;
        
        System.out.println("Server operating on "
            +InetAddress.getLocalHost().getHostAddress()
            +" on port "+SERVER_PORT);
        
        // set up data
        namesList.add("Dan");
        namesList.add("Jess");
        namesList.add("Bob Marley");
        
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
            inString = inStream.readLine(); // read first input from client
            while (!inString.equals("5")) { // loop until 5 (exit option) is sent
                int menuOption = Integer.parseInt(inString); // set option number
                
                outStream.println(promptClient(menuOption));
                inString = inStream.readLine(); // read input from client
                outStream.println(menuCommand(menuOption, inString)); // send request to client
//                System.out.println("Client said: "+inString);
//                outStream.println(inString);
//                outStream.flush();
//                inString = inStream.readLine(); // read input from client
            }
            
            System.out.println("Closed connection for "
                +sock.getInetAddress().getHostAddress());
            sock.close();
            } // end try socket
        } // end while
    } // end main method
    
        // initiates menu command request
        public static String menuCommand(int i, String cInput) {
            String output = "";
            
            switch (i) {
                case 1: // add a name
                    output = "[1]Server adds a name";
                    break;
                case 2: // remove a name
                    output = "[2]Server removes a name";
                    break;
                case 3: // list all names
                    output = "[3]Server lists all names";
                    break;
                case 4: // check if a name recorded
                    output = "[4]Server searches for name";
                    break;
                default:
                    System.out.println("Error finding correct menu option.");
                    break;
            }
            return output;
        } // end menuCommand
        
        // specific menu option prompt [1-4]
        public static String promptClient(int menuOpt){
            String prompt = "";
            
                switch (menuOpt) {
                case 1: // add a name
                    prompt = "Please provide the name to add and press enter.\n"
                        +"*Name can have spaces";
                    break;
                case 2: // remove a name
                    prompt = "Please provide the name to remove and press enter.\n"
                        +"*Name can have spaces";
                    break;
                case 3: // list all names
                    prompt = "Press enter to retrieve all names";
                    break;
                case 4: // check if a name recorded
                    prompt = "Please provide the name to search and press enter.\n"
                        +"*Name can have spaces";
                    break;
                default:
                    prompt = "Error finding correct menu option.";
                    break;
            }
            
            return prompt;
        }
    
} // end class NameServer
