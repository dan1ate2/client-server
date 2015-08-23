/*
    Dan Hogan
    dhogan15
    22108261
    ISY00246
    Assignment 1
    Part 3a
    Purpose: Client voting
 */
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final int clientPort = 2014;
    private static final int serverPort = 2015;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userVote;
        boolean validVote = false;
        
        try {
            InetAddress server = InetAddress.getByName(args[0]);

            // loop until correct vote entered (yes or no)
            do {
                System.out.print("Enter \"yes\" or \"no\": "); // prompt for vote
                userVote = sc.nextLine(); // console input to String
                userVote = userVote.toLowerCase(); // vote to lowercase

                // validate user vote input
                switch (userVote) {
                    case "yes":
                        System.out.println("Vote \"yes\" sent to " + args[0]);
                        validVote = true;
                        break;
                    case "no":
                        System.out.println("Vote \"no\" sent to " + args[0]);
                        validVote = true;
                        break;
                    default:
                        System.out.println("Error, an invalid vote was entered");
                        break;
                }
            }  
            while (!validVote);
            
            byte [] buff = userVote.getBytes();
            DatagramPacket packet = 
                    new DatagramPacket(buff, buff.length, server, serverPort);
            try (DatagramSocket sock = new DatagramSocket(clientPort)) {
                sock.send(packet);
                sock.close();
            }
        }
        catch (Exception e) {
            System.out.println("Could not start voting client.\n" 
            + "Incorrect or no IP address given before starting program.");
        }
    }
}
