package ServerPkg;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class JustServer {
    private static final int SERVER_PORT = 2015;
    public static List<String> namesList = new ArrayList(); // LOOKUP CASE FORMAT FOR GLOBAL VARIABLE (IE CAMELCASE)
    
    public static void main(String[] args) throws IOException {
        getFileData(); // load file into array 
        
        System.out.println("Server operating on "
            +InetAddress.getLocalHost().getHostAddress()
            +" on port "+SERVER_PORT+"\n");
        ServerSocket ssock = new ServerSocket(SERVER_PORT); // set server socket
        
        while (true) {
            Socket sock = ssock.accept();

            System.out.println(sock.getInetAddress().getHostAddress() 
                + " is now connected");

            // new thread and run
            Thread clientThread = new Thread(new JustServerProcess(sock));
            clientThread.start();
        } 
    }
    
        // load names file
        public static void getFileData() throws IOException {
        File file = new File("src/ServerPkgnames.txt");
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
    
}
