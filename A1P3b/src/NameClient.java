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
    private static final int SERVER_PORT = 2015;
    
    public static void main(String[] args) 
            throws UnknownHostException, IOException, ClassNotFoundException {
        String inConsoleString;
        String inServerString;

        InetAddress server = InetAddress.getByName(args[0]); // server address
        try (Socket sock = new Socket(server, SERVER_PORT)) { // create socket
            System.out.println("Connected to " + args[0]); // print connection
            
            // output to server
            PrintWriter outStream = 
                    new PrintWriter(sock.getOutputStream()); 
            
            // input from server
            ObjectInputStream inStream =
                    new ObjectInputStream(sock.getInputStream());
            
            // user console input
            BufferedReader inConsole =
                    new BufferedReader(
                            new InputStreamReader(System.in));
            
            // SERVER COMMUNICATION
            do {
                do {
                    displayMenu(); // display menu
                    inConsoleString = inConsole.readLine(); // get user option/input
                } while (!validateMenuOption(inConsoleString)); // check valid input
                // send request to server
                outStream.println(inConsoleString); // set console input for send
                outStream.flush(); // send chosen option to server
                
                // server response
                inServerString = inStream.readObject().toString(); // object to string
                System.out.println(inServerString); // prints server option prompt
                
                // send name information to server
                inConsoleString = inConsole.readLine(); // get user request (name)
                outStream.println(inConsoleString); // set console input for send
                outStream.flush(); // send name to server
                
                // server response
                inServerString = inStream.readObject().toString(); // object to string
                System.out.println(inServerString); // prints server option answer
            } while (!inConsoleString.equals("5")); // loop until exit option
            
            sock.close(); // close socket
        } // end try socket
    } // end main method
    
    // display the main menu
    public static void displayMenu() {
        System.out.print("\nName Saver Server Menu\n\n"
                +"1. Add a name\n2. Remove a name\n3. List all names\n"
                +"4. Check if name recorded\n5. Exit\n\n"
                +"Enter selection [1-5]:");
    }
    
    // validate menu choice input from user/console
    public static boolean validateMenuOption(String input) {
        boolean valid = false;
        try {
            if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 5){
                System.out.println("**Error: Menu option not found.\n"
                        +"  Please type a single digit [1-5] for menu option.");
            } 
            else {
                valid = true;
            }
        }
        catch (NumberFormatException nfe) {
            System.out.println("**Error: Only a single number can be input.\n"
            +"  Please type a single digit [1-5] for menu option.");
        }
        return valid;
    }
    
} // end class
