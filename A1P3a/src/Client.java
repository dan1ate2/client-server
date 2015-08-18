/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 3a
    Purpose: Client voting
 */
import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class Client {
    private static final int clientPort = 2014;
    private static final int serverPort = 2015;
    
    public static void main(String[] args) {
        try {
            InetAddress server = InetAddress.getLocalHost();
            byte [] buff = "Hello Server".getBytes();
            
            System.out.println(Arrays.toString(buff));
            
            DatagramPacket packet = 
                    new DatagramPacket(buff, buff.length, server, serverPort);
            
            try (DatagramSocket sock = new DatagramSocket(clientPort)) {
                sock.send(packet);
                sock.close();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
}
