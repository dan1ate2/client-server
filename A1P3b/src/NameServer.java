/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 3b
    Purpose: Server app that reads/writes list of names to/from file.
        Allows client to connect and modify.
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class NameServer {
    private static final int SERVER_PORT = 2015;
    private static List<String> namesList = new ArrayList(); // LOOKUP CASE FORMAT FOR GLOBAL VARIABLE (IE CAMELCASE)
    
    public static void main(String[] args) 
            throws UnknownHostException, IOException {
        String inString = "";
        
        System.out.println("Server operating on "
            +InetAddress.getLocalHost().getHostAddress()
            +" on port "+SERVER_PORT);
        
        // set up server socket
        ServerSocket ssock = new ServerSocket(SERVER_PORT);
        
        getFileData(); // load file into array
        
        while (true) {
            // wait for client input
            try (Socket sock = ssock.accept()) {
                System.out.println(sock.getInetAddress().getHostAddress() 
                    + " is now connected");
                
                // create in and out streams
                ObjectOutputStream outStream = 
                        new ObjectOutputStream(sock.getOutputStream());
                BufferedReader inStream =
                        new BufferedReader(
                                new InputStreamReader(sock.getInputStream()));
                
            // CLIENT COMMUNICATION
            while (!inString.equals("5")) { // loop until exit option
                inString = inStream.readLine(); // read menu option from client
                int menuOption = Integer.parseInt(inString); // set menu option
                
                System.out.println("Client said: "+inString); // TESTING ONLY
                
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
            saveToFile(); // update names file
            } // end try socket
        } // end while
        
        
    } // end main method
    
        // load names file
        public static void getFileData() throws IOException {
        File file = new File("src/names.txt");

        // load file data
        if (file.exists()) {
            try (BufferedReader br = 
                    new BufferedReader(
                            new FileReader(file))) {
                    
                // add every line (name) in file to list
                for (String line; (line = br.readLine()) != null;) {
                    namesList.add(line);
                }
                
                br.close(); // close buff
            } // end try buff
        } // end if
        }
        
        // saves the list to a file (overwrites previous)
        public static void saveToFile() throws IOException {
            File file = new File("src/names.txt");
            try (FileWriter fw = new FileWriter(file, false)) {
                for (String s : namesList) {
                    fw.write(s+"\n");
                }
            }
        }

        // initiates menu command request
        public static String menuCommand(int i, String cInput) {
            String result = "";
            
            switch (i) {
                case 1: // add a name
                    namesList.add(cInput);
                    result = "Name \""+cInput+"\" added to list.";
                    break;
                case 2: // remove a name
                    if (namesList.removeIf(cInput::equals)) {
                    // :: = method reference - ClassName::MethodName (Java 8) BOOM!
                       result = "\""+cInput+"\""+" removed from list."; 
                    }
                    else {
                        result = "\""+cInput+"\""+" not found.";
                    }
                    break;
                case 3: // list all names
                    // iterate through list, concatenate, new line
                    for (String s : namesList)
                        {
                            result += s + "\n";
                        }
                    break;
                case 4: // check if a name recorded
                    if (namesList.contains(cInput)) {
                        result = "\""+cInput+"\""+" is recorded in list.";
                    }
                    else {
                        result = "\""+cInput+"\""+" not found in list.";
                    } 
                    break;
                default:
                    result = "Error finding correct menu option.";
                    break;
            } // end switch
            return result;
        } // end menuCommand
        
        // get prompt for user chosen menu option [1-4]
        public static String promptClient(int menuOpt){
            String prompt;
            
                switch (menuOpt) {
                case 1: // add a name
                    prompt = "Please provide the name to ADD and press enter.\n"
                            +"*Name can have spaces";
                    break;
                case 2: // remove a name
                    prompt = "Please provide the name to REMOVE and press enter.\n"
                        +"*Name can have spaces";
                    break;
                case 3: // list all names
                    prompt = "Press enter to RETRIEVE all names";
                    break;
                case 4: // check if a name recorded
                    prompt = "Please provide the name to SEARCH and press enter.\n"
                        +"*Name can have spaces";
                    break;
                default:
                    prompt = "Error finding correct menu option.";
                    break;
            } // end switch
            return prompt;
        } // end promptClient
    
} // end class NameServer
