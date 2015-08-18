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
            System.out.println("Server operating on "
                    + InetAddress.getLocalHost().getHostAddress());
            
            DatagramSocket sock = new DatagramSocket(serverPort);

            String data = "";
            while (!data.equals("EXIT")) {

            byte [] buff = new byte[100];

            DatagramPacket packet =
               new DatagramPacket(buff,buff.length);

            sock.receive(packet);

            data=new String(packet.getData(),0,packet.getLength());

            System.out.println("Received: " + data);
            }

         sock.close();
         }
        catch (Exception e) {
         e.printStackTrace();
         }
      }
}