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
                
                // print file to console
                for (String line; (line = br.readLine()) != null;) {
                    System.out.println(line); // print each record
                }
                
                // distinguish stock type and add to array
                
            }
        }
    }
}
