/*
    Dan Hogan
    dhogan15
    22108261
    ISY00246
    Assignment 1
    Part 3a
    Purpose: Server receiving & counting votes
 */
import java.net.*;

public class Server {
   private static final int serverPort = 2015;

   public static void main(String [] args) {
        String data;
        int yesVote = 0;
        int noVote = 0;

        try {
            System.out.println("Voting server "
                    + InetAddress.getLocalHost().getHostAddress() + 
                    " active on port " + serverPort + 
                    "\nType Ctrl-C to finish.\n");
            
            try (DatagramSocket sock = new DatagramSocket(serverPort)) {
                
                // loop through voting server indefinately
                while (true) {
                    boolean validVote = false;
                    
                    // set up packet & socket
                    byte [] buff = new byte[100];
                    DatagramPacket packet =
                            new DatagramPacket(buff,buff.length);
                    sock.receive(packet);
                    data = new String(packet.getData(),0,packet.getLength());
                    
                    // validate vote received
                    switch (data) {
                        case "yes":
                            yesVote++;
                            validVote = true;
                            break;
                        case "no":
                            noVote++;
                            validVote = true;
                            break;
                        default:
                            System.out.println("**Error** Bad vote string received");
                            break;
                    }

                    // if valid vote, print vote info & tally
                    if (validVote) {
                    System.out.println("[" 
                            + packet.getAddress().getHostAddress() 
                            + "] " + "\"" 
                            + (data.substring(0, 1).toUpperCase() + data.substring(1)) 
                            + "\"" + " vote received");
                    System.out.println("    So far Yes= " + yesVote 
                    + " No= " + noVote + ".");
                    }  
                } // end while
            } // end try DatagramSocket
         } // end try
        catch (Exception e) {
         System.out.println("Error, could not start vote server, possible "
                 + "incorrect server address/port or firewall settings.");
         }
      } // end main()
   
}
