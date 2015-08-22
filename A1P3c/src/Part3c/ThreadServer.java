/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 3c
    Purpose: Server app that reads/writes list of names to/from file.
        Allows client to connect and modify.
 */
package Part3c;

import static Part3c.ProcessClient.saveToFile;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ThreadServer {
    private static final int SERVER_PORT = 2015;
    private static List<String> namesList = new ArrayList(); // LOOKUP CASE FORMAT FOR GLOBAL VARIABLE (IE CAMELCASE)
    
    public static void main(String[] args) 
            throws UnknownHostException, IOException{
        
        System.out.println("Server operating on "
            +InetAddress.getLocalHost().getHostAddress()
            +" on port "+SERVER_PORT+"\n");
        
        ServerSocket ssock = new ServerSocket(SERVER_PORT); // set server socket
        getFileData(); // load file into array

            while (true) {
                // wait for client input
                try (Socket sock = ssock.accept()) {
                    System.out.println(sock.getInetAddress().getHostAddress() 
                        + " is now connected");

                // create thread
                // send thread to ProcessThread

                } // end try socket
                catch (SocketException se) {
                    saveToFile();
                    System.out.println("Connection lost. Names file saved.");
                }
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
                        if (!line.equals("")) {
                            namesList.add(line);
                        }
                    }
                    br.close(); // close buff
                } // end try buff
            } // end if
        } // end getFileData()
    
} // end class NameServer
