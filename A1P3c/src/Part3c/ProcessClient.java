//System.out.println("");
package Part3c;

import static Part3c.ThreadServer.namesList;
import java.io.*;
import java.net.*;

/**
 *
 * @author Ðan
 */
public class ProcessClient implements Runnable{
    // variables
    String inString = "";
    Socket sock;
    
    // constructor
    public ProcessClient (Socket s) {
        this.sock = s;
    }
    
    @Override
    public void run() {
        // create in and out streams
        try {
            ObjectOutputStream outStream = null;
            outStream = 
                new ObjectOutputStream(sock.getOutputStream());
            outStream.flush();
            BufferedReader inStream =
                    new BufferedReader(
                            new InputStreamReader(sock.getInputStream()));
            System.out.println("Created streams");

            System.out.println("Entering 'while !inString equals 5' loop");
            while (!inString.equals("5")) { // loop until exit option
                inString = inStream.readLine(); // read menu option from client
                int menuOption = Integer.parseInt(inString); // set menu option

                System.out.println("Entering 'if inString = 5'");
                if (!inString.equals("5")) {
                    // prompt client for name
                    outStream.writeObject(promptClient(menuOption));
                    outStream.flush();

                    inString = inStream.readLine(); // read input from client

                    // send result to client
                    outStream.writeObject(menuCommand(menuOption, inString));
                    outStream.flush();
                }
            }

                            // close connection to client
            System.out.println("Closed connection for "
                +sock.getInetAddress().getHostAddress());
            inString = ""; // reset for next connection
            sock.close(); // close socket

            // update names file
            saveToFile();
            System.out.println("Updated/saved names file.");
        } // end try
        catch (IOException ioe) {
            System.out.println("Error handling client interaction.");
            ioe.printStackTrace();
        }
    }
        
    // saves the list to a file (overwrites previous)
    public static void saveToFile() throws IOException {
        File file = new File("src/names.txt");
        try (FileWriter fw = new FileWriter(file, false)) {
            for (String s : namesList) {
                if (!s.equals("")) {
                    fw.write(s+"\n");
                }
            } // end for
        } // end try
    } // end save to file()

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
    
} // end class
