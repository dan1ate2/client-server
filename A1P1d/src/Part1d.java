/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 1d
    Purpose: Test reading file with Stock object data
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Part1d {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println(args[0]); // prints argument
        
        if (args.length > 0) { // if argument exists
            
            // try print file
            File file = new File(args[0]);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                
                // scan every line in file
                for (String line; (line = br.readLine()) != null;) {
                    System.out.println("\nDATA: " + line); // print each record
                    
                    // test flags in file (method test 1)
                    System.out.println("\nTest method 1:");
                    if (line.contains("cf")) {
                        System.out.println("Line contains 'cf'");
                    }
                    else if (line.contains("sd")) {
                        System.out.println("Line contains 'sd'");
                    }
                    
                    // test flags in file (method test 2)
                    System.out.println("\nTest method 2:");
                    String[] lineToArray;
                    lineToArray = line.split(" ");
                    switch (lineToArray[0]) {
                        case "cf":
                            System.out.println("First string is 'cf'");
                            break;
                        case "sd":
                            System.out.println("First string is 'sd'");
                            break;
                    }
                    
                    // test flags in file (method test 3)
                    System.out.println("\nTest method 3:");
                    if (line.startsWith("cf")) {
                        System.out.println("First string starts with 'cf'");
                    }
                    else if (line.startsWith("sd")) {
                        System.out.println("First string starts with 'sd'");
                    }
                    
                    // test flags (method 4)
                    System.out.println("\nTest method 4:");
                    String flag = line.substring(0, 2);
                    switch (flag) {
                        case "cf":
                            System.out.println("Flag string is 'cf'");
                            break;
                        case "sd":
                            System.out.println("Flag string is 'sd'");
                            break;
                    }
                }
                
                // distinguish stock type and add to array
                
            }
        }
    }
}
