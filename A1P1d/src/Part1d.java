/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 1d
    Purpose: Test reading file with Stock object data then printing to console
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
        // welcome message and instructions
        System.out.println("Welcome.\nTo display stock items, the format " + 
                "inside the file must start with item identifier on first line,\n" + 
                "followed by item details on seperate lines, then '!' on line "
                + "underneath.\n\nExample for Confectionary:\ncf\n32\nChocolate\n55\n10th "
                + "August 2018\n!\n" + "\nConfectionary data (in order): 'cf', Id, "
                + "Description, Quantity, Expiry, '!'\n" + "Soft Drink data (in order): "
                + "'sd', ID, Description, Quantity, Package Size, '!'\n");
        
        if (args.length > 0) { // if any args exist
            File file = new File(args[0]); // filename from console parameter
            
            // get file input
            if (file.exists()) {
                try (BufferedReader br = 
                        new BufferedReader(new FileReader(file))) {
                    ArrayList<Stock> stockList = new ArrayList();
                    int lineNumber = 0; // line number in file for errors
                    
                    // scan every line in file
                    for (String line; (line = br.readLine()) != null;) {
                        ArrayList<String> stockItemElements = new ArrayList();
                        
                        // while not end of stock record or file
                        while ((!"!".equals(line)) && (line != null)) { 
                            lineNumber++; // update line number read in file
                            
                            // add to temp arraylist
                            if (!"".equals(line)) { // do if not blank line
                                stockItemElements.add(line);
                            }
                            line = br.readLine(); // read next line
                        }
                        
                        // put data objects into arraylist
                        if (("!".equals(line)) || (line == null)) {
                            lineNumber++; // update line number read in file
                            
                            // find flag in start of data
                            switch (stockItemElements.get(0)) { 
                                case "cf": // flag is Confectionary object
                                    try {
                                        // validate data
                                        stockList.add(new Confectionary
                                            (Integer.parseInt(stockItemElements.get(1)), 
                                            stockItemElements.get(2), 
                                            Integer.parseInt(stockItemElements.get(3)), 
                                            stockItemElements.get(4)));
                                    }
                                    catch (Exception e){
                                        System.out.println("Data error:\n" + 
                                            "Couldn't create confectionary item " 
                                            + "above line " + lineNumber + 
                                            " in file.");
                                    }
                                    break;
                                case "sd": // flag is SoftDrink object
                                    try {
                                        // validate data
                                        stockList.add(new SoftDrink
                                            (Integer.parseInt(stockItemElements.get(1)), 
                                            stockItemElements.get(2), 
                                            Integer.parseInt(stockItemElements.get(3)), 
                                            Integer.parseInt(stockItemElements.get(4))));
                                    }
                                    catch (Exception e){
                                        System.out.println("Data error:\n" + 
                                            "Couldn't create softdrink item " + 
                                            "above line " + lineNumber + 
                                            " in file.");
                                    }
                                    break;
                                default : // no flag found
                                    System.out.println("Data error:\n" + "No item " 
                                        + "identifier found. Couldn't create "
                                        + "item above line " + lineNumber + 
                                        " in file.");
                                    break;
                            } // end of switch
                        } // end 'if statement' (putting data into arraylist)
                    } // end 'for loop' (scanning lines of file)
                    br.close(); // close file
                    
                    // print stock items in arraylist
                    System.out.println("\n--STOCK ITEMS--\nConfectionary:");
                    stockList.stream().forEach((i) -> {
                        if (i instanceof Confectionary) {
                            // print confectionary items
                            displayStockItem(i);
                        }
                    });
                    System.out.println("\nSoft Drink:");
                    stockList.stream().forEach((i) -> {
                        if (i instanceof SoftDrink) {
                            // print softdrink items
                            displayStockItem(i);
                        }
                    });
                }
            } // end of 'if file exists'
            else {
                // error message if filename doesn't exist
                System.out.println("Error, input file does not exist.");
            }
        } // end of 'if arguments > 0'
        else {
        // error message if no filename given
            System.out.println("Error, no filename was given.");
        }
    } // end of main method
    
    // polymorphically process Stock items for print
    public static void displayStockItem(Stock s) {
        System.out.println(s);
    }
}
