/*
    Dan Hogan
    dhogan15
    22108261
    ISY00246
    Assignment 1
    Part 1d
    Purpose: Stock interface
 */

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