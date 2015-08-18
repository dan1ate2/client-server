/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 3a
    Purpose: Client voting
 */
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final int clientPort = 2014;
    private static final int serverPort = 2015;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userVote;
        try {
            InetAddress server = InetAddress.getByName(args[0]);
            System.out.print("Enter 'yes' or 'no': ");
            userVote = sc.nextLine();
            
            byte [] buff = userVote.getBytes();
            
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
