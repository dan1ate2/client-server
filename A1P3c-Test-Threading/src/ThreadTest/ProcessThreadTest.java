package ThreadTest;

import static ThreadTest.ServerThreadTest.saveToFile;
import static ThreadTest.ServerThreadTest.namesList;
import java.io.*;
import java.net.*;

public class ProcessThreadTest implements Runnable {
    // variables
    final Socket sock;
    int clientNum;

    // constructor
    public ProcessThreadTest (Socket s) {
        this.sock = s;
    }
    
    @Override
    public void run() {
        String inString = "";
        
        try {
            // initiate streams
            ObjectOutputStream outStream = new ObjectOutputStream(sock.getOutputStream());
            outStream.flush();
            BufferedReader inStream =
                    new BufferedReader(
                            new InputStreamReader(sock.getInputStream()));
            
            // client interaction
            while (!inString.equals("5")) { // loop until exit option
                inString = inStream.readLine(); // read menu option from client
                int menuOption = Integer.parseInt(inString); // set menu option

                // if not exit option, process client request
                if (!inString.equals("5")) {
                    
                    synchronized(namesList) {
                        // prompt client for name
                        outStream.writeObject(promptClient(menuOption));
                        outStream.flush();

                        inString = inStream.readLine(); // read input from client

                        // send processed result to client
                        outStream.writeObject(menuCommand(menuOption, inString));
                        outStream.flush();
                        saveToFile();
                    }
                }
            }
            // close connection to client
            System.out.println("Closed connection for "
                +sock.getInetAddress().getHostAddress());
            sock.close(); // close socket
        } 
        catch (IOException ioe) {
            System.out.println("** Error handling client interaction.");
        }
        finally {
            try {
                saveToFile();
                System.out.println("Updated/saved names file.");
            } catch (IOException ex) {
                System.out.println("** Error saving names file.");
            }
        }
    } // end run

    // initiates menu command request
    public static String menuCommand(int i, String cInput) {
        String result;

        switch (i) {
            case 1: // add a name
                if (!cInput.trim().equals("")) { // if not empty string
                    namesList.add(cInput);
                    result = "Name \""+cInput+"\" added to list.";
                }
                else {
                    result = "** Error, no name entered.";
                }
                break;

            case 2: // remove a name
                if (namesList.removeIf(cInput::equals)) { // remove if match
                // :: = method reference - ClassName::MethodName (Java 8)
                   result = "\""+cInput+"\""+" removed from list."; 
                }
                else {
                    result = "** \""+cInput+"\""+" not found.";
                }
                break;

            case 3: // list all names
                // iterate through list, concatenate, new line
                result = "";
                for (String s : namesList) {
                        result += s + "\n";
                    }
                break;

            case 4: // check if a name recorded
                if (namesList.contains(cInput)) {
                    result = "\""+cInput+"\""+" is recorded in list.";
                }
                else {
                    result = "** \""+cInput+"\""+" not found in list.";
                } 
                break;

            default:
                result = "** Error finding correct menu option.";
                break;
        } // end switch
        return result;
    } // end menuCommand()

    // get prompt for user chosen menu option [1-4]
    public static String promptClient(int menuOpt){
        String prompt;

            switch (menuOpt) {
            case 1: // add a name
                prompt = "Please provide the name to ADD and press enter.\n"
                    +"* Name can have spaces";
                break;
            case 2: // remove a name
                prompt = "Please provide the name to REMOVE and press enter.\n"
                    +"* Name can have spaces";
                break;
            case 3: // list all names
                prompt = "Press enter to RETRIEVE all names";
                break;
            case 4: // check if a name recorded
                prompt = "Please provide the name to SEARCH and press enter.\n"
                    +"* Name can have spaces";
                break;
            default:
                prompt = "Error finding correct menu option.";
                break;
        } // end switch
        return prompt;
    } // end promptClient()
    
}
