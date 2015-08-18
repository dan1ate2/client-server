/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 3a
    Purpose: Server voting
 */
import java.net.*;

public class Server {
   private static final int serverPort = 2015;

   public static void main(String [] args) {
        try {
            System.out.println("Voting server "
                    + InetAddress.getLocalHost().getHostAddress() + 
                    " active on port " + serverPort + 
                    "\nType Ctrl-C to finish.\n");
            
            try (DatagramSocket sock = new DatagramSocket(serverPort)) {
                String data = "";
                while (!data.equals("EXIT")) {
                    byte [] buff = new byte[100];
                    DatagramPacket packet =
                            new DatagramPacket(buff,buff.length);
                    sock.receive(packet);
                    data = new String(packet.getData(),0,packet.getLength());
                    
                    System.out.println("[" 
                            + packet.getAddress().getHostAddress() 
                            + "] " + "\"" 
                            + (data.substring(0, 1).toUpperCase() + data.substring(1)) 
                            + "\"" + " vote received");
                } // end while
            } // end try DatagramSocket
         } // end try
        catch (Exception e) {
         e.printStackTrace();
         }
      } // end main()
   
}
