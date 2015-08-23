/*
    Dan Hogan
    dhogan15
    22108261
    ISY00246
    Assignment 1
    Part 3c
    Purpose: Client app that allows connection to server app and read/modify
        list of names that are recorded in a file.
 */
package Part3c;

import java.io.*;
import java.net.*;

public class Client {
    private static final int SERVER_PORT = 2015;
    
    public static void main(String[] args) 
            throws UnknownHostException, IOException, ClassNotFoundException {
        String inConsoleString;
        
        InetAddress server = InetAddress.getByName(args[0]); // server address
        try (Socket sock = new Socket(server, SERVER_PORT)) { // create socket
            System.out.println("Connected to " + args[0]); // print connection
            
            // initiate streams
            PrintWriter outStream = 
                new PrintWriter(sock.getOutputStream()); // output to server
            ObjectInputStream inStream =
                new ObjectInputStream(sock.getInputStream()); // input from server
            BufferedReader inConsole =
                new BufferedReader(
                    new InputStreamReader(System.in)); // user console input
            System.out.println("Initiated streams");
            
            // SERVER COMMUNICATION
            do { // loop through until exit option
                do { // display menu, request option while option not valid
                    displayMenu();
                    inConsoleString = inConsole.readLine();
                } while (!validateMenuOption(inConsoleString));
                
                // send request to server
                outStream.println(inConsoleString);
                outStream.flush();
                
                if (!inConsoleString.equals("5")) { // if not exit option
                    // server response
                    System.out.println(inStream.readObject()); // prints server prompt

                    // send name information to server
                    inConsoleString = inConsole.readLine(); // get name
                    outStream.println(inConsoleString);
                    outStream.flush();

                    // server response
                    System.out.println(inStream.readObject()); // prints result
                }
            } while (!inConsoleString.equals("5")); // loop until exit option
        }
    }
    
    // display the main menu
    public static void displayMenu() {
        System.out.print("\nName Saver Server Menu\n\n"
                +"1. Add a name\n2. Remove a name\n3. List all names\n"
                +"4. Check if name recorded\n5. Exit\n\n"
                +"Enter selection [1-5]:");
    } // end displayMenu()
    
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
    } // end validateMenuOption()
    
}
