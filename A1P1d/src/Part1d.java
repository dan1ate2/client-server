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
import java.util.ArrayList;

public class Part1d {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println(args[0]); // prints argument
        
        if (args.length > 0) { // if any args exist
            File file = new File(args[0]); // filename from console parameter
            
            // get file input
            if (file.exists()) {
                try (BufferedReader br = 
                        new BufferedReader(new FileReader(file))) {
                    ArrayList<Stock> stockList = new ArrayList();
                    
                    // scan every line in file
                    for (String line; (line = br.readLine()) != null;) {
                        System.out.println("\nDATA: " + line); // PRINT RECORD
                        
                        String[] lineToArray;
                        lineToArray = line.split(" "); // put data into array
                        
                        // put data objects into arraylist
                        switch (lineToArray[0]) { // find flag in start of data
                            case "cf": // Confectionary
                                System.out.println("Flag is 'cf'");
                                // validate data
                                try {
                                    stockList.add(new Confectionary
                                        (Integer.parseInt(lineToArray[1]), 
                                            lineToArray[2], 
                                            Integer.parseInt(lineToArray[3]), 
                                            lineToArray[4]));
                                }
                                catch (Exception e){
                                    System.out.println("Couldn't create "
                                            + "confectionary item");
                                }
                                break;
                            case "sd": // Soft Drink
                                System.out.println("Flag is 'sd'");
                                break;
                            default : // if no flag found
                                System.out.println("Error: No flag found,"
                                        + " item skipped.");
                                break;
                        }
                    }
                    br.close(); // close file
                    
                    // print stock items in arraylist
                    stockList.stream().forEach((i) -> {
                        displayStockItem(i);
                    });
                }
            }
        }
    }
    
    // polymorphically process Stock items for print
    public static void displayStockItem(Stock s) {
        System.out.println(s);
    }
}
