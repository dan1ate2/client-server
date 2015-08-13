/*
    Dan Hogan
    ISY00246
    Assignment 1
    Part 1c
    Purpose: Stock interface
 */
package part1c;

public interface Stock {
    public int getID();
        // get stock ID
    public String getDescription();
        // get stock description
    public int number();
        // get number of items
    public void setNumber(int num);
        // update number (quantity) in stock
}