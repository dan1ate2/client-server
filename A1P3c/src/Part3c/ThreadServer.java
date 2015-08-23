/*
    Dan Hogan
    dhogan15
    22108261
    ISY00246
    Assignment 1
    Part 3c
    Purpose: Server app that reads/writes list of names to/from file.
        Allows clients to connect and modify (multithreaded server).
 */
package Part3c;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ThreadServer {
    private static final int SERVER_PORT = 2015;
    public static List<String> namesList = new ArrayList(); // LOOKUP CASE FORMAT FOR GLOBAL VARIABLE (IE CAMELCASE)
    
    public static void main(String[] args) throws IOException {
        
        getFileData(); // load file into array 
        
        System.out.println("Server operating on "
            +InetAddress.getLocalHost().getHostAddress()
            +" on port "+SERVER_PORT+"\n");
        ServerSocket ssock = new ServerSocket(SERVER_PORT); // set server socket
        
        while (true) {
            try {
            // wait for incoming connection
            Socket sock = ssock.accept();
            System.out.println(sock.getInetAddress().getHostAddress() 
                + " is now connected");

            // new thread and run
            Thread clientThread = new Thread(new ProcessClient(sock));
            clientThread.start();
            }
            catch (IOException ioe) {
                saveToFile();
                System.out.println("Connection lost. Names file saved.");
            }
        } 
    }
    
    // load names file
    public static void getFileData() throws IOException {
    File file = new File("src/Part3c/names.txt");
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
    
    // saves the list to a file (overwrites previous)
    public static void saveToFile() throws IOException {
        File file = new File("src/Part3c/names.txt");
        try (FileWriter fw = new FileWriter(file, false)) {
            for (String s : namesList) {
                if (!s.equals("")) {
                    fw.write(s+"\n");
                }
            } // end for
        } // end try
    } // end save to file()
        
}