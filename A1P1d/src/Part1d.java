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
//        System.out.println(args[0]); // prints argument (filename)
        
        if (args.length > 0) { // if any args exist
            File file = new File(args[0]); // filename from console parameter
            
            // get file input
            if (file.exists()) {
                try (BufferedReader br = 
                        new BufferedReader(new FileReader(file))) {
                    ArrayList<Stock> stockList = new ArrayList();
                    
                    // scan every line in file
                    for (String line; (line = br.readLine()) != null;) {
//                        System.out.println("\nDATA: " + line); // PRINT RECORD
                        
                        ArrayList<String> stockItemElements = new ArrayList();
                        // while not end of stock record or file
                        while ((!"!".equals(line)) && (line != null)) { 
                            // add to array
                            stockItemElements.add(line);
                            line = br.readLine();
                        }
                        
                        // put data objects into arraylist
                        if (("!".equals(line)) || (line == null)) {
                            // find flag in start of data
                            switch (stockItemElements.get(0)) { 
                                case "cf": // flag is Confectionary
                                    // validate data
                                    try {
                                        stockList.add(new Confectionary
                                            (Integer.parseInt(stockItemElements.get(1)), 
                                            stockItemElements.get(2), 
                                            Integer.parseInt(stockItemElements.get(3)), 
                                            stockItemElements.get(4)));
                                    }
                                    catch (Exception e){
                                        System.out.println("Couldn't create "
                                            + "confectionary item");
                                    }
                                    break;
                                case "sd": // flag is Soft Drink
                                    // validate
                                    try {
                                        stockList.add(new SoftDrink
                                            (Integer.parseInt(stockItemElements.get(1)), 
                                            stockItemElements.get(2), 
                                            Integer.parseInt(stockItemElements.get(3)), 
                                            Integer.parseInt(stockItemElements.get(4))));
                                    }
                                    catch (Exception e){
                                        System.out.println("Couldn't create "
                                            + "softdrink item");
                                    }
                                    break;
                                default : // no flag found
                                    System.out.println("Error: No flag found,"
                                        + " item skipped.");
                                    break;
                            }
                        }
                    }
                    br.close(); // close file
                    
                    // print stock items in arraylist
                    System.out.println("\nSTOCK ITEMS:");
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
