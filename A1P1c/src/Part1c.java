/*
    Dan Hogan
    dhogan15
    22108261
    ISY00246
    Assignment 1
    Part 1c
    Purpose: Test polymorphism on stock items
 */

import java.util.ArrayList;

public class Part1c {

    public static void main(String[] args) {
        // create array list of stock
        ArrayList<Stock> stockList = new ArrayList();
        stockList.add(new Confectionary
            (11, "Red Liqorice", 5, "23rd June 2016"));
        stockList.add(new Confectionary
            (12, "Jelly Beans", 34, "15th December 2017"));
        stockList.add(new SoftDrink(21, "Sprite", 3, 12));
        stockList.add(new SoftDrink(22, "Coke", 5, 12));
        
        // print stock items in arraylist
        for (Stock i : stockList) {
            displayStockItem(i);
        }
    }
    
    // polymorphically process Stock items for print
    public static void displayStockItem(Stock s) {
        System.out.println(s.getID() + ", " + s.getDescription());
    }
    
}
